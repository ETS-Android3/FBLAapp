package com.nickli.scheduleapp.lunch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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

public class LunchActivity extends AppCompatActivity {
    // Define variables
    private RecyclerView lunchView;
    Button tvBack2;

    private LunchAdapter adapter;
    private ArrayList<LunchUpload> list;
    FirebaseAuth auth;

    @Override
    // When activity is started/created
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set frontend to layout
        setContentView(R.layout.activity_lunch);

        // Grabs Firebase Instance and set database location
        auth = FirebaseAuth.getInstance();
        String location = "lunch/";

        DatabaseReference root = FirebaseDatabase.getInstance().getReference(location);

        tvBack2 = findViewById(R.id.tv_back2);

        // Sets the layout manager to organize RecyclerView positions
        lunchView = findViewById(R.id.lunch_view);
        lunchView.setHasFixedSize(true);
        lunchView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new LunchAdapter(this , list);
        lunchView.setAdapter(adapter);

        tvBack2.setOnClickListener(view -> {
            startActivity(new Intent(LunchActivity.this, MainActivity.class));
        });

        // Method to take all information from list and show it on RecyclerViews
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    LunchUpload model = dataSnapshot.getValue(LunchUpload.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
