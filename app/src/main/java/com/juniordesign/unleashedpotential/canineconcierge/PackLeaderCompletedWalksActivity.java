package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by christy on 10/10/2017.
 */

public class PackLeaderCompletedWalksActivity extends AppCompatActivity {

    private ListView completedWalksList;
    private DatabaseReference db;
    private ArrayList<Walk> walks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.packleader_completed_walks);

        walks = new ArrayList<Walk>();
        db = FirebaseDatabase.getInstance().getReference();

        db.child("walks").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //TODO: Get Completed walks by pack leader
                        for(DataSnapshot singleWalk : dataSnapshot.getChildren()) {
                            Walk addWalk = singleWalk.getValue(Walk.class);
                            if(addWalk.isCompleted()) {
                                //walks.add(addWalk);
                            }
                        }
                        completedWalksList = (ListView) findViewById(R.id.packleader_completed_walks_list);
                        completedWalksList.setAdapter(new packLeaderCompletedListAdapter(PackLeaderCompletedWalksActivity.this, walks));
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //TODO: handle database error
                    }
                });

    }
}

class packLeaderCompletedListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Walk> data;

    private static LayoutInflater inflater = null;

    public packLeaderCompletedListAdapter(Context context, ArrayList<Walk> data) {
        this.context = context;
        this.data = data;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final SimpleDateFormat displayWalkDate = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat displayWalkTimeStart = new SimpleDateFormat("hh:mm");
        SimpleDateFormat displayWalkTimeEnd = new SimpleDateFormat("hh:mm");

        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.completed_walk_row, null);
        final Walk thisWalk = data.get(position);

        TextView dogName = (TextView) vi.findViewById(R.id.dog_name);
        dogName.setText(thisWalk.getDogName() + " " + displayWalkDate.format(thisWalk.getStartTime()));

        TextView startTime = (TextView) vi.findViewById(R.id.start_time);
        startTime.setText("" + displayWalkTimeStart.format(thisWalk.getStartTime()));

        TextView endTime = (TextView) vi.findViewById(R.id.end_time);
        endTime.setText("" + displayWalkTimeEnd.format(thisWalk.getEndTime()));

        TextView distance = (TextView) vi.findViewById(R.id.distance);
        distance.setText(Double.toString(thisWalk.getDistance()) + " mi");

        TextView packLeader = (TextView) vi.findViewById(R.id.leader_name);
        packLeader.setText("" + thisWalk.getWalkerID());

        Button mapBtn = (Button)vi.findViewById(R.id.view_map);

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(view.getRootView().getContext());
                alertBuilder.setTitle(thisWalk.getDogName() + " " + displayWalkDate.format(thisWalk.getStartTime()))
                        .setNeutralButton("Close", null);
                //TODO: Sprint 4 - Insert map
                AlertDialog dialog = alertBuilder.create();
                dialog.show();
            }
        });

        return vi;
    }
}
