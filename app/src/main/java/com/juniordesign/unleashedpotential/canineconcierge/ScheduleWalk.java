package com.juniordesign.unleashedpotential.canineconcierge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 9/13/2017.
 */

public class ScheduleWalk extends AppCompatActivity {

    private ListView packLeadersList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_walk);

        packLeadersList = (ListView) findViewById(R.id.pack_leaders_list);

        // Instantiating an array list
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

    // TODO: update pack leader list on calendar selection

    // TODO: display both pack leader name and walk time in listView

    // TODO: handle onclick of pack leader
    
    // TODO: handle onclick of schedule walk button

}
