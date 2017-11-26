package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by christy on 10/10/2017.
 */

/**
 * PackLeaderMainActivity - Pack Leader Portal
 * Directs user to set available hours, view completed walks, view upcoming walks
 * Sign Out button
 */
public class PackLeaderMainActivity extends AppCompatActivity {

    private Button btnSetHours, btnCompletedWalks, btnUpcomingWalks, btnStartWalk, btnLogout;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packleader_activity_main);

        btnSetHours = (Button) findViewById(R.id.go_to_set_hours);
        btnCompletedWalks = (Button) findViewById(R.id.go_to_completed_walks);
        btnUpcomingWalks = (Button) findViewById(R.id.go_to_upcoming_walks);
        btnStartWalk = (Button) findViewById(R.id.start_walk);
        btnLogout = (Button) findViewById(R.id.sign_out);

        auth = FirebaseAuth.getInstance();

        //Button Listeners
        btnSetHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PackLeaderMainActivity.this, PackLeaderSetHoursActivity.class));
            }
        });

        btnCompletedWalks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PackLeaderMainActivity.this, PackLeaderCompletedWalksActivity.class));
            }
        });

        btnUpcomingWalks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PackLeaderMainActivity.this, PackLeaderUpcomingWalksActivity.class));
            }
        });

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
