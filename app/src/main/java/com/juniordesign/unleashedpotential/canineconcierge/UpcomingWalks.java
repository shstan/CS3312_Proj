package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by chris on 9/13/2017.
 */

public class UpcomingWalks extends AppCompatActivity {

    private ListView upcomingWalksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_walks);

        upcomingWalksList = (ListView) findViewById(R.id.upcoming_walks_list);

        upcomingWalksList.setAdapter(new upcomingListAdapter(this, new Walk[] {
                // TODO: need to populate the data - Stan
        }));

    }
}

class upcomingListAdapter extends BaseAdapter {

    Context context;

    // TODO: convert to list of walk data
    Walk[] data;

    private static LayoutInflater inflater = null;

    public upcomingListAdapter(Context context, Walk[] data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.upcoming_walk_row, null);

        // TODO: Set TextViews with proper data from dataset
        TextView dogName = (TextView) vi.findViewById(R.id.upcoming_dog_name);
        dogName.setText(data[position].getDogName());

        TextView walkDate = (TextView) vi.findViewById(R.id.upcoming_leader_name);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM / dd / yyyy");
        walkDate.setText(dateFormat.format(data[position].getStartTime()));

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        TextView startTime = (TextView) vi.findViewById(R.id.upcoming_walk_date);
        startTime.setText(timeFormat.format(data[position].getStartTime()));

        TextView endTime = (TextView) vi.findViewById(R.id.upcoming_window);
        endTime.setText(timeFormat.format(data[position].getEndTime()));

        return vi;
    }
}