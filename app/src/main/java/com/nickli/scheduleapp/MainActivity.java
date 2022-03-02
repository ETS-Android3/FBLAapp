package com.nickli.scheduleapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.nickli.scheduleapp.emailStaff.EmailActivity;
import com.nickli.scheduleapp.extraActivities.ClubActivity;
import com.nickli.scheduleapp.lunch.LunchActivity;
import com.nickli.scheduleapp.schedule.ScheduleActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    ConstraintLayout btnLogOut;
    FirebaseAuth mAuth;

    ConstraintLayout schedule;
    ConstraintLayout extraActivity;
    ConstraintLayout lunch;
    ConstraintLayout emailStaff;
    ConstraintLayout calendar;

    StorageReference mStorageRef;
    DatabaseReference mDatabaseRef;
    FirebaseAuth auth;
    ArrayList<String> pathArray;
    Uri imageUpload;

    ConstraintLayout showUploads;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schedule = findViewById(R.id.schedule);
        extraActivity = findViewById(R.id.extraActivity);
        lunch = findViewById(R.id.lunch);
        emailStaff = findViewById(R.id.emailStaff);
        calendar = findViewById(R.id.calendar);

        btnLogOut = findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();

        btnLogOut.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });


        schedule.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ScheduleActivity.class));
        });

        extraActivity.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ClubActivity.class));
        });

        lunch.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, LunchActivity.class));
        });

        emailStaff.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, EmailActivity.class));
        });

        calendar.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CalendarActivity.class));
        });
    }
}
