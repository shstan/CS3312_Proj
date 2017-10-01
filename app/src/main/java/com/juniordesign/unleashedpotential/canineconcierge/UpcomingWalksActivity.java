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

import java.util.ArrayList;

/**
 * Created by chris on 9/13/2017.
 */

public class UpcomingWalksActivity extends AppCompatActivity {

    private ListView upcomingWalksList;
    private FirebaseDatabase db;
    private FirebaseAuth auth;
    private DatabaseReference dbr;
    private ArrayList<Walk> walks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_walks);
        auth = FirebaseAuth.getInstance();
        walks = new ArrayList<Walk>();
        db = FirebaseDatabase.getInstance();
        dbr = db.getReference("walks");
        upcomingWalksList = (ListView) findViewById(R.id.upcoming_walks_list);
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleWalk : dataSnapshot.getChildren()) {
                    Walk addWalk = singleWalk.getValue(Walk.class);
                    if(!addWalk.isCompleted()) {
                        walks.add(addWalk);
                    }
                }
                upcomingWalksList.setAdapter(new upcomingListAdapter(UpcomingWalksActivity.this, walks));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //TODO iterate thru data to find the walks with users id (obtainable from auth.getCurrentUser().getUid())




    }
    public void cancelWalk() {

    }
}

class upcomingListAdapter extends BaseAdapter {

    Context context;

    // TODO: convert to list of walk data
    ArrayList<Walk> data;

    private static LayoutInflater inflater = null;

    public upcomingListAdapter(Context context, ArrayList<Walk> data) {
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
        Walk thisWalk = data.get(position);
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.upcoming_walk_row, null);

        // TODO: Set TextViews with proper data from dataset
        TextView dogName = (TextView) vi.findViewById(R.id.upcoming_dog_name);
        dogName.setText("Fido" + position);

        TextView walkDate = (TextView) vi.findViewById(R.id.upcoming_leader_name);
        walkDate.setText("" + thisWalk.getStartTime().getDate());

        TextView startTime = (TextView) vi.findViewById(R.id.upcoming_walk_date);
        startTime.setText("" + thisWalk.getStartTime().getTime());

        TextView endTime = (TextView) vi.findViewById(R.id.upcoming_window);
        endTime.setText("" + thisWalk.getEndTime().getTime());        dogName.setText("Fido" + position);




        return vi;
    }
}