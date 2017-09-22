package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 9/13/2017.
 */

public class ScheduleWalk extends AppCompatActivity {

    private ListView packLeadersList;
    Button btnSchedule, pbutton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_walk);

        btnSchedule = (Button) findViewById(R.id.finish_schedule_walk);

        displayAvailablePackLeaders();

        // Confirmation message onclick
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: send Date, Time, Pack Leader info to dialog

                displayAlertDialog();
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

    public void displayAlertDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ScheduleWalk.this);
        alertBuilder.setTitle("Confirm dog walk?")
                .setMessage("<Date> <Time> <Pack Leader>")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        // TODO: go to landing page? or payment page?
                        // Answer: Payment.
                    }})
                .setNegativeButton(android.R.string.no, null);

        AlertDialog dialog = alertBuilder.create();
        dialog.show();

        pbutton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.BLUE);
    }
}
