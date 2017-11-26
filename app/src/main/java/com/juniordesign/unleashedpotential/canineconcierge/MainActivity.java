package com.juniordesign.unleashedpotential.canineconcierge;

/**
 * Created by christy on 9/13/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * MainActivity - Dog Owner Portal
 * Directs user to schedule walk, view completed walks, view upcoming walks
 * Sign Out button
 */
public class MainActivity extends AppCompatActivity  {

    private Button btnScheduleWalk, btnCompletedWalks, btnUpcomingWalks, btnLogout;
    private FirebaseAuth auth;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScheduleWalk = (Button) findViewById(R.id.go_to_schedule_walk);
        btnCompletedWalks = (Button) findViewById(R.id.go_to_completed_walks);
        btnUpcomingWalks = (Button) findViewById(R.id.go_to_upcoming_walks);
        btnLogout = (Button) findViewById(R.id.sign_out);

        //Check for authenticated user
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        if (auth.getCurrentUser() == null) {
            //If no user, launch LoginActivity
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        } else {
            //Check if user is pack leader
            Query plQuery = db.getReference("pack_leaders")
                    .orderByChild("email").equalTo(auth.getCurrentUser().getEmail());

            plQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot singlePackLeader : dataSnapshot.getChildren()) {
                        //User is a pack leader - direct to PackLeaderMainActivity
                        startActivity(new Intent(MainActivity.this, PackLeaderMainActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //TODO: Handle database error
                }
            });
        }

        //Button Listeners
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

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
