package cn.NTBrave.poetry.db;

import org.litepal.crud.LitePalSupport;

//主界面City列表数据库字段
public class CityDb extends LitePalSupport {

    private String cityDb_cid;
    private String cityDb_cityName;
    private String cityDb_txt;
    private String cityDb_temperature;
    private int cityDb_imageId;

    public CityDb() {

    }

    public CityDb(String cityDb_cid, String cityDb_cityName, String cityDb_txt, String cityDb_temperature, int cityDb_imageId) {
        this.cityDb_cid = cityDb_cid;
        this.cityDb_cityName = cityDb_cityName;
        this.cityDb_txt = cityDb_txt;
        this.cityDb_temperature = cityDb_temperature;
        this.cityDb_imageId = cityDb_imageId;
    }

    public String getCityDb_cid() {
        return cityDb_cid;
    }

    public void setCityDb_cid(String cityDb_cid) {
        this.cityDb_cid = cityDb_cid;
    }

    public String getCityDb_cityName() {
        return cityDb_cityName;
    }

    public void setCityDb_cityName(String cityDb_cityName) {
        this.cityDb_cityName = cityDb_cityName;
    }

    public String getCityDb_txt() {
        return cityDb_txt;
    }

    public void setCityDb_txt(String cityDb_txt) {
        this.cityDb_txt = cityDb_txt;
    }

    public String getCityDb_temperature() {
        return cityDb_temperature;
    }

    public void setCityDb_temperature(String cityDb_temperature) {
        this.cityDb_temperature = cityDb_temperature;
    }

    public int getCityDb_imageId() {
        return cityDb_imageId;
    }

    public void setCityDb_imageId(int cityDb_imageId) {
        this.cityDb_imageId = cityDb_imageId;
    }
}
