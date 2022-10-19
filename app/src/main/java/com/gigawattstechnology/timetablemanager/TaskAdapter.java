package com.gigawattstechnology.timetablemanager;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.RecyclerViewHolder>{
    List<User> data;
    Context context;
    public TaskAdapter(Context context,List<User> data){
        this.context=context;
        this.data=data;
    }
    @Override
    public int getItemViewType(final int position) {
        return R.layout.taskcontainer;
    }
    @NonNull
    @Override
    public TaskAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(viewType, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.RecyclerViewHolder holder, int position) {

        final User user=data.get(position);
        holder.taskname.setText(user.taskname);
        holder.description.setText(user.description);
        holder.date.setText(user.date);
        holder.starttime.setText(user.starttime);
        holder.endtime.setText(user.endtime);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView taskname,description,date,starttime,endtime;
        Button complete,delete,postpone;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            taskname=itemView.findViewById(R.id.taskname);
            description=itemView.findViewById(R.id.taskdescription);
            date=itemView.findViewById(R.id.date);
            starttime=itemView.findViewById(R.id.starttime);
            endtime=itemView.findViewById(R.id.endtime);
            complete=itemView.findViewById(R.id.complete);
            delete=itemView.findViewById(R.id.delete);
            postpone=itemView.findViewById(R.id.postpone);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppDatabase appDatabase=AppDatabase.getDbInstance(context);
                    User user=new User();
                    user.taskname=taskname.getText().toString();
                    user.description=description.getText().toString();
                    user.date=date.getText().toString();
                    user.starttime=starttime.getText().toString();
                    user.endtime=endtime.getText().toString();
                    appDatabase.userDao().delete(user);
                    MediaPlayer mediaPlayer=MediaPlayer.create(context,R.raw.delete);
                    mediaPlayer.start();
                }
            });
            postpone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MediaPlayer mediaPlayer=MediaPlayer.create(context,R.raw.postpone);
                    mediaPlayer.start();
                }
            });
        }
    }
}
