package com.nickli.scheduleapp.schedule;

import java.io.Serializable;

// Serializable to make a marker interface, guides information for ScheduleRemind
public class ScheduleUpload implements Serializable {
    private String classPeriod;
    private String className;
    private String classTime;
    private String classDate;

    // Sets variables when method is called
    public ScheduleUpload(String classPeriod, String className, String classTime, String classDate) {
        this.className = className;
        this.classPeriod = classPeriod;
        this.classTime = classTime;
        this.classDate = classDate;
    }
    // Returns class information when method is called
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
