package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegisterStep1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step_1);
    }

    public void goToRegisterStep2(View view) {
        // TODO: validate user input
        startActivity(new Intent(RegisterStep1.this, RegisterStep2.class));
    }
}
