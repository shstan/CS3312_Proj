package com.juniordesign.unleashedpotential.canineconcierge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        // TODO: attempt login
        // TODO: print error on message screen if fails
    }

    public void beginRegistration(View view) {
        Intent launchRegistration = new Intent(MainActivity.this,RegisterStep1.class);
        startActivity(launchRegistration);
    }

}
