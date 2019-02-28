package cn.NTBrave.poetry.entity;

//搜索后被选中城市实体类
public class SearchCityEvent {
    private String cityCode;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public SearchCityEvent(String cityCode) {
        this.cityCode = cityCode;
    }

}
