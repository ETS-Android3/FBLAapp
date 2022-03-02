package com.nickli.scheduleapp.schedule;

import java.io.Serializable;

public class ScheduleUpload implements Serializable {
    private String classPeriod;
    private String className;
    private String classTime;
    private String classDate;

    public ScheduleUpload(String classPeriod, String className, String classTime, String classDate) {
        this.className = className;
        this.classPeriod = classPeriod;
        this.classTime = classTime;
        this.classDate = classDate;
    }
    public String getClassPeriod() {
        return classPeriod;
    }
    public String getClassName() {
        return className;
    }
    public String getClassTime() {
        return classTime;
    }
    public String getClassDate() {
        return classDate;
    }
    public void setClassPeriod(String classPeriod) {
        this.classPeriod = classPeriod;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }
    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }
    public ScheduleUpload() {

    }
}
