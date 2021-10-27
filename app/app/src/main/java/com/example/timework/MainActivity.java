package com.example.timework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * MainPage of the app
 * Shows the main calendar
 * Sends the user to the sign in page if it cannot connect
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView view = (TextView)findViewById(R.id.textView);
        Button b = (Button)findViewById(R.id.button);
        view.setText(getString(R.string.user_name));

        // If no user, print error message and button to redirect to sign in activity
        if (getString(R.string.user_name).equals("None"))
        {
            view.setText(getString(R.string.no_account_message));
            b.setVisibility(View.VISIBLE);
        }
        else
        {
            b.setVisibility(View.GONE);
            // Otherwise Load user data
            String json = "{\n \"name\": \"" + getString(R.string.user_name) +
                    "\",\n \"email\": \"" + getString(R.string.user_mail) +
                    "\",\n \"password\": \"" + getString(R.string.user_pass) + "\"\n";

            view.setText(PostRequest("https://localhost:4000/api/signin", json));
        }
    }


    /**
     * Sends a post request to the api
     *
     * @param link Url of the request
     * @param data Data of the request
     * @return the response of the API or an error message in case of failure
     */
    public static String PostRequest(String link, String data)
    {
        String msg;
        URL url;
        try {
            url = new URL(link);
        } catch (Exception e){
            return "[Request-Error] Invalid URL (" + e.getMessage() + ")";
        }

        HttpURLConnection http;
        try {
            http = (HttpURLConnection) url.openConnection();

            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("Accept", "application/json");
            http.setRequestProperty("Content-Type", "application/json");

            http.connect();
        } catch (Exception e) {
            return "[Request-Error] Connection Failed (" + e.getMessage() + ")";
        }

        try {
            byte[] out = data.getBytes(StandardCharsets.UTF_8);
            OutputStream stream = http.getOutputStream();
            stream.write(out);
        } catch (Exception e) {
            return "[Request-Error] Failed to send data (" + e.getMessage() + ")";
        }

        try {
            msg = http.getResponseMessage();
        }
        catch (Exception e) {
            return "[Request-Error] Failed to retrieve data (" + e.getMessage() + ")";
        }

        http.disconnect();
        return msg;
    }

    /**
     * Load the Sign In page
     *
     * @param view Represents the button that called the method.
     *             The function can't be called from the button without it
     */
    public void LoadSignInPage(View view)
    {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}