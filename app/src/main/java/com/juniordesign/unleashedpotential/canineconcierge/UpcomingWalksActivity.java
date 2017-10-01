package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chris on 9/13/2017.
 */

public class UpcomingWalksActivity extends AppCompatActivity {

    private ListView upcomingWalksList;
    private FirebaseDatabase db;
    private FirebaseAuth auth;
    private DatabaseReference dbr;
    private HashMap data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_walks);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        dbr = db.getReference("walks");
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data = new HashMap((Map) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //TODO iterate thru data to find the walks with users id (obtainable from auth.getCurrentUser().getUid())
        upcomingWalksList = (ListView) findViewById(R.id.upcoming_walks_list);

        upcomingWalksList.setAdapter(new upcomingListAdapter(this, new String[] { "data1",
                "data2" }));

    }
    public void cancelWalk() {

    }
}

class upcomingListAdapter extends BaseAdapter {

    Context context;

    // TODO: convert to list of walk data
    String[] data;

    private static LayoutInflater inflater = null;

    public upcomingListAdapter(Context context, String[] data) {
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
        dogName.setText("Fido");

        TextView walkDate = (TextView) vi.findViewById(R.id.upcoming_leader_name);
        walkDate.setText(data[position]);

        TextView startTime = (TextView) vi.findViewById(R.id.upcoming_walk_date);
        startTime.setText("10/5/17");

        TextView endTime = (TextView) vi.findViewById(R.id.upcoming_window);
        endTime.setText("11 am - 12 pm");

        return vi;
    }
}