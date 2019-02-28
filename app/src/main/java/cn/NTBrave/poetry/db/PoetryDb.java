package cn.NTBrave.poetry.db;

import org.litepal.crud.LitePalSupport;

//类功能：主界面诗词列表数据库字段
public class PoetryDb extends LitePalSupport {

    private int poetryDb_id;
    private String poetryDb_poetry;
    private String poetryDb_poetry_link;
    private String poetryDb_weather;
    private String poetryDb_author;
    private String poetryDb_annotation;
    private int poetryDb_qwxl;
    private int poetryDb_jygk;
    private int poetryDb_yyql;

    public PoetryDb() {

    }


    public int getPoetryDb_id() {
        return poetryDb_id;
    }

    public void setPoetryDb_id(int poetryDb_id) {
        this.poetryDb_id = poetryDb_id;
    }

    public String getPoetryDb_poetry() {
        return poetryDb_poetry;
    }

    public void setPoetryDb_poetry(String poetryDb_poetry) {
        this.poetryDb_poetry = poetryDb_poetry;
    }

    public String getPoetryDb_poetry_link() {
        return poetryDb_poetry_link;
    }

    public void setPoetryDb_poetry_link(String poetryDb_poetry_link) {
        this.poetryDb_poetry_link = poetryDb_poetry_link;
    }

    public String getPoetryDb_weather() {
        return poetryDb_weather;
    }

    public void setPoetryDb_weather(String poetryDb_weather) {
        this.poetryDb_weather = poetryDb_weather;
    }

    public String getPoetryDb_author() {
        return poetryDb_author;
    }

    public void setPoetryDb_author(String poetryDb_author) {
        this.poetryDb_author = poetryDb_author;
    }

    public String getPoetryDb_annotation() {
        return poetryDb_annotation;
    }

    public void setPoetryDb_annotation(String poetryDb_annotation) {
        this.poetryDb_annotation = poetryDb_annotation;
    }

    public int getPoetryDb_qwxl() {
        return poetryDb_qwxl;
    }

    public void setPoetryDb_qwxl(int poetryDb_qwxl) {
        this.poetryDb_qwxl = poetryDb_qwxl;
    }

    public int getPoetryDb_jygk() {
        return poetryDb_jygk;
    }

    public void setPoetryDb_jygk(int poetryDb_jygk) {
        this.poetryDb_jygk = poetryDb_jygk;
    }

    public int getPoetryDb_yyql() {
        return poetryDb_yyql;
    }

    public void setPoetryDb_yyql(int poetryDb_yyql) {
        this.poetryDb_yyql = poetryDb_yyql;
    }
}
