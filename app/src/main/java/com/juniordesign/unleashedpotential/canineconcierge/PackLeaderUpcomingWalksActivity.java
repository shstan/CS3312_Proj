package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Context;
import android.content.DialogInterface;
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
 * Created by christy on 10/10/2017.
 */

/**
 * PackLeaderUpcomingWalksActivity - Pack Leader Portal
 *
 * displays all of the pack leader's upcoming walks with ability to cancel
 */
public class PackLeaderUpcomingWalksActivity extends AppCompatActivity{

    private ListView upcomingWalksList;
    private FirebaseDatabase db;
    private FirebaseAuth auth;
    private DatabaseReference dbr;
    private ArrayList<Walk> walks;
    private String packLeaderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.packleader_upcoming_walks);

        auth = FirebaseAuth.getInstance();
        walks = new ArrayList<Walk>();
        db = FirebaseDatabase.getInstance();
        dbr = db.getReference("walks");
        upcomingWalksList = (ListView) findViewById(R.id.packleader_upcoming_walks_list);

        //Get Pack Leaders display name to use in fetching their walk data
        db.getReference("pack_leaders").orderByChild("email").equalTo(auth.getCurrentUser().getEmail())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot singlePackLeader : dataSnapshot.getChildren()) {
                            PackLeader p_lead = singlePackLeader.getValue(PackLeader.class);
                            packLeaderName = p_lead.getFirstName() + " " + p_lead.getLastName();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //TODO: Handle database error
                    }
                });

        //Fetch all walk data
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleWalk : dataSnapshot.getChildren()) {
                    Walk addWalk = singleWalk.getValue(Walk.class);
                    addWalk.setWalkID(singleWalk.getKey());
                    if(!addWalk.isCompleted() && addWalk.getWalkerID().equals(packLeaderName)) {
                        walks.add(addWalk);
                    }
                }

                upcomingWalksList.setAdapter(new packLeaderUpcomingListAdapter(PackLeaderUpcomingWalksActivity.this, walks));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

/**
 * packLeaderUpcomingListAdapter
 *
 * used to create fill and add functionality to list view on PackLeaderUpcomingWalksActivity
 */
class packLeaderUpcomingListAdapter extends BaseAdapter {

    Context context;
    private DatabaseReference db;
    ArrayList<Walk> data;

    private static LayoutInflater inflater = null;

    /**
     * Constructor of packLeaderUpcomingListAdapter
     *
     * @param context Activity ListView is found within
     * @param data Walk data to display in ListView
     */
    public packLeaderUpcomingListAdapter(Context context, ArrayList<Walk> data) {
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

        final SimpleDateFormat displayWalkDate = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat displayWalkTimeStart = new SimpleDateFormat("hh:mm");
        SimpleDateFormat displayWalkTimeEnd = new SimpleDateFormat("hh:mm");

        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.upcoming_walk_row, null);

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
