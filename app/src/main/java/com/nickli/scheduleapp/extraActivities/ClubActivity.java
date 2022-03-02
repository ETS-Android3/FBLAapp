package com.nickli.scheduleapp.extraActivities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class ClubActivity extends AppCompatActivity {
    private RecyclerView clubView;
    Button tvBack4;
    Button importClubs;

    private ClubAdapter adapter;
    private ArrayList<ClubUpload> list;
    FirebaseAuth auth;

    private ClubAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        auth = FirebaseAuth.getInstance();
        String location = "clubs/";

        DatabaseReference root = FirebaseDatabase.getInstance().getReference(location);

        tvBack4 = findViewById(R.id.tv_back4);
        //importClubs = findViewById(R.id.importClubs);

        EditText searchClubs = findViewById(R.id.searchClubs);
        searchClubs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });

        setOnClickListener();
        clubView = findViewById(R.id.club_view);
        clubView.setHasFixedSize(true);
        clubView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new ClubAdapter(this , list, listener);
        clubView.setAdapter(adapter);

        tvBack4.setOnClickListener(view -> {
            startActivity(new Intent(ClubActivity.this, MainActivity.class));
        });

//        importClubs.setOnClickListener(view -> {
//            startActivity(new Intent(ClubActivity.this, ClubAdd.class));
//        });

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ClubUpload model = dataSnapshot.getValue(ClubUpload.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setOnClickListener() {
        listener = new ClubAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position){

            }
        };
    }

    private void filter(String text) {
        ArrayList<ClubUpload> filteredList = new ArrayList<>();

        for (ClubUpload item : list) {
            if (item.getClubName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }

}
