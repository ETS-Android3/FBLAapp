package com.nickli.scheduleapp.extraActivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nickli.scheduleapp.R;

import java.util.ArrayList;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.MyViewHolder> {

    // Define variables
    private ArrayList<ClubUpload> mList;
    private Context context;

    private RecyclerViewClickListener listener;

    // Obtains list of information from database using ClubAdapter
    public ClubAdapter(Context context, ArrayList<ClubUpload> mList, RecyclerViewClickListener listener){
        this.context = context;
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    // Takes list obtained from database and displays it onto a view holder (RecyclerView)
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.email_item, parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    // Method to setText for the view holder and gets the information from the list
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.clubName.setText("Club: " + mList.get(position).getClubName());
        holder.clubStaff.setText("Staff(s): " + mList.get(position).getClubStaff());
    }

    @Override
    // Gets size of list to see how many views are needed
    public int getItemCount() {
        return mList.size();
    }

    // Class to implement RecyclerView
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView clubName;
        TextView clubStaff;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            clubName = itemView.findViewById(R.id.staffName);
            clubStaff = itemView.findViewById(R.id.staffEmail);
        }

    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    // Method to change list to only filtered ones
    public void filterList(ArrayList<ClubUpload> filteredList) {
        mList = filteredList;
        notifyDataSetChanged();
    }

}
