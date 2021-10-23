package com.example.timework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Sign in Page of the App
 * Loads Calendar data and send it to the Main Page
 */
public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    /**
     * Sends a sign in request to the api
     *
     * @param view Represents the button that called the method.
     *             The function can't be called from the button without it
     */
    public void SignIn(View view)
    {
        EditText text = findViewById(R.id.editTextTextPersonName);
        EditText mail = findViewById(R.id.editTextTextEmailAddress);
        EditText pass = findViewById(R.id.editTextTextPassword);

        String json = "{\n \"name\": \"" + text.getText().toString() +
                "\",\n \"email\": \"" + mail.getText().toString() +
                "\",\n \"password\": \"" + pass.getText().toString() + "\"\n";

        text.setText(MainActivity.PostRequest("https:://localhost:4000/signin", json));
    }

    /**
     * Loads the Sign Up page
     *
     * @param view Represents the button that called the method.
     *             The function can't be called from the button without it
     */
    public void LoadSignUpPage(View view)
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}