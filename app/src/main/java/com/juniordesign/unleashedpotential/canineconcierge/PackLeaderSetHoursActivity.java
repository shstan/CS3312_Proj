package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by chris on 10/10/2017.
 */

public class PackLeaderSetHoursActivity extends AppCompatActivity {

    PackLeaderSetHoursActivity.HourListAdapter dataAdapter = null;

    //TODO: refresh hours view based on day selected
    String selectedDay = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packleader_sethours);

        displayListView();
    }

    public void finishSetHours(View view) {
        startActivity(new Intent(PackLeaderSetHoursActivity.this, PackLeaderMainActivity.class));
        finish();
    }

    private void displayListView() {

        //TODO: get list of hours from db
        ArrayList<Hour> hourList = new ArrayList<Hour>();
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
        hourTest = new Hour("14","4pm - 5pm",false);
        hourList.add(hourTest);

        //create an ArrayAdapter from the String Array
        dataAdapter = new PackLeaderSetHoursActivity.HourListAdapter(this,
                R.layout.hour_selection_row, hourList);
        ListView listView = (ListView) findViewById(R.id.hour_selection);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Hour hour = (Hour) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + hour.getName(),
                        Toast.LENGTH_LONG).show();
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
        public View getView(int position, View convertView, ViewGroup parent) {

            PackLeaderSetHoursActivity.HourListAdapter.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.hour_selection_row, null);

                holder = new PackLeaderSetHoursActivity.HourListAdapter.ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        //TODO: save selection to db
                        CheckBox cb = (CheckBox) v ;
                        Hour hour = (Hour) cb.getTag();
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() +
                                        " is " + cb.isChecked(),
                                Toast.LENGTH_LONG).show();
                        hour.setSelected(cb.isChecked());
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
