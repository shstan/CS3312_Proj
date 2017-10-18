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

public class RegisterStep1Activity extends AppCompatActivity {
    private EditText inputEmail, inputConfirmPassword, inputFirstName, inputLastName, inputPassword;
    private Button btnSignUp;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_step_1);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignUp = (Button) findViewById(R.id.to_register_step_2);
        inputEmail = (EditText) findViewById(R.id.email_address);
        inputFirstName = (EditText) findViewById(R.id.first_name);
        inputLastName = (EditText) findViewById(R.id.last_name);
        inputPassword = (EditText) findViewById(R.id.password);
        inputConfirmPassword = (EditText) findViewById(R.id.confirm_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //Intent to switch to MainActivity
        Intent mainInt = new Intent(RegisterStep1Activity.this, MainActivity.class);
        //Intent to switch to LoginActivity
        final Intent loginInt = new Intent(RegisterStep1Activity.this, LoginActivity.class);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String confirmPassword = inputConfirmPassword.getText().toString().trim();
                final String firstName = inputFirstName.getText().toString().trim();
                String lastName = inputLastName.getText().toString().trim();

                if (TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(firstName)) {
                    Toast.makeText(getApplicationContext(), "Enter First Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(lastName)) {
                    Toast.makeText(getApplicationContext(), "Enter Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(password.equals(confirmPassword))) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getApplicationContext(), "Enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter the password", Toast.LENGTH_SHORT).show();
                    return;
                }

                //start showing progress bar
                progressBar.setVisibility(View.VISIBLE);
                Intent step2 = new Intent(RegisterStep1Activity.this, RegisterStep2Activity.class);
                step2.putExtra("email", email);
                step2.putExtra("password", password);
                step2.putExtra("last_name", lastName);
                step2.putExtra("first_name", firstName);
                startActivity(step2);
                finish();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }



    public void goToRegisterStep2(View view) {
        startActivity(new Intent(RegisterStep1Activity.this, RegisterStep2Activity.class));
    }
}
