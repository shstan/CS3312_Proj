package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * LoginActivity
 * Handles user authentication
 * Directs user to proper portal - Dog Owner or Pack Leader
 * Screen displayed on application load if user is not authenticated
 *
 */
public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnLogin;

    private FirebaseAuth auth;
    private FirebaseDatabase db;
    private DatabaseReference dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail =  (EditText) findViewById(R.id.email_address);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login_button);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        dbr = db.getReference("pack_leaders");

        //Handle Login Button Click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                //Alert if username or password is not entered
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Attempt Sign in through Firebase
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                //Login failed, display error message
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Login Failed, Password or username incorrect",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    //Login successful, direct user to main activity of correct portal
                                    dbr.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for(DataSnapshot singlePackLeader : dataSnapshot.getChildren()) {
                                                PackLeader p_lead = singlePackLeader.getValue(PackLeader.class);
                                                if (p_lead.email.equals(email)) {
                                                    startActivity(new Intent(LoginActivity.this, PackLeaderMainActivity.class));
                                                    finish();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            //TODO: Handle database error
                                        }
                                    });
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }

    /**
     * Launches Dog Owner Registration
     * @param view
     */
    public void beginRegistration(View view) {
        Intent launchRegistration = new Intent(LoginActivity.this,RegisterStep1Activity.class);
        startActivity(launchRegistration);
    }

    /**
     * Launches Pack Leader Registration/Application
     * @param view
     */
    public void beginPackLeaderRegistration(View view) {
        Intent launchRegistration = new Intent(LoginActivity.this, PackLeaderRegistrationActivity.class);
        startActivity(launchRegistration);
    }
}
