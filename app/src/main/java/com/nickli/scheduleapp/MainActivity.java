package com.nickli.scheduleapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
    // Define variables
    ImageView imageView;
    TextView textView;
    ConstraintLayout btnLogOut;
    FirebaseAuth mAuth;

    ConstraintLayout schedule;
    ConstraintLayout extraActivity;
    ConstraintLayout lunch;
    ConstraintLayout emailStaff;
    ConstraintLayout calendar;
    ConstraintLayout bugReport;

    StorageReference mStorageRef;
    DatabaseReference mDatabaseRef;
    FirebaseAuth auth;
    ArrayList<String> pathArray;
    Uri imageUpload;

    ConstraintLayout showUploads;

    @Override
    // Method called when app is run or started
    protected void onStart() {
        super.onStart();
        // Makes sure to see if user is currently logged in or not
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            // If not logged in, sends user to Login Activity
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    @Override
    // When activity is created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sets frontend layout
        setContentView(R.layout.activity_main);

        schedule = findViewById(R.id.schedule);
        extraActivity = findViewById(R.id.extraActivity);
        lunch = findViewById(R.id.lunch);
        emailStaff = findViewById(R.id.emailStaff);
        calendar = findViewById(R.id.calendar);
        bugReport = findViewById(R.id.bugReport);

        btnLogOut = findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();

        // OnClickListener for all the different functions, sends them to different activities
        btnLogOut.setOnClickListener(view -> {
            // Gets email and information of the user signed in
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            // Signs the user out of Google
            mGoogleSignInClient.signOut();
            // Signs the user out of app
            mAuth.getInstance().signOut();
            // Changes the page to the login page
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });


        // If buttons are clicked, the page will change
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

        bugReport.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, BugActivity.class));
        });
    }
}
