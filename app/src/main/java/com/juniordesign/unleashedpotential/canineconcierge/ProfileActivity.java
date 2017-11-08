package com.juniordesign.unleashedpotential.canineconcierge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

/**
 * ProfileActivity
 *
 * ***Code not in use*** will be used when user profile page created
 */
public class ProfileActivity extends Activity {
    private EditText fullName, address, phoneNumber,email;
    private Button btnPasswordReset, btnBack;
    private FirebaseAuth auth;
    private DatabaseReference db;
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Get Firebase Auth and DB references;
        auth = FirebaseAuth.getInstance();

        //Get UserID to grab user data from db
        userID = auth.getCurrentUser().getUid();


        fullName = (EditText) findViewById(R.id.full_name);
        address = (EditText) findViewById(R.id.address);
        phoneNumber = (EditText) findViewById(R.id.phone_number);
        email = (EditText) findViewById(R.id.email);
        btnPasswordReset = (Button) findViewById(R.id.reset_pass);
        final String userEmail = auth.getCurrentUser().getEmail();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

        btnPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(userEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("EMAIL", "Email Sent");
                                }
                            }
                        });
            }
        });
    }

}
