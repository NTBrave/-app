package cn.NTBrave.poetry.entity;

//
public class CalendarEvent {
    private String reason;
    private String suit;
    private String avoid;
    private String lunar;
    private String lunarYear;
    private String date;

    public CalendarEvent() {}

    public CalendarEvent(String reason, String suit, String avoid,String lunar,String lunarYear,String date) {
        this.reason = reason;
        this.suit = suit;
        this.avoid = avoid;
        this.lunar = lunar;
        this.lunarYear = lunarYear;
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getAvoid() {
        return avoid;
    }

    public void setAvoid(String avoid) {
        this.avoid = avoid;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getLunarYear() {
        return lunarYear;
    }

    public void setLunarYear(String lunarYear) {
        this.lunarYear = lunarYear;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
