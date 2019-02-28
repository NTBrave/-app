package cn.NTBrave.poetry.util;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import cn.NTBrave.poetry.entity.Calendar;

//解析calendar返回的json数据

public class CalendarUtil {
    private static final String TAG = "CalendarUtil";

    public static Calendar handleCalendarResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            Log.d(TAG, "handleCalendarResponse: "+jsonObject.toString());
            return new Gson().fromJson(jsonObject.toString(), Calendar.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
