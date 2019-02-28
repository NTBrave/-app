package cn.NTBrave.poetry.entity;

//适配个人信息界面选项的实体类
public class PersonEntity {
    private int imageId;
    private String option;
    private String result;

    public PersonEntity(int imageId, String option, String result) {
        this.imageId = imageId;
        this.option = option;
        this.result = result;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
