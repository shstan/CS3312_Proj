package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by chris on 10/10/2017.
 */

public class PackLeaderMainActivity extends AppCompatActivity {

    private Button btnSetHours, btnCompletedWalks, btnUpcomingWalks, btnLogout;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packleader_activity_main);

        btnSetHours = (Button) findViewById(R.id.go_to_set_hours);
        btnCompletedWalks = (Button) findViewById(R.id.go_to_completed_walks);
        btnUpcomingWalks = (Button) findViewById(R.id.go_to_upcoming_walks);
        btnLogout = (Button) findViewById(R.id.sign_out);

        btnSetHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, ScheduleWalkActivity.class));
            }
        });

        btnCompletedWalks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, CompletedWalksActivity.class));
            }
        });

        btnUpcomingWalks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, UpcomingWalksActivity.class));
            }
        });

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            //startActivity(new Intent(MainActivity.this, LoginActivity.class));
            //finish();
        }
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(PackLeaderMainActivity.this, LoginActivity.class));
                finish();
            }
        });

    }
}
