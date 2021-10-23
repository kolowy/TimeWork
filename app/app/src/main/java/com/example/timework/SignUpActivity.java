package com.example.timework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Sign up page of the app
 * Register a user to the database and send an empty
 * calendar to the Main Page
 */
public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    /**
     * Sends a sign up request to the api
     *
     * @param view Represents the button that called the method.
     *             The function can't be called from the button without it
     */
    public void SignUp(View view)
    {
        EditText text = findViewById(R.id.editTextTextPersonName);
        EditText mail = findViewById(R.id.editTextTextEmailAddress);
        EditText pass = findViewById(R.id.editTextTextPassword);

        String json = "{\n \"name\": \"" + text.getText().toString() +
                "\",\n \"email\": \"" + mail.getText().toString() +
                "\",\n \"password\": \"" + pass.getText().toString() + "\"\n";

        text.setText(MainActivity.PostRequest("https:://localhost:4000/register-user", json));
    }
}