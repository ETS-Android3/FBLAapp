package com.nickli.scheduleapp.lunch;

public class LunchUpload {
    private String dayOfWeek;
    private String food;

    public LunchUpload(String dayOfWeek, String food) {
        this.dayOfWeek = dayOfWeek;
        this.food = food;
    }
    public String getDayOfWeek() {
        return dayOfWeek;
    }
    public String getFood() {
        return food;
    }
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public void setFood(String food) {
        this.food = food;
    }
    public LunchUpload() {

    }
}
