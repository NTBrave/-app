package cn.NTBrave.poetry.entity;

import com.google.gson.annotations.SerializedName;

//
public class Result {
    @SerializedName("data")
    private Data result_data;

    public Data getResult_data() {
        return result_data;
    }

    public void setResult_data(Data result_data) {
        this.result_data = result_data;
    }
}
