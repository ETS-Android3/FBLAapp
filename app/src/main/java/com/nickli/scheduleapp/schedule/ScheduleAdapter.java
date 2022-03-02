package com.nickli.scheduleapp.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nickli.scheduleapp.R;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    private ArrayList<ScheduleUpload> mList;
    private Context context;

    public ScheduleClickListener scheduleClickListener;

    public interface ScheduleClickListener {
        void selectedSchedule(ScheduleUpload scheduleUpload);
    }

    public ScheduleAdapter(Context context , ArrayList<ScheduleUpload> mList, ScheduleAdapter.ScheduleClickListener scheduleClickListener){

        this.context = context;
        this.mList = mList;
        this.scheduleClickListener = scheduleClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.schedule_item, parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ScheduleUpload scheduleUpload = mList.get(position);
        String period = scheduleUpload.getClassPeriod();
        String name = scheduleUpload.getClassName();
        String time = scheduleUpload.getClassTime();
        String days = scheduleUpload.getClassDate();
        holder.classPeriod.setText("Period: " + period);
        holder.className.setText("Class: " + name);
        holder.classTime.setText("Time: " + time);
        holder.classDay.setText("Days: " + days);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleClickListener.selectedSchedule(scheduleUpload);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView classPeriod;
        TextView className;
        TextView classTime;
        TextView classDay;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            classPeriod = itemView.findViewById(R.id.tvClassPeriod);
            className = itemView.findViewById(R.id.tvClassName);
            classTime = itemView.findViewById(R.id.tvClassTime);
            classDay = itemView.findViewById(R.id.tvClassDay);
        }
    }
}
