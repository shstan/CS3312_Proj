package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 9/13/2017.
 */

public class CompletedWalks extends AppCompatActivity {

    private ListView completedWalksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_walks);

        completedWalksList = (ListView) findViewById(R.id.completed_walks_list);

        completedWalksList.setAdapter(new completedListAdapter(this, new String[] { "data1",
                "data2" }));

        /*List<String> availablePackLeaders = new ArrayList<String>();
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

        completedWalksList.setAdapter(arrayAdapter);*/

    }
}

class completedListAdapter extends BaseAdapter {

    Context context;

    // TODO: convert to list of walk data
    String[] data;

    private static LayoutInflater inflater = null;

    public completedListAdapter(Context context, String[] data) {
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
            vi = inflater.inflate(R.layout.completed_walk_row, null);

        // TODO: Set TextViews with proper data
        TextView dogName = (TextView) vi.findViewById(R.id.dog_name);
        dogName.setText("Fido");

        TextView walkDate = (TextView) vi.findViewById(R.id.walk_date);
        walkDate.setText(data[position]);

        TextView startTime = (TextView) vi.findViewById(R.id.start_time);
        startTime.setText("12:55 pm");

        TextView endTime = (TextView) vi.findViewById(R.id.end_time);
        endTime.setText("1:45 pm");

        TextView distance = (TextView) vi.findViewById(R.id.distance_label);
        distance.setText("1.1 miles");

        TextView packLeader = (TextView) vi.findViewById(R.id.leader_name);
        packLeader.setText("John");

        return vi;
    }
}
