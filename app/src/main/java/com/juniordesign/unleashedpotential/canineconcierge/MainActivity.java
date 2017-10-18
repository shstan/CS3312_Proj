package com.juniordesign.unleashedpotential.canineconcierge;

/**
 * Created by chris on 9/13/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {
    private Button btnScheduleWalk, btnCompletedWalks, btnUpcomingWalks;

    //private FirebaseAuth auth;
    //private String userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScheduleWalk = (Button) findViewById(R.id.go_to_schedule_walk);
        btnCompletedWalks = (Button) findViewById(R.id.go_to_completed_walks);
        btnUpcomingWalks = (Button) findViewById(R.id.go_to_upcoming_walks);

        btnScheduleWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScheduleWalkActivity.class));
            }
        });

        btnCompletedWalks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CompletedWalksActivity.class));
            }
        });

        btnUpcomingWalks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UpcomingWalksActivity.class));
            }
        });



//        buttonPay.setOnClickListener(this);

    }

    public void sendMessage(View view) {
        Intent pay = new Intent(this, PayActivity.class);
        startActivity(pay);
    }





}
