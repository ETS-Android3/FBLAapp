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
    private RecyclerView emailView;
    Button tvBack3;
    TextView staffEmail;
    //Button addEmail;

    private EmailAdapter adapter;
    private ArrayList<EmailUpload> list;
    FirebaseAuth auth;

    private EmailAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        auth = FirebaseAuth.getInstance();
        String location = "staff/";

        DatabaseReference root = FirebaseDatabase.getInstance().getReference(location);

        tvBack3 = findViewById(R.id.tv_back3);
        staffEmail = findViewById(R.id.staffEmail);

        //addEmail = findViewById(R.id.addEmail);

//        addEmail.setOnClickListener(view -> {
//            startActivity(new Intent(EmailActivity.this, EmailAdd.class));
//        });

        EditText searchStaff = findViewById(R.id.searchStaff);
        searchStaff.addTextChangedListener(new TextWatcher() {
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

        //setOnClickListener();
        emailView = findViewById(R.id.email_view);
        emailView.setHasFixedSize(true);
        emailView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new EmailAdapter(this , list, this::selectedStaff);//listener);
        emailView.setAdapter(adapter);

        tvBack3.setOnClickListener(view -> {
            startActivity(new Intent(EmailActivity.this, MainActivity.class));
        });

//        email.setOnClickListener(view -> {
//            EmailStaff();
//        });

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

    private void setOnClickListener() {
        listener = new EmailAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position){
                //startActivity(new Intent(Intent.ACTION_SEND, Uri.parse("nickli.1540489@gmail.com")));
//                Intent emailIntent = new Intent(Intent.ACTION_SEND);
//                String email = list.get(position).getStaffEmail();
//                emailIntent.setData(Uri.parse("mailto:"+email));
//                try {
//                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//                    finish();
//                    Log.i("Finished sending email...", "");
//                } catch (android.content.ActivityNotFoundException ex) {
//                    Toast.makeText(EmailActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
//                }
                String email = list.get(position).getStaffEmail();
                String[] emails = email.split(",");
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, emails);

                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an email client: "));
            }
        };
    }

    private void EmailStaff() {
        String email = staffEmail.getText().toString();
        startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse(email)));
    }

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
    public void selectedStaff(EmailUpload emailUpload){
        //Toast.makeText(this, "Selected Staff: "+emailUpload.getStaffEmail(), Toast.LENGTH_SHORT).show();
        String email = emailUpload.getStaffEmail();
        String[] emails = email.split(",");
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, emails);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client: "));
    }

}
