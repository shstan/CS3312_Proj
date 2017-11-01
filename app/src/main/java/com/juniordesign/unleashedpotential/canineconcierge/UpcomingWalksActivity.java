package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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


/**
 * UpcomingWalksActivity - Dog Owner Portal
 *
 * displays all of the dog owner's upcoming walks with ability to cancel
 */
public class UpcomingWalksActivity extends AppCompatActivity {

    private FirebaseDatabase db;
    private FirebaseAuth auth;

    private ListView upcomingWalksList;
    private ArrayList<Walk> walks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_walks);

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        upcomingWalksList = (ListView) findViewById(R.id.upcoming_walks_list);
        walks = new ArrayList<Walk>();

        //Fetch walk data
        db.getReference("walks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleWalk : dataSnapshot.getChildren()) {
                    Walk addWalk = singleWalk.getValue(Walk.class);
                    addWalk.setWalkID(singleWalk.getKey());

                    if(!addWalk.isCompleted() && auth.getCurrentUser().getUid().equals(addWalk.getClientID())) {
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
    }
}

/**
 * upcomingListAdapter
 *
 * used to create fill and add functionality to list view on upcomingWalksActivity
 */
class upcomingListAdapter extends BaseAdapter {

    Context context;
    private DatabaseReference db;
    ArrayList<Walk> data;

    private static LayoutInflater inflater = null;

    private final SimpleDateFormat displayWalkDate = new SimpleDateFormat("MM/dd/yyyy");
    private SimpleDateFormat displayWalkTimeStart = new SimpleDateFormat("hh:mm");
    private SimpleDateFormat displayWalkTimeEnd = new SimpleDateFormat("hh:mm");

    /**
     * Constructor of upcomingListAdapter
     *
     * @param context Activity ListView is found within
     * @param data Walk data to display in ListView
     */
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
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.upcoming_walk_row, null);

        final Walk thisWalk = data.get(position);

        //Set TextViews within ListView rows
        TextView dogName = (TextView) vi.findViewById(R.id.upcoming_dog_name);
        dogName.setText(thisWalk.getDogName() + " " + displayWalkDate.format(thisWalk.getStartTime()));

        TextView walkDate = (TextView) vi.findViewById(R.id.upcoming_leader_name);
        walkDate.setText("" + thisWalk.getWalkerID());

        TextView endTime = (TextView) vi.findViewById(R.id.upcoming_window);
        endTime.setText("" + displayWalkTimeStart.format(thisWalk.getStartTime()) + " - " + displayWalkTimeEnd.format(thisWalk.getEndTime()));

        Button deleteBtn = (Button)vi.findViewById(R.id.delete_btn);

        //Confirmation dialogue on walk cancellation
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(view.getRootView().getContext());
                alertBuilder.setTitle("Are you sure?")
                        .setMessage("Cancel Upcoming Walk: " + thisWalk.getDogName() + " " + displayWalkDate.format(thisWalk.getStartTime()))
                        .setPositiveButton("Cancel Walk", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteWalk(position);
                            }})
                        .setNegativeButton("Nevermind", null);

                AlertDialog dialog = alertBuilder.create();
                dialog.show();

                Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                nbutton.setTextColor(Color.BLUE);
            }
        });

        return vi;
    }

    /**
     * Deletes walk from database after user confirmation
     *
     * @param position walk position in data array to be deleted
     */
    public void deleteWalk(int position) {
        db.child("walks").child(data.get(position).getWalkID()).removeValue();
        data.clear();
    }
}