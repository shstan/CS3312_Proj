package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.DialogInterface;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chris on 9/13/2017.
 */

public class ScheduleWalkActivity extends AppCompatActivity {

    private ListView packLeadersList;
    Button btnSchedule, pbutton;
    private DatabaseReference db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_walk);
        db = FirebaseDatabase.getInstance().getReference();
        btnSchedule = (Button) findViewById(R.id.finish_schedule_walk);

        displayAvailablePackLeaders();

        // Confirmation message onclick
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: send Date, Time, Pack Leader info to dialog

                Walk newWalk = new Walk("Time Horton Hears a Who", "Billy May Cyrus", "Matt in the Hat", new Date(2017, 9, 3, 11, 30, 0), new Date(2017, 9, 3, 12, 30,0), new Location("Test"),new Location("Test"), 90.1);

                displayAlertDialog(newWalk);
            }
        });

    }

    // TODO: handle onclick of calendar date -> updates availablePackLeaders
    // TODO: display both pack leader name and walk time in listView
    public void displayAvailablePackLeaders() {
        packLeadersList = (ListView) findViewById(R.id.pack_leaders_list);

        List<String> availablePackLeaders = new ArrayList<String>();
        availablePackLeaders.add("foo");
        availablePackLeaders.add("bar");
        availablePackLeaders.add("hey");
        availablePackLeaders.add("sup");

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                availablePackLeaders );

        packLeadersList.setAdapter(arrayAdapter);
    }

    // TODO: handle onclick selection of pack leader

    public void displayAlertDialog(final Walk newWalk) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ScheduleWalkActivity.this);
        alertBuilder.setTitle("Confirm dog walk?")
                .setMessage("Walk: " + newWalk.getStartTime() + " with " + newWalk.getWalkerID())
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        // TODO: go to landing page? or payment page?
                        String walkID = db.child("walks").push().getKey();
                        db.child("walks").child(walkID).setValue(newWalk);
                    }})
                .setNegativeButton(android.R.string.no, null);

        AlertDialog dialog = alertBuilder.create();
        dialog.show();

        pbutton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.BLUE);
    }
}
