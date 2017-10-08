package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class PackLeaderRegistrationStep2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packleader_registration_step2);
    }

    public void goToRegisterStep2(View view) {
        //startActivity(new Intent(PackLeaderRegistrationActivity.this, PackLeaderRegistrationActivity2.class));
    }
}