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

    //private final View.OnClickListener mOnClickListener = new MyOnClickListener();
    private ArrayList<EmailUpload> mList;
    private Context context;

    private RecyclerViewClickListener listener;

    public StaffClickListener staffClickListener;

    public interface StaffClickListener {
        void selectedStaff(EmailUpload emailUpload);
    }

    public EmailAdapter(Context context, ArrayList<EmailUpload> mList, StaffClickListener staffClickListener ){//RecyclerViewClickListener listener){
        this.context = context;
        this.mList = mList;
        this.staffClickListener = staffClickListener;
        //this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.email_item, parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        EmailUpload emailUpload = mList.get(position);
        String name = emailUpload.getStaffName();
        String email = emailUpload.getStaffEmail();
        holder.staffName.setText(name);
        holder.staffEmail.setText(email);

        //holder.staffName.setText(mList.get(position).getStaffName());
        //staffEmail.setText(mList.get(position).getStaffEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staffClickListener.selectedStaff(emailUpload);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView staffName;
        private TextView staffEmail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            staffName = itemView.findViewById(R.id.staffName);
            staffEmail = itemView.findViewById(R.id.staffEmail);

            itemView.setOnClickListener(this);
        }

        //DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        //Query query = ref.child("staff").orderByChild("staffEmail").equalTo(staffEmail.getText().toString());

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public void filterList(ArrayList<EmailUpload> filteredList) {
        mList = filteredList;
        notifyDataSetChanged();
    }

}
