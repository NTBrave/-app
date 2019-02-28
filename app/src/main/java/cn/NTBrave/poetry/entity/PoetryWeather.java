package cn.NTBrave.poetry.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//
public class PoetryWeather {

    @SerializedName("poetry")
    public List<Poetry> poetryList;
}
