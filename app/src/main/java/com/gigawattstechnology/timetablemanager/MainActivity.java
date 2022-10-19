package com.gigawattstechnology.timetablemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
TabLayout tabLayout;
TabItem tasktoperform,postponedtask,goalstatus;
ViewPager vpager;
Button newtask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        tabLayout=findViewById(R.id.tablayout);
        newtask=findViewById(R.id.newtask);
        tasktoperform=findViewById(R.id.tasktoperform);
        postponedtask=findViewById(R.id.postponedtask);
        goalstatus=findViewById(R.id.goalstatus);
        vpager=findViewById(R.id.vpager);
        PageAdapter pageAdapter=new PageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        pageAdapter.addFragment(new tasktoperform(),"Task to perform");
        pageAdapter.addFragment(new postponedtask(),"Postponed task");
        pageAdapter.addFragment(new goalstatus(),"Goal status");
        vpager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(vpager);
        newtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, newtask.class);
                startActivity(intent);
            }
        });
    }
}