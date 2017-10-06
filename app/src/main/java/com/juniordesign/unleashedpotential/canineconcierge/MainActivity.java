package com.juniordesign.unleashedpotential.canineconcierge;

/**
 * Created by chris on 9/13/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  {
    private Button btnScheduleWalk, btnCompletedWalks, btnUpcomingWalks, btnLogout;

    private FirebaseAuth auth;
    //private String userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScheduleWalk = (Button) findViewById(R.id.go_to_schedule_walk);
        btnCompletedWalks = (Button) findViewById(R.id.go_to_completed_walks);
        btnUpcomingWalks = (Button) findViewById(R.id.go_to_upcoming_walks);
        btnLogout = (Button) findViewById(R.id.sign_out);

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

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
//        buttonPay.setOnClickListener(this);

    }

    public void sendMessage(View view) {
        Intent pay = new Intent(this, PayActivity.class);
        startActivity(pay);
    }





}
