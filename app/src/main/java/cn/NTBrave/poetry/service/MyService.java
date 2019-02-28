package cn.NTBrave.poetry.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.NTBrave.poetry.db.PoetryDb;
import cn.NTBrave.poetry.entity.Poetry;
import cn.NTBrave.poetry.entity.PoetryWeather;
import cn.NTBrave.poetry.entity.RefreshTimeEvent;
import cn.NTBrave.poetry.util.CalendarUtil;
import cn.NTBrave.poetry.util.DateUtil;
import cn.NTBrave.poetry.util.HttpUtil;
import cn.NTBrave.poetry.entity.CalendarEvent;
import cn.NTBrave.poetry.util.PoetryWeatherUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;
//
public class MyService extends Service {

    private static final String TAG = "MyService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        LitePal.getDatabase();


//        new Thread(() -> {
//            updateWeatherBg();
//
//            stopSelf();
//
//        }).start();

        createPoetryDatabase();
        String date = DateUtil.getDateString();
        Log.d(TAG, "date: "+date);
        //读取万年历缓存
        SharedPreferences preferences = getSharedPreferences("date", MODE_PRIVATE);
        String today = preferences.getString("today", null);
        String reason = preferences.getString("reason", null);
        String suit = preferences.getString("suit", null);
        String avoid = preferences.getString("avoid", null);
        String lunar = preferences.getString("lunar", null);
        String lunarYear = preferences.getString("lunarYear", null);
        if (date.equals(today)) {
            Log.d(TAG, "onStartCommand: "+"读取缓存发送");
            EventBus.getDefault().postSticky(new CalendarEvent(reason,suit,avoid,lunar,lunarYear,today));
        } else {
            Log.d(TAG, "onStartCommand: "+"查询网络数据后发送");
            requestCalendar(date);
        }

        updateCityWeather();
        return super.onStartCommand(intent, flags, startId);

    }


    /**
     * 查询万年历显示宜忌
     * @param date
     */
    private void requestCalendar(final String date) {
        final CalendarEvent calendarEvent = new CalendarEvent();
        String calendarUri = "http://v.juhe.cn/calendar/day?date=" + date + "&key=3ec186487910553df15ad59c08761c55";
        HttpUtil.sendOkHttpRequest(calendarUri, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String responsText = response.body().string();
                final cn.NTBrave.poetry.entity.Calendar calendar = CalendarUtil.handleCalendarResponse(responsText);

                if (calendar != null) {
                    calendarEvent.setReason(calendar.getReason());
                    Log.d(TAG, "onResponse: " + calendar.getReason());
                    Log.d(TAG, "onResponse: " + calendarEvent.getReason());
                    calendarEvent.setSuit(calendar.getResult().getResult_data().getSuit());
                    calendarEvent.setAvoid(calendar.getResult().getResult_data().getAvoid());
                    calendarEvent.setLunar(calendar.getResult().getResult_data().getLunar());
                    calendarEvent.setLunarYear(calendar.getResult().getResult_data().getLunarYear());
                    calendarEvent.setDate(calendar.getResult().getResult_data().getToday());
                    //把万年历数据载入缓存
                    SharedPreferences.Editor editor = getSharedPreferences("date", MODE_PRIVATE).edit();
                    editor.putString("reason", calendar.getReason());
                    editor.putString("today", date);
                    editor.putString("suit", calendar.getResult().getResult_data().getSuit());
                    editor.putString("avoid", calendar.getResult().getResult_data().getAvoid());
                    editor.putString("lunar", calendar.getResult().getResult_data().getLunar());
                    editor.putString("lunarYear", calendar.getResult().getResult_data().getLunarYear());
                    editor.apply();
                    //发送粘性事件
                    EventBus.getDefault().postSticky(calendarEvent);
                }
            }

        });
    }

    /**
     * 后台更新图片
     */
    private void updateWeatherBg() {
        //向服务器发送请求,是否有新图片
        //如果有新图片,开始加载
        String uri = "http://www.hzmeurasia.cn/background/bg.png";
        HttpUtil.sendOkHttpRequest(uri, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: 服务中图片加载失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bg = response.body().string();
                SharedPreferences.Editor editor = getSharedPreferences("background", MODE_PRIVATE).edit();
                editor.putString("Bg", bg);
                editor.apply();
            }
        });
    }

    //定时任务自动更新城市列表天气
    private void updateCityWeather() {
        Log.d(TAG, "updateCityWeather: 开始刷新天气");
        SharedPreferences preferences = getSharedPreferences("person", MODE_PRIVATE);
        int time = preferences.getInt("refreshFlag", 3);
        List<Integer> timeList = new ArrayList<>();
        timeList.add(1);
        timeList.add(2);
        timeList.add(4);
        timeList.add(8);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Log.d(TAG, "updateCityWeather: time"+time);
        //设定间隔时间
        Log.d(TAG, "updateCityWeather: 刷新间隔时间"+timeList.get(time));
        int hours = timeList.get(time) * 60 * 60 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + hours;
        Intent intent = new Intent(this, MyService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, intent, 0);
        assert manager != null;
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
    }

    //获取服务器诗句数据库数据
    private void createPoetryDatabase() {
        String poetryWeatherUrl = "http://www.hzmeurasia.cn";
        HttpUtil.sendOkHttpRequest(poetryWeatherUrl, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String responsText = response.body().string();
                Log.d(TAG, "onResponse: "+responsText);
                final PoetryWeather poetryWeather = PoetryWeatherUtil.handlePoetryWeatherResponse(responsText);
                if (poetryWeather!=null) {
                    if (poetryWeather.poetryList.size() != LitePal.count(PoetryDb.class)
                            && poetryWeather.poetryList.size() > 0) {
                        for (Poetry poetry : poetryWeather.poetryList) {
                            PoetryDb poetryDb = new PoetryDb();
                            poetryDb.setPoetryDb_id(poetry.id);
                            poetryDb.setPoetryDb_poetry(poetry.poetry);
                            poetryDb.setPoetryDb_poetry_link(poetry.poetry_link);
                            poetryDb.setPoetryDb_weather(poetry.weather);
                            Log.d(TAG, "onResponse: 作者"+poetry.author);
                            poetryDb.setPoetryDb_author(poetry.author);
                            poetryDb.setPoetryDb_annotation(poetry.annotation);
                            poetryDb.setPoetryDb_qwxl(poetry.qwxl);
                            poetryDb.setPoetryDb_jygk(poetry.jygk);
                            poetryDb.setPoetryDb_yyql(poetry.yyql);
                            poetryDb.save();
                        }
                    }
                }
                Log.d(TAG, "诗词数据库字段数 "+LitePal.count(PoetryDb.class));
//                SharedPreferences.Editor editor = getSharedPreferences("poetry", MODE_PRIVATE).edit();

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
