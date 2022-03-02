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

    //private final View.OnClickListener mOnClickListener = new MyOnClickListener();
    private ArrayList<ClubUpload> mList;
    private Context context;

    private RecyclerViewClickListener listener;

    public ClubAdapter(Context context, ArrayList<ClubUpload> mList, RecyclerViewClickListener listener){
        this.context = context;
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.email_item, parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.clubName.setText("Club: " + mList.get(position).getClubName());
        holder.clubStaff.setText("Staff(s): " + mList.get(position).getClubStaff());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView clubName;
        TextView clubStaff;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            clubName = itemView.findViewById(R.id.staffName);
            clubStaff = itemView.findViewById(R.id.staffEmail);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public void filterList(ArrayList<ClubUpload> filteredList) {
        mList = filteredList;
        notifyDataSetChanged();
    }

}
