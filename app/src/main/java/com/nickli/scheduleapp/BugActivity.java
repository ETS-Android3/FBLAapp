package com.nickli.scheduleapp;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import papaya.in.sendmail.SendMail;

public class BugActivity extends AppCompatActivity {
    // Defines variables
    TextInputEditText bugType;
    TextInputEditText bugSteps;
    TextView bugCancel;
    Button bugReportBtn;

    @Override
    // When the page is opened
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set frontend layout
        setContentView(R.layout.activity_bug);
        
        bugType = findViewById(R.id.bugType);
        bugSteps = findViewById(R.id.bugSteps);
        bugCancel = findViewById(R.id.bugCancel);
        bugReportBtn = findViewById(R.id.bugReportBtn);

        // When cancel button is clicked
        bugCancel.setOnClickListener(view -> {
            startActivity(new Intent(BugActivity.this, MainActivity.class));
        });

        // When report button is clicked
        bugReportBtn.setOnClickListener(view -> {
            // Calls method
            bugReport();
        });

    }

    private void bugReport() {
        // Gets the two inputed text from user
        String type = bugType.getText().toString();
        String body = bugSteps.getText().toString();

        // Sets up the mailing interface with subject and body message
        SendMail mail = new SendMail("app.email705@gmail.com", "AppEmail123",
                "nickli.1540489@gmail.com",
                "Bug Report",
                type+":\n\n"+body);

        // Sends the email to developer with subject of Bug Report
        mail.execute();

        // Confirms to user that the report has been sent
        Toast.makeText(BugActivity.this, "Bug Report sent", Toast.LENGTH_SHORT).show();

        // Brings user back to main page
        startActivity(new Intent(BugActivity.this, MainActivity.class));
    }

}
