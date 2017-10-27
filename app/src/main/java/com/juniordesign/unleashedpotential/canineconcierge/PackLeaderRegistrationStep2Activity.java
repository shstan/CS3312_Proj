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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PackLeaderRegistrationStep2Activity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText inputZip, inputState, inputPhoneNumber, inputAddress1, inputAddress2, inputCity;
    private Button btnSignUp;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packleader_registration_step2);

        db = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        inputZip = (EditText) findViewById(R.id.packleader_zip);
        inputState = (EditText) findViewById(R.id.packleader_state);
        //Id says password but it is the phone number input, when I switch the id to phone_number it messes the layout
        //up and I dont want to mess with it xD
        inputPhoneNumber = (EditText) findViewById(R.id.packleader_phone);
        inputAddress1 = (EditText) findViewById(R.id.packleader_addr1);
        inputAddress2 = (EditText) findViewById(R.id.packleader_addr2);
        inputCity = (EditText) findViewById(R.id.packleader_city);
        btnSignUp = (Button) findViewById(R.id.continue_step3);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String zip = inputZip.getText().toString().trim();
                String state = inputState.getText().toString().trim();
                String phoneNumber = inputPhoneNumber.getText().toString().trim();
                String address1 = inputAddress1.getText().toString().trim();
                String address2 = inputAddress2.getText().toString().trim();
                String city = inputCity.getText().toString().trim();
                String email = getIntent().getStringExtra("email");
                String password = getIntent().getStringExtra("password");
                String firstName = getIntent().getStringExtra("first_name");
                String lastName = getIntent().getStringExtra("last_name");
                if (TextUtils.isEmpty(zip)) {
                    Toast.makeText(getApplicationContext(), "Enter Zip Code!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(state)) {
                    Toast.makeText(getApplicationContext(), "Enter State!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(getApplicationContext(), "Enter Phone Number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(address1)) {
                    Toast.makeText(getApplicationContext(), "Enter Address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(city)) {
                    Toast.makeText(getApplicationContext(), "Enter City!", Toast.LENGTH_SHORT).show();
                    return;
                }

                final PackLeader newLeader = new PackLeader(email, firstName, lastName, zip, state, phoneNumber, address1, address2, city);
                //create user/ user profile
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(PackLeaderRegistrationStep2Activity.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //If registering fails, display message
                                if (!task.isSuccessful()) {
                                    Toast.makeText(PackLeaderRegistrationStep2Activity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    //Once user is authenticated, create profile based on UserID in firebase
                                    String uID = auth.getCurrentUser().getUid();
                                    db.child("pack_leaders").child(uID).setValue(newLeader);
                                    startActivity(new Intent(PackLeaderRegistrationStep2Activity.this, PackLeaderMainActivity.class));
                                }
                            }
                        });
            }
        });
    }

    public void goToRegisterStep3(View view) {
        startActivity(new Intent(PackLeaderRegistrationStep2Activity.this, PackLeaderRegistrationSetHoursActivity.class));
    }
}