package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chris on 9/13/2017.
 */

public class ScheduleWalkActivity extends AppCompatActivity {

    private ListView packLeadersList;
    Button btnSchedule, pbutton;
    private DatabaseReference db;
    private CalendarView cal;
    private int dayOfWeek;
    private int selMonth;
    private int selYr;
    private int selDay;
    private String currDay;
    private String selectedLdr;
    private HashMap pack_leaders;
    private HashMap walks;
    private FirebaseAuth auth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(ScheduleWalkActivity.this, LoginActivity.class));
        } else {
            System.out.println(auth.getCurrentUser().getUid());
        }
        setContentView(R.layout.schedule_walk);
        db = FirebaseDatabase.getInstance().getReference();
        btnSchedule = (Button) findViewById(R.id.finish_schedule_walk);
        cal = (CalendarView) findViewById(R.id.calendarView);
        pack_leaders = new HashMap();
        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("pack_leaders").orderByChild("pack_leaders").getRef();
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    pack_leaders = new HashMap((Map) dataSnapshot.getValue());

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Failed");
            }
        });
        DatabaseReference schedWalks = FirebaseDatabase.getInstance().getReference("walks");
        schedWalks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    walks = new HashMap((Map) dataSnapshot.getValue());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        long date = cal.getDate();
        cal.setMinDate(date);
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                dayOfWeek = getDay(year, month, dayOfMonth);
                currDay = getStringDayOfWeek(dayOfWeek);
                System.out.println(currDay);
                selDay = dayOfMonth;
                selMonth = month;
                selYr = year;
                displayAvailablePackLeaders();
            }
        });
        cal.setFirstDayOfWeek(2);
        //displayAvailablePackLeaders();
        packLeadersList = (ListView) findViewById(R.id.pack_leaders_list);
        packLeadersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                selectedLdr = (String)packLeadersList.getItemAtPosition(pos);
            }
        });

        //dayOfWeek = new Integer(0);
        // Confirmation message onclick
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: send Date, Time, Pack Leader info to dialog
                String ldrId = selectedLdr.substring(0, selectedLdr.indexOf(":"));
                String startTime = selectedLdr.substring(selectedLdr.indexOf(":") + 2, selectedLdr.indexOf("-"));
                String endTime = selectedLdr.substring(selectedLdr.indexOf("-"));
                Walk newWalk = new Walk(ldrId, auth.getCurrentUser().getUid(), "Matt in the Hat", new GregorianCalendar(selYr, selMonth, selDay, Integer.parseInt(startTime), 0).getTime(), new GregorianCalendar(selYr, selMonth, selDay, Integer.parseInt(endTime), 0, 0).getTime(), new Location("Test"),new Location("Test"), 90.1);

                displayAlertDialog(newWalk);
            }
        });

    }

    // TODO: handle onclick of calendar date -> updates availablePackLeaders
    // TODO: display both pack leader name and walk time in listView
    public String getStringDayOfWeek(int day) {
        switch(day) {
            case 0:
                return "Sunday";
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            default:
                return null;
        }
    }
    public int getDay(int year, int month, int dayOfMonth) {
        int day = (year % 100)/4;
        day += dayOfMonth;
        int m;
        if (month < 2) {
            m = 11 + month;
        } else {
            m = month - 1;
        }
        double m2 = (((double) m) * 2.6) - 0.2;
        int m3 = (int)Math.floor(m2);
        day = day + m3 - (2 * (year / 100)) + (year % 100) + (year / 400);
        return (day % 7);
    }
    public void displayAvailablePackLeaders() {
        List<String> availablePackLeaders = new ArrayList<String>();
        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        availablePackLeaders = getAvailablePackLeaders(currDay);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                availablePackLeaders);

        packLeadersList.setAdapter(arrayAdapter);
    }
    public void removeScheduled(ArrayList<Long> hrs) {
        Set<String> keys = walks.keySet();
        for (String key : keys) {
            HashMap walk = (HashMap)walks.get(key);

        }
    }
    public ArrayList<String> getAvailablePackLeaders(String day) {
        ArrayList<String> ret = new ArrayList<>();
        Set<String> keys = pack_leaders.keySet();
        System.out.println(keys.size());
        for (String key : (Set<String>)keys) {
            HashMap ldr = (HashMap) pack_leaders.get(key);
            if (ldr.get(day) == null) {
                Toast.makeText(getApplicationContext(), "Select a date with available pack leaders!", Toast.LENGTH_SHORT).show();
            } else {
                ArrayList<Long> hrs = (ArrayList<Long>)ldr.get(day);
                removeScheduled(hrs);
                for (long hr : hrs) {
                    String s = String.format("%s %s: ", ldr.get("firstName"), ldr.get("lastName"));
                    System.out.println("added name to string");
                    if (hr == 12) {
                        s = s + (hr) + "-" + ((hr % 12) + 1);
                    } else {
                        s = s + (hr % 12) + "-" + ((hr % 12) + 1);
                    }
                    System.out.println(s);
                    ret.add(s);
                }
            }
        }
        return ret;
    }
    // TODO: handle onclick selection of pack leader

    public void displayAlertDialog(final Walk newWalk) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        String startTime = selectedLdr.substring(selectedLdr.indexOf(":") + 2, selectedLdr.indexOf("-"));
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ScheduleWalkActivity.this);
        alertBuilder.setTitle("Confirm dog walk?")
                .setMessage("Walk: @" + newWalk.getStartTime() + " with " + newWalk.getWalkerID())
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        startActivity(new Intent(ScheduleWalkActivity.this, PaymentActivity.class));
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