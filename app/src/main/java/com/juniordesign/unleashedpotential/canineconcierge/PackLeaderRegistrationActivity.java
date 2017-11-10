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

/**
 * PackLeaderRegistrationActivity - Pack Leader Portal
 *
 * Step 1 of pack leader registration, input name, email, pass, confirm pass
 */
public class PackLeaderRegistrationActivity extends AppCompatActivity {

    private EditText inputEmail, inputConfirmPassword, inputFirstName, inputLastName, inputPassword;
    private Button step2_button;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packleader_registration_step1);

        auth = FirebaseAuth.getInstance();

        step2_button = (Button) findViewById(R.id.packleader_registration_gotostep2);
        inputEmail = (EditText) findViewById(R.id.packleader_email);
        inputFirstName = (EditText) findViewById(R.id.packleader_firstname);
        inputLastName = (EditText) findViewById(R.id.packleader_lastname);
        inputPassword = (EditText) findViewById(R.id.packleader_password);
        inputConfirmPassword = (EditText) findViewById(R.id.packleader_confirmpassword);

        //Set up onclick listener to go to step 2, data validation on user input
        step2_button.setOnClickListener(new View.OnClickListener() {
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
                Intent step2 = new Intent(PackLeaderRegistrationActivity.this, PackLeaderRegistrationStep2Activity.class);
                step2.putExtra("email", email);
                step2.putExtra("password", password);
                step2.putExtra("last_name", lastName);
                step2.putExtra("first_name", firstName);
                startActivity(step2);
                finish();
            }
        });

    }

    /**
     * Go to step 2 of pack leader registration
     * @param view
     */
    public void goToRegisterStep2(View view) {
        startActivity(new Intent(PackLeaderRegistrationActivity.this, PackLeaderRegistrationStep2Activity.class));
    }
}
