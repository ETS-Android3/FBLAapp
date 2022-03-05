package com.nickli.scheduleapp.emailStaff;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nickli.scheduleapp.MainActivity;
import com.nickli.scheduleapp.R;

import java.util.ArrayList;

public class EmailActivity extends AppCompatActivity implements EmailAdapter.StaffClickListener{
    // Define variables
    private RecyclerView emailView;
    Button tvBack3;
    TextView staffEmail;

    private EmailAdapter adapter;
    private ArrayList<EmailUpload> list;
    FirebaseAuth auth;

    @Override
    // When activity is created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set frontend to activity_email layout
        setContentView(R.layout.activity_email);

        // Get user authentication and define database path
        auth = FirebaseAuth.getInstance();
        String location = "staff/";

        DatabaseReference root = FirebaseDatabase.getInstance().getReference(location);

        tvBack3 = findViewById(R.id.tv_back3);
        staffEmail = findViewById(R.id.staffEmail);

        EditText searchStaff = findViewById(R.id.searchStaff);
        // Implementing search text
        searchStaff.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Calls function
                filter(s.toString());
            }
        });

        emailView = findViewById(R.id.email_view);
        emailView.setHasFixedSize(true);
        emailView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        // Connect to EmailAdapter to retrieve data from realtime database
        adapter = new EmailAdapter(this , list, this::selectedStaff);
        emailView.setAdapter(adapter);

        tvBack3.setOnClickListener(view -> {
            startActivity(new Intent(EmailActivity.this, MainActivity.class));
        });

        // Add all email information onto RecyclerView
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    EmailUpload emailUpload = dataSnapshot.getValue(EmailUpload.class);
                    list.add(emailUpload);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    // Changes RecyclerView based on text inside search
    private void filter(String text) {
        ArrayList<EmailUpload> filteredList = new ArrayList<>();

        for (EmailUpload item : list) {
            if (item.getStaffName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }

    @Override
    // When the Recycler View for a staff or teacher is pressed
    public void selectedStaff(EmailUpload emailUpload){
        // Obtain the staff email from the Firebase Realtime Database
        String email = emailUpload.getStaffEmail();
        // Convert the String email into an array
        String[] emails = email.split(",");
        // Create new activity to email staff or teacher
        Intent intent = new Intent(Intent.ACTION_SEND);
        // Enter the array of email(s) in the TO: section of the email client
        intent.putExtra(Intent.EXTRA_EMAIL, emails);

        // Shows an activity to select an email client
        intent.setType("message/rfc822");
        // Opens the email client that is selected from the previous activity
        startActivity(Intent.createChooser(intent, "Choose an email client: "));
    }

}
