//package com.nickli.scheduleapp.emailStaff;
//
//import android.util.Log;
//import android.view.View;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.nickli.scheduleapp.R;
//import com.nickli.scheduleapp.emailStaff.EmailUpload;
//
//import java.util.ArrayList;
//
//public class MyOnClickListener implements View.OnClickListener {
//    RecyclerView mRecyclerView;
//    private ArrayList<EmailUpload> mList;
//
//    @Override
//    public void onClick(View v) {
//        mRecyclerView.findViewById(R.id.email_view);
//        int itemPosition = mRecyclerView.getChildLayoutPosition(v);
//        EmailUpload item = mList.get(itemPosition);
//        Log.d("Item: ", item.toString());
//    }
//}
