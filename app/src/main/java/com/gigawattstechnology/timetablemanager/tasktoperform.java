package com.gigawattstechnology.timetablemanager;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.ColumnInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class tasktoperform extends Fragment {
    RecyclerView recyclerView;
    Context context;
    TaskAdapter taskAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tasktoperform, container, false);
        context=view.getContext();
        recyclerView=view.findViewById(R.id.tasktoperformrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        AppDatabase appDatabase=AppDatabase.getDbInstance(context);
        List<User> data=appDatabase.userDao().getAllUsers();
        //taskAdapter.notifyDataSetChanged();
        taskAdapter=new TaskAdapter(context,data);
        recyclerView.setAdapter(taskAdapter);
        return view;
    }
}