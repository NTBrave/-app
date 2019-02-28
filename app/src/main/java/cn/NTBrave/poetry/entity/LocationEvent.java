package cn.NTBrave.poetry.entity;

//SearchCity所接收的EventBus消息类
public class LocationEvent {

    private String name;
    private String privince;
    private String code;

    public LocationEvent(String name, String privince, String code) {
        this.name = name;
        this.privince = privince;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrivince() {
        return privince;
    }

    public void setPrivince(String privince) {
        this.privince = privince;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
