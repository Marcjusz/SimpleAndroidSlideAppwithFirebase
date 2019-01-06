package com.example.mn.simpleandroidslideappwithfirebase;

import java.io.Serializable;

public class DataBaseGift implements Serializable {
    String mark;
    String prince;
    String description;
    String title;

    DataBaseGift() {

    }

    public DataBaseGift(String mark, String prince, String description, String title) {
        this.mark = mark;
        this.prince = prince;
        this.description = description;
        this.title = title;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getPrince() {
        return prince;
    }

    public void setPrince(String prince) {
        this.prince = prince;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}