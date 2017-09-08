package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegisterStep2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step_2);
    }

    public void finishRegistration(View view) {
        // TODO: validate user input
        // TODO: send info to database
    }
}
