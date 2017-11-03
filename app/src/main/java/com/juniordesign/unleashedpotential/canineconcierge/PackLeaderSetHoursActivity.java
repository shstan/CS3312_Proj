package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by chris on 10/10/2017.
 */

public class PackLeaderSetHoursActivity extends AppCompatActivity {

    PackLeaderSetHoursActivity.HourListAdapter dataAdapter = null;

    //TODO: refresh hours view based on day selected
    private Button monday_butt, tuesday_butt, wednesday_butt, thursday_butt, friday_butt, saturday_butt, sunday_butt;
    String selectedDay = "Monday";

    boolean dateChange = false;

    private Map<String, ArrayList<Integer>> timeSelections = new HashMap<>();
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference dbr;
    private String uID;
    private Map<String, Button> buttonMap;


    //Need to be changed?
//    private ArrayList<Integer> availHourSlots


    /**
     * The method used for toggling the selections.
     * @param day the day selected.
     * @param hourCode The hourcode clicked.
     * @return If that time is set as avaliable slot, or not.
     */
    private boolean ToggleSelection(String day, int hourCode) {
        if (day == null) {
            Toast.makeText(getApplicationContext(),
                    "Select a date first, please!",
                    Toast.LENGTH_SHORT);
//            Systwm.out.println
            return false;
        }
        if(timeSelections.containsKey(day)) {
            if (timeSelections.get(day).contains(hourCode)) {
                timeSelections.get(day).remove(hourCode);
                return false;
            } else {
                timeSelections.get(day).add(hourCode);
            }
        } else {
            ArrayList<Integer> newHourSet = new ArrayList<>();
            newHourSet.add(hourCode);
            timeSelections.put(day, newHourSet);
        }
        return true;
    }


    private void indicateDaySelection(String day) {


        if (day != null) {
            for (Button a : buttonMap.values()) {
                a.setBackgroundColor(Color.TRANSPARENT);
            }
            buttonMap.get(day).setBackgroundColor(Color.YELLOW);
            dateChange = true;
        }
//        dbr.child()


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packleader_sethours);
        auth = FirebaseAuth.getInstance();
        uID = auth.getCurrentUser().getUid();
        db = FirebaseDatabase.getInstance();
        dbr = db.getReference("pack_leaders").child(uID);




        //displayListView();

        monday_butt = (Button) findViewById(R.id.monday);
        tuesday_butt = (Button) findViewById(R.id.tuesday);
        wednesday_butt = (Button) findViewById(R.id.wednesday);
        thursday_butt = (Button) findViewById(R.id.thursday);
        friday_butt = (Button) findViewById(R.id.friday);
        saturday_butt = (Button) findViewById(R.id.saturday);
        sunday_butt = (Button) findViewById(R.id.sunday);

        displayListView();
        monday_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Monday";
                indicateDaySelection(selectedDay);
            }
        });
        tuesday_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Tuesday";
                indicateDaySelection(selectedDay);
            }
        });
        wednesday_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Wednesday";
                indicateDaySelection(selectedDay);
            }
        });
        thursday_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Thursday";
                indicateDaySelection(selectedDay);
            }
        });
        friday_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Friday";
                indicateDaySelection(selectedDay);
            }
        });
        saturday_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Saturday";
                indicateDaySelection(selectedDay);
            }
        });
        sunday_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Sunday";
                indicateDaySelection(selectedDay);
            }
        });


        buttonMap = new HashMap<>();
        buttonMap.put("Monday", monday_butt);
        buttonMap.put("Tuesday", tuesday_butt);
        buttonMap.put("Wednesday", wednesday_butt);
        buttonMap.put("Thursday", thursday_butt);
        buttonMap.put("Friday", friday_butt);
        buttonMap.put("Saturday", saturday_butt);
        buttonMap.put("Sunday", sunday_butt);
        indicateDaySelection("Monday");
        displayListView();

    }

    public void finishSetHours(View view) {
        startActivity(new Intent(PackLeaderSetHoursActivity.this, PackLeaderMainActivity.class));
        finish();
    }

    private void displayListView() {

        //TODO: get list of hours from db



        final ArrayList<Hour> hourList = new ArrayList<Hour>();
        Hour hourTest = new Hour("10","10am - 11am",false);
        hourList.add(hourTest);
        hourTest = new Hour("11","11am - 12pm",false);
        hourList.add(hourTest);
        hourTest = new Hour("12","12pm - 1pm",false);
        hourList.add(hourTest);
        hourTest = new Hour("13","1pm - 2pm",false);
        hourList.add(hourTest);
        hourTest = new Hour("14","2pm - 3pm",false);
        hourList.add(hourTest);
        hourTest = new Hour("15","3pm - 4pm",false);
        hourList.add(hourTest);
        hourTest = new Hour("16","4pm - 5pm",false);
        hourList.add(hourTest);

        //create an ArrayAdapter from the String Array
        dataAdapter = new PackLeaderSetHoursActivity.HourListAdapter(this,
                R.layout.hour_selection_row, hourList);
        final ListView listView = (ListView) findViewById(R.id.hour_selection);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Hour hour = (Hour) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + hour.getName(),
                        Toast.LENGTH_LONG).show();

//                if (ToggleSelection(selectedDay, hour.getCode())) {
//                    hour.setSelected(true);
////                    listView.getChildAt(position).setBackgroundColor(Color.BLUE);
//                } else {
//                    hour.setSelected(false);
////                    listView.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
//                }

            }
        });

        dbr.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Integer> availHourSlots;
                if (selectedDay != null) {
                    if (dataSnapshot.child(selectedDay) != null) {
                        HashMap<String, ArrayList<Integer>> pl
                                = (HashMap<String, ArrayList<Integer>>) dataSnapshot.getValue();
                        System.out.println(pl);
                        availHourSlots = pl.get(selectedDay);
                        int index = 0;
                        for(Hour hour : hourList) {
                            hour.setSelected(false);
//                            ((CheckBox)listView.getChildAt(index)).setSelected(false);
                            if (availHourSlots.contains(Integer.parseInt(hour.getCode()))) {
                                hour.setSelected(true);
//                                ((CheckBox)listView.getChildAt(index)).setSelected(true);
                            }
                            dataAdapter.notifyDataSetChanged();
                        }
                    }
                }

//                CheckBox cb;
//
//                for(int i=0; i<listView.getChildCount();i++)
//                {
//                    cb = (CheckBox)listView.getChildAt(i);
//                    cb.setChecked(false);
//                    Integer.parseInt(cb.getTag())
//                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),
                        "Failed to Load Data!\n" + databaseError.getDetails(),
                        Toast.LENGTH_SHORT);
            }
        });



    }

    class HourListAdapter extends ArrayAdapter<Hour> {

        private ArrayList<Hour> hourList;

        public HourListAdapter(Context context, int textViewResourceId,
                               ArrayList<Hour> hourList) {
            super(context, textViewResourceId, hourList);
            this.hourList = new ArrayList<Hour>();
            this.hourList.addAll(hourList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            PackLeaderSetHoursActivity.HourListAdapter.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.hour_selection_row, null);

                holder = new PackLeaderSetHoursActivity.HourListAdapter.ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);
                //TODO: FInalize
                if (hourList.get(position).isSelected()) {
                    holder.name.setChecked(true);
                } else {
                    holder.name.setChecked(false);
                }

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        //TODO: save selection to db

//                        mDatabase


                        //1. uncheck all the boxes if the date is changed.
                        if (dateChange) {
//                            ViewGroup vg = (ViewGroup) v.getParent();
//                            for (int i = 0; i < vg.getChildCount(); i++) {
//                                CheckBox ckb = (CheckBox) vg.getChildAt(i);
//                                ckb.setChecked((timeSelections.get(selectedDay) != null) && timeSelections.get(selectedDay)
//                                        .contains(((Hour) (ckb.getTag())).getCode()));
//
//                            }
                        }

                        dateChange = false;

                        //2. Use the timeSelection




                        CheckBox cb = (CheckBox) v ;
                        if (selectedDay == null) {
                            ((CheckBox) v).setChecked(false);
                            Toast.makeText(getApplicationContext(),
                                    "Select a date first, please!",
                                    Toast.LENGTH_SHORT).show();
                        }

                        Hour hour = (Hour) cb.getTag();
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() +
                                        " is " + cb.isChecked() + "\nIndex:" + position,
                                Toast.LENGTH_LONG).show();
                        hour.setSelected(cb.isChecked());
                        ToggleSelection(selectedDay, Integer.parseInt(hour.getCode()));
                        indicateDaySelection(selectedDay);
                        System.out.println("timeSelections = " + timeSelections);
                    }
                });

            }
            else {
                holder = (PackLeaderSetHoursActivity.HourListAdapter.ViewHolder) convertView.getTag();
            }

            Hour hour = hourList.get(position);
            holder.code.setText(hour.getCode());
            holder.name.setText(hour.getName());
            holder.name.setChecked(hour.isSelected());
            holder.name.setTag(hour);

            return convertView;

        }

    }
}
