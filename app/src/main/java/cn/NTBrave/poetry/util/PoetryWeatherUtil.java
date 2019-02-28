package cn.NTBrave.poetry.util;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.NTBrave.poetry.entity.PoetryWeather;

//
public class PoetryWeatherUtil {

    public static PoetryWeather handlePoetryWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("PoetryWeather");
            String poetryContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(poetryContent, PoetryWeather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
