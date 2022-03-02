package com.nickli.scheduleapp.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nickli.scheduleapp.MainActivity;
import com.nickli.scheduleapp.R;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity implements ScheduleAdapter.ScheduleClickListener{
    private RecyclerView scheduleView;
    Button addSchedule;
    TextView tvBack1;

    private ScheduleAdapter adapter;
    private ArrayList<ScheduleUpload> list;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String userID = user.getUid();
        String location = "users/" + userID + "/schedule/";

        DatabaseReference root = FirebaseDatabase.getInstance().getReference(location);
        setContentView(R.layout.activity_schedule);

        tvBack1 = findViewById(R.id.tv_back1);
        addSchedule = findViewById(R.id.btnAdd);

        scheduleView = findViewById(R.id.schedule_view);
        scheduleView.setHasFixedSize(true);
        scheduleView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new ScheduleAdapter(this , list, this::selectedSchedule);
        scheduleView.setAdapter(adapter);

        tvBack1.setOnClickListener(view -> {
            startActivity(new Intent(ScheduleActivity.this, MainActivity.class));
        });

        addSchedule.setOnClickListener(view -> {
            startActivity(new Intent(ScheduleActivity.this, ScheduleAdd.class));
        });

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ScheduleUpload model = dataSnapshot.getValue(ScheduleUpload.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void selectedSchedule(ScheduleUpload scheduleUpload) {
        String period = scheduleUpload.getClassPeriod();
        startActivity(new Intent(ScheduleActivity.this, ScheduleRemind.class).putExtra("data", scheduleUpload));
    }
}
