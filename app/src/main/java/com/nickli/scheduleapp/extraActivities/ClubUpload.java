package com.nickli.scheduleapp.extraActivities;

import org.json.JSONArray;

public class ClubUpload {
    private String clubName;
    private String clubStaff;

    public ClubUpload(String clubName, String clubStaff) {
        this.clubName = clubName;
        this.clubStaff = clubStaff;
    }
    public String getClubName() {
        return clubName;
    }
    public String getClubStaff() {
        return clubStaff;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
    public void setClubStaff(String clubStaff) {
        this.clubStaff = clubStaff;
    }
    public ClubUpload() {

    }
}
