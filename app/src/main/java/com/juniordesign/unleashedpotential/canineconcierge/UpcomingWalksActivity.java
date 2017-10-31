package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by christy on 9/13/2017.
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
                    addWalk.setWalkID(singleWalk.getKey());
                    if(!addWalk.isCompleted()) {
                        walks.add(addWalk);
                    }
                }
                upcomingWalksList.setAdapter(new upcomingListAdapter(UpcomingWalksActivity.this, walks));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //TODO: Handle database error
            }
        });
        //TODO iterate thru data to find the walks with users id (obtainable from auth.getCurrentUser().getUid())
    }
}

class upcomingListAdapter extends BaseAdapter {

    Context context;
    private DatabaseReference db;
    ArrayList<Walk> data;

    private static LayoutInflater inflater = null;

    public upcomingListAdapter(Context context, ArrayList<Walk> data) {
        this.context = context;
        this.data = data;
        db = FirebaseDatabase.getInstance().getReference();
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Walk thisWalk = data.get(position);

        SimpleDateFormat displayWalkDate = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat displayWalkTimeStart = new SimpleDateFormat("hh:mm");
        SimpleDateFormat displayWalkTimeEnd = new SimpleDateFormat("hh:mm");

        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.upcoming_walk_row, null);

        TextView dogName = (TextView) vi.findViewById(R.id.upcoming_dog_name);
        dogName.setText(thisWalk.getDogName() + " " + displayWalkDate.format(thisWalk.getStartTime()));

        TextView walkDate = (TextView) vi.findViewById(R.id.upcoming_leader_name);
        walkDate.setText("" + thisWalk.getWalkerID());

        TextView endTime = (TextView) vi.findViewById(R.id.upcoming_window);
        endTime.setText("" + displayWalkTimeStart.format(thisWalk.getStartTime()) + " - " + displayWalkTimeEnd.format(thisWalk.getEndTime()));

        Button deleteBtn = (Button)vi.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Confirmation dialogue
                data.remove(position);
                db.child("walks").child(thisWalk.getWalkID()).removeValue();
                notifyDataSetChanged();
            }
        });

        return vi;
    }
}