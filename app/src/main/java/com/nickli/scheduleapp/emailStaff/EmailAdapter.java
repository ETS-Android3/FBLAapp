package com.nickli.scheduleapp.emailStaff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nickli.scheduleapp.R;

import java.util.ArrayList;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.MyViewHolder> {

    // Define variables
    private ArrayList<EmailUpload> mList;
    private Context context;

    private RecyclerViewClickListener listener;

    public StaffClickListener staffClickListener;

    public interface StaffClickListener {
        void selectedStaff(EmailUpload emailUpload);
    }

    public EmailAdapter(Context context, ArrayList<EmailUpload> mList, StaffClickListener staffClickListener ){
        this.context = context;
        this.mList = mList;
        this.staffClickListener = staffClickListener;
    }

    @NonNull
    @Override
    // Takes context information and place it in RecyclerView
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.email_item, parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    // Retrieve information from Realtime Database
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Gets current position of the RecyclerView
        EmailUpload emailUpload = mList.get(position);
        // Use the position to find the name and email accordingly
        String name = emailUpload.getStaffName();
        String email = emailUpload.getStaffEmail();
        // Set the TextViews as the obtained information
        holder.staffName.setText(name);
        holder.staffEmail.setText(email);

        // Set an OnClickListener for the RecyclerViews
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staffClickListener.selectedStaff(emailUpload);
            }
        });
    }

    @Override
    // Finds size of the list to see how many RecyclerViews needed
    public int getItemCount() {
        return mList.size();
    }

    // RecyclerView
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView staffName;
        private TextView staffEmail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            staffName = itemView.findViewById(R.id.staffName);
            staffEmail = itemView.findViewById(R.id.staffEmail);

            // Checks if user has clicked on RecyclerView
            itemView.setOnClickListener(this);
        }

        @Override
        // When RecyclerView is clicked
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    // Changes the RecyclerViews shown according to the search bar
    public void filterList(ArrayList<EmailUpload> filteredList) {
        mList = filteredList;
        notifyDataSetChanged();
    }

}
