//package com.nickli.scheduleapp.emailStaff;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.nickli.scheduleapp.R;
//
//public class EmailAdd extends AppCompatActivity {
//
//    // Define Variables
//    Button btnAddEmail;
//    TextView tvCancel;
//
//    FirebaseAuth auth;
//    StorageReference mStorageRef;
//    DatabaseReference mDatabaseRef;
//
//    TextInputEditText number;
//    TextInputEditText staffName;
//    TextInputEditText staffEmail;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_email_add);
//
//        number = findViewById(R.id.number);
//        staffName = findViewById(R.id.staffName);
//        staffEmail = findViewById(R.id.staffEmail);
//
//        btnAddEmail = findViewById(R.id.btnAddEmail);
//        tvCancel = findViewById(R.id.tvCancel);
//
//        btnAddEmail.setOnClickListener(view -> {
//            emailInfoUpload();
//        });
//
//        tvCancel.setOnClickListener(view -> {
//            startActivity(new Intent(EmailAdd.this, EmailActivity.class));
//        });
//
//    }
//
//    private void emailInfoUpload() {
//        auth = FirebaseAuth.getInstance();
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//
//        String number_ = number.getText().toString();
//        String staff_Name = staffName.getText().toString();
//        String staff_Email = staffEmail.getText().toString();
//
//        //checkFilePermissions();
//        FirebaseUser user = auth.getCurrentUser();
//        String userID = user.getUid();
//
//        String location_database = "staff/";
//        //String location_storage = "images/users/" + userID + "/schedule/";
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference(location_database);
//
//        if (TextUtils.isEmpty(number_)) {
//            number.setError("Period cannot be empty");
//            number.requestFocus();
//        } else if (TextUtils.isEmpty(staff_Name)){
//            staffName.setError("Class Name cannot be empty");
//            staffName.requestFocus();
////        } else if (TextUtils.isEmpty(staff_Email)){
////            staffEmail.setError("Class Day cannot be empty");
////            staffEmail.requestFocus();
//        } else {
//            String[] staffNameArray = staff_Name.split(" ");
//            staff_Email = staffNameArray[staffNameArray.length-1] + "_" + staffNameArray[staffNameArray.length-2] + "@ausd.us";
//            staff_Email = staff_Email.toLowerCase();
//
//            EmailUpload upload = new EmailUpload(staff_Name, staff_Email, "1");
//            String uploadId = number_;
//            assert uploadId != null;
//            mDatabaseRef.child(uploadId).setValue(upload);
//            number.setText(null);
//            staffName.setText(null);
//            staffEmail.setText(null);
//        }
//    }
//}
