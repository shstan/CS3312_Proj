package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 10/10/2017.
 */

/**
 * PackLeaderSetHoursActivity - Pack Leader portal
 *
 * Pack leader can set available hours for dog walks
 */
public class PackLeaderSetHoursActivity extends AppCompatActivity {

    private Button monday_but, tuesday_but, wednesday_but, thursday_but, friday_but, saturday_but, sunday_but;
    private Map<String, Button> buttonMap = new HashMap<>();

    private String[] daysOfWeek = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    String selectedDay = "Monday";

    PackLeaderSetHoursActivity.HourListAdapter dataAdapter = null;
    public HashMap<String, ArrayList<Hour>> timeSelections = new HashMap<>();

    private String uID;
    private DatabaseReference dbrPackLeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packleader_sethours);

        uID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbrPackLeader = FirebaseDatabase.getInstance().getReference("pack_leaders").child(uID).getRef();

        //Set button onclick listeners
        monday_but = (Button) findViewById(R.id.monday);
        tuesday_but = (Button) findViewById(R.id.tuesday);
        wednesday_but = (Button) findViewById(R.id.wednesday);
        thursday_but = (Button) findViewById(R.id.thursday);
        friday_but = (Button) findViewById(R.id.friday);
        saturday_but = (Button) findViewById(R.id.saturday);
        sunday_but = (Button) findViewById(R.id.sunday);

        monday_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Monday";
                indicateDaySelection(selectedDay);
            }
        });
        tuesday_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Tuesday";
                indicateDaySelection(selectedDay);
            }
        });
        wednesday_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Wednesday";
                indicateDaySelection(selectedDay);
            }
        });
        thursday_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Thursday";
                indicateDaySelection(selectedDay);
            }
        });
        friday_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Friday";
                indicateDaySelection(selectedDay);
            }
        });
        saturday_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Saturday";
                indicateDaySelection(selectedDay);
            }
        });
        sunday_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDay = "Sunday";
                indicateDaySelection(selectedDay);
            }
        });

        //ButtonMap is used for setting the button's background color on selection
        buttonMap.put("Monday", monday_but);
        buttonMap.put("Tuesday", tuesday_but);
        buttonMap.put("Wednesday", wednesday_but);
        buttonMap.put("Thursday", thursday_but);
        buttonMap.put("Friday", friday_but);
        buttonMap.put("Saturday", saturday_but);
        buttonMap.put("Sunday", sunday_but);

        //Create filler array for listAdapter
        ArrayList<Hour> hourList = new ArrayList<Hour>();
        hourList = fillHourArray(hourList);

        for (String day: daysOfWeek) {
            timeSelections.put(day, cloneList(hourList));
        }

        //Fill timeSelection Map with user's current available hours
        dbrPackLeader.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    HashMap p_lead = new HashMap((Map) dataSnapshot.getValue());
                    for (String day: daysOfWeek) {
                        if (p_lead.containsKey(day)) {
                            ArrayList<Long> hrs = (ArrayList<Long>)p_lead.get(day);
                            for (Long hr: hrs) {
                                if(hr != null) timeSelections.get(day).get((int)(long)hr).setSelected(true);
                            }
                        }
                    }

                    //this call is inside databaseReference because must be synchronous
                    indicateDaySelection(selectedDay);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO: Handle database error
            }
        });
    }

    /**
     * Clone list of hours for HashMap used to display in List Adapter
     * @param list ArrayList<Hour> to be cloned
     * @return clone of ArrayList<Hour>
     */
    public static ArrayList<Hour> cloneList(ArrayList<Hour> list) {
        ArrayList<Hour> clone = new ArrayList<Hour>(list.size());
        for (Hour item : list) clone.add(new Hour(item.getCode(), item.getName(), item.isSelected()));
        return clone;
    }

    /**
     * Save available hours to database and redirect user to MainActivity screen
     */
    public void finishSetHours(View view) {
        //List of hours to fill and save to db by day of week
        ArrayList<Integer> hrsList = new ArrayList<Integer>();

        for (String day: daysOfWeek) {
            hrsList.clear();
            for (Hour hr: timeSelections.get(day)) {
                if (hr.isSelected()) {
                    hrsList.add(Integer.parseInt(hr.getCode()));
                }
            }
            //Save available hours to db
            dbrPackLeader.child(day).setValue(hrsList);
        }

        startActivity(new Intent(PackLeaderSetHoursActivity.this, PackLeaderMainActivity.class));
        finish();
    }

    /**
     * Send selectedDay's list info to the list adapter
     */
    private void displayListView() {
        //create an ArrayAdapter from the String Array
        dataAdapter = new PackLeaderSetHoursActivity.HourListAdapter(this,
                R.layout.hour_selection_row, timeSelections.get(selectedDay));

        // Assign adapter to hour_selection ListView
        final ListView listView = (ListView) findViewById(R.id.hour_selection);
        listView.setAdapter(dataAdapter);
    }

    /**
     * Highlights which day is selected
     * @param day to be selected
     */
    private void indicateDaySelection(String day) {
        if (day != null) {
            for (Button a : buttonMap.values()) {
                a.setBackgroundColor(Color.parseColor("#ccffffff"));
            }
            buttonMap.get(day).setBackgroundColor(Color.GRAY);
        }
        displayListView();
    }

    /**
     * Create filler array for ListAdapter
     * @param hourList Array to fill
     * @return ArrayList full of all 24 time slots
     */
    private static ArrayList<Hour> fillHourArray(ArrayList<Hour> hourList) {
        Hour hourTest = new Hour("0","12am - 1am",false);
        hourList.add(hourTest);
        hourTest = new Hour("1","1am - 2am",false);
        hourList.add(hourTest);
        hourTest = new Hour("2","2am - 3am",false);
        hourList.add(hourTest);
        hourTest = new Hour("3","3am - 4am",false);
        hourList.add(hourTest);
        hourTest = new Hour("4","4am - 5am",false);
        hourList.add(hourTest);
        hourTest = new Hour("5","5am - 6am",false);
        hourList.add(hourTest);
        hourTest = new Hour("6","6am - 7am",false);
        hourList.add(hourTest);
        hourTest = new Hour("7","7am - 8am",false);
        hourList.add(hourTest);
        hourTest = new Hour("8","8am - 9am",false);
        hourList.add(hourTest);
        hourTest = new Hour("9","9am - 10am",false);
        hourList.add(hourTest);
        hourTest = new Hour("10","10am - 11am",false);
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
        hourTest = new Hour("17","5pm - 6pm",false);
        hourList.add(hourTest);
        hourTest = new Hour("18","6pm - 7pm",false);
        hourList.add(hourTest);
        hourTest = new Hour("19","7pm - 8pm",false);
        hourList.add(hourTest);
        hourTest = new Hour("20","8pm - 9pm",false);
        hourList.add(hourTest);
        hourTest = new Hour("21","9pm - 10pm",false);
        hourList.add(hourTest);
        hourTest = new Hour("20","8pm - 9pm",false);
        hourList.add(hourTest);
        hourTest = new Hour("21","9pm - 10pm",false);
        hourList.add(hourTest);
        hourTest = new Hour("22","10pm - 11pm",false);
        hourList.add(hourTest);
        hourTest = new Hour("23","11pm - 12am",false);
        hourList.add(hourTest);

        return hourList;
    }


    /**
     * HourListAdapter
     *
     * Creates checkbox list for hour_selection
     * Sends data back to PackLeaderSetHoursActivity
     */
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

                //Set status of checkboxes based on data
                if (hourList.get(position).isSelected()) {
                    holder.name.setChecked(true);
                } else {
                    holder.name.setChecked(false);
                }

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Hour hour = (Hour) cb.getTag();
                        hour.setSelected(cb.isChecked());
                    }
                });

            } else {
                holder = (PackLeaderSetHoursActivity.HourListAdapter.ViewHolder) convertView.getTag();
            }

            //Set Text of checkbox list
            Hour hour = hourList.get(position);
            holder.code.setText(hour.getCode());
            holder.name.setText(hour.getName());
            holder.name.setChecked(hour.isSelected());
            holder.name.setTag(hour);

            return convertView;

        }

    }
}
