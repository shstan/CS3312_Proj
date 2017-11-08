package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * ProfileActionsActivity
 *
 * ***Code not in use*** will be used when user profile page created
 * Will add side navigation to get to account page
 */
public class ProfileActionsActivity extends AppCompatActivity {
    private Button btnLogout, btnPasswordReset, btnProfile;
    private FirebaseAuth auth;
    private String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_actions);

        btnLogout = (Button) findViewById(R.id.sign_out);
        btnPasswordReset = (Button) findViewById(R.id.pass_reset);
        btnProfile = (Button) findViewById(R.id.profile);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(ProfileActionsActivity.this, LoginActivity.class));
            finish();
        } else {
            userEmail = auth.getCurrentUser().getEmail();

        }
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(ProfileActionsActivity.this, LoginActivity.class));
                finish();
            }
        });

        //Broken for some god damn reason...
//        btnProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ProfileActionsActivity.this, ProfileActivity.class));
//                finish();
//            }
//        });
        btnPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(userEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Email sent successfully", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }


}
