package com.example.testapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.entities.User;


import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<User> list;

    public UserAdapter(List<User> list){
        this.list = list;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.fullNameTextView.setText(list.get(position).getFullName());
        holder.positionTextView.setText(list.get(position).getPosition());
        holder.workInMonthTextView.setText("Рабочих часов: " + list.get(position).getWorkHoursInMonth());
        holder.workedOutHoursTextView.setText("Часов отработано: "+ list.get(position).getWorkedOutHours());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView fullNameTextView;
        public TextView positionTextView;
        public TextView workInMonthTextView;
        public TextView workedOutHoursTextView;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            fullNameTextView = view.findViewById(R.id.fullNameTextView);
            positionTextView = view.findViewById(R.id.positionTextView);
            workInMonthTextView = view.findViewById(R.id.workInMonthTextView);
            workedOutHoursTextView = view.findViewById(R.id.workedOutHoursTextView);
        }
    }
}
