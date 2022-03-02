package com.nickli.scheduleapp.lunch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nickli.scheduleapp.R;

import java.util.ArrayList;

public class LunchAdapter extends RecyclerView.Adapter<LunchAdapter.MyViewHolder> {

    private ArrayList<LunchUpload> mList;
    private Context context;

    public LunchAdapter(Context context , ArrayList<LunchUpload> mList){

        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.lunch_item, parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.dayOfWeek.setText(mList.get(position).getDayOfWeek());
        holder.food.setText(mList.get(position).getFood());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dayOfWeek;
        TextView food;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            dayOfWeek = itemView.findViewById(R.id.dayOfWeek);
            food = itemView.findViewById(R.id.food);
        }
    }
}
