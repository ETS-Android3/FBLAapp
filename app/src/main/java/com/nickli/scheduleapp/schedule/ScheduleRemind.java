package com.nickli.scheduleapp.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nickli.scheduleapp.R;

import java.util.ArrayList;

public class ScheduleRemind extends AppCompatActivity {

    // Define Variables
    TextView tvSelectedPeriod;
    TextView tvSelectedClass;
    TextView tvSelectedTime;
    TextView tvSelectedDay;

    ScheduleUpload scheduleUpload;
    Intent intent;

    TextView remindBack;
    Button removeSchedule;
    FirebaseAuth auth;
    private ArrayList<ScheduleUpload> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_remind);
        tvSelectedPeriod = findViewById(R.id.tvSelectedPeriod);
        tvSelectedClass = findViewById(R.id.tvSelectedClass);
        tvSelectedTime = findViewById(R.id.tvSelectedTime);
        tvSelectedDay = findViewById(R.id.tvSelectedDate);
        intent = getIntent();

        remindBack = findViewById(R.id.remindBack);
        removeSchedule = findViewById(R.id.btnRemove);

        // Takes information sent from ScheduleActivity to get info of classes
        scheduleUpload = (ScheduleUpload) intent.getSerializableExtra("data");
        String period = "Period: " + scheduleUpload.getClassPeriod();
        String class_ = scheduleUpload.getClassName();
        String time = "Starting at " + scheduleUpload.getClassTime();
        String day = "On " + scheduleUpload.getClassDate();

        // Sets text in this activity to show correct information
        tvSelectedPeriod.setText(period);
        tvSelectedClass.setText(class_);
        tvSelectedTime.setText(time);
        tvSelectedDay.setText(day);

        // OnClickListeners to remove schedule or go back when clicked
        removeSchedule.setOnClickListener(view -> {
            // Calls method
            scheduleRemove(scheduleUpload);
        });

        remindBack.setOnClickListener(view -> {
            startActivity(new Intent(ScheduleRemind.this, ScheduleActivity.class));
        });

    }

    // When called from the OnClickListener
    private void scheduleRemove(ScheduleUpload scheduleUpload) {
        // Gets location of the schedule
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String userID = user.getUid();
        String location = "users/" + userID + "/";

        // Sets the location and uses query to find the correct class period to remove
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(location).child("schedule");
        Query query = ref.child(scheduleUpload.getClassPeriod());

        // Once query found, method called to remove value
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Removes the value at reference
                dataSnapshot.getRef().removeValue();
                startActivity(new Intent(ScheduleRemind.this, ScheduleActivity.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
