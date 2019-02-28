package cn.NTBrave.poetry.entity;

//
public class RefreshTimeEvent {
    private String refreshFlag;

    public String getRefreshFlag() {
        return refreshFlag;
    }

    public void setRefreshFlag(String refreshFlag) {
        this.refreshFlag = refreshFlag;
    }

    public RefreshTimeEvent(String refreshFlag) {
        this.refreshFlag = refreshFlag;
    }
}
