package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by chris on 9/13/2017.
 */

public class CompletedWalksActivity extends AppCompatActivity {

    private ListView completedWalksList;
    private DatabaseReference db;
    private ArrayList<Walk> walks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_walks);
        walks = new ArrayList<Walk>();
        db = FirebaseDatabase.getInstance().getReference();
        Log.d("TEST", "ABOVE DB CALL");

        db.child("walks").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        Log.d("TEST", "IN DB CALL");
                        for(DataSnapshot singleWalk : dataSnapshot.getChildren()) {
                            Walk addWalk = singleWalk.getValue(Walk.class);
                            if( addWalk.isCompleted()) {
                                walks.add(addWalk);
                            }
                        }
                        completedWalksList = (ListView) findViewById(R.id.completed_walks_list);
                        Log.d("TEST", walks.toString());
                        completedWalksList.setAdapter(new completedListAdapter(CompletedWalksActivity.this, walks));

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });


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
    ArrayList<Walk> data;

    private static LayoutInflater inflater = null;

    public completedListAdapter(Context context, ArrayList<Walk> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
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
        Walk thisWalk = data.get(position);
        // TODO: Set TextViews with proper data by index/position of dataset
        TextView dogName = (TextView) vi.findViewById(R.id.dog_name);
        dogName.setText("Fido" + position);

        TextView walkDate = (TextView) vi.findViewById(R.id.walk_date);
        walkDate.setText("" + thisWalk.getStartTime().getDate());

        TextView startTime = (TextView) vi.findViewById(R.id.start_time);
        startTime.setText("" + thisWalk.getStartTime().getTime());

        TextView endTime = (TextView) vi.findViewById(R.id.end_time);
        endTime.setText("" + thisWalk.getEndTime().getTime());

        TextView distance = (TextView) vi.findViewById(R.id.distance);
        distance.setText("" + thisWalk.getDistance());

        TextView packLeader = (TextView) vi.findViewById(R.id.leader_name);
        packLeader.setText("" + thisWalk.getWalkerID());

        return vi;
    }
}
