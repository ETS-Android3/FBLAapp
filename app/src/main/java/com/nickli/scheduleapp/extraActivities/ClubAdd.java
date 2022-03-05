//package com.nickli.scheduleapp.extraActivities;
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
//import java.io.IOException;
//
//public class ClubAdd extends AppCompatActivity {
//
//    Button btnAddClub;
//    TextView tvCancel2;
//
//    FirebaseAuth auth;
//    StorageReference mStorageRef;
//    DatabaseReference mDatabaseRef;
//
//    TextInputEditText clubName;
//    TextInputEditText clubStaff;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_club_add);
//
//        clubName = findViewById(R.id.clubName);
//        clubStaff = findViewById(R.id.clubStaff);
//
//        btnAddClub = findViewById(R.id.btnAddClub);
//        tvCancel2 = findViewById(R.id.tvCancel2);
//
//        btnAddClub.setOnClickListener(view -> {
//            try {
//                emailInfoUpload();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        tvCancel2.setOnClickListener(view -> {
//            startActivity(new Intent(ClubAdd.this, ClubActivity.class));
//        });
//
//    }
//
//    private void emailInfoUpload() throws IOException {
//        auth = FirebaseAuth.getInstance();
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//
//        String location_database = "clubs/";
//        //String location_storage = "images/users/" + userID + "/schedule/";
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference(location_database);
//
////        //File clubNamesArr = new File("clubs.txt");
////        FileReader clubNamesArr = new FileReader("clubs.txt");
////        //DataInputStream clubNamesArr = new DataInputStream(getAssets().open(String.format("clubs.txt")));
////        Scanner scan = new Scanner(clubNamesArr);
////        while(scan.hasNextLine()){
////            String clubName = scan.nextLine();
////
////            ClubUpload upload = new ClubUpload(clubName, "");
////            String uploadId = clubName;
////            assert uploadId != null;
////            mDatabaseRef.child(uploadId).setValue(upload);
////        }
////        clubNamesArr.close();
//
//
//        String club_Name = clubName.getText().toString();
//        String club_Staff = clubStaff.getText().toString();
//
//        //checkFilePermissions();
//        FirebaseUser user = auth.getCurrentUser();
//        String userID = user.getUid();
//
//        if (TextUtils.isEmpty(club_Name)) {
//            clubName.setError("Club Name cannot be empty");
//            clubName.requestFocus();
//        } else {
//            ClubUpload upload = new ClubUpload(club_Name, club_Staff);
//            String uploadId = club_Name;
//            assert uploadId != null;
//            mDatabaseRef.child(uploadId).setValue(upload);
//            clubName.setText(null);
//            clubStaff.setText(null);
//        }
//
//    }
//}
