package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    static String usernameKey;
    static SharedPreferences sharedPreferences;

    public void onButtonClick(View view){
        //1. Get username and password via EditText view.
        EditText myTextField = (EditText) findViewById(R.id.username);
        String userEditString = myTextField.getText().toString();
        //2. Add username to SharedPreferences object.
        sharedPreferences.edit().putString(usernameKey, userEditString).apply();
        //3. Start second activity
        goToActivity2();
    }

    public void goToActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernameKey = "username";
        sharedPreferences = getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);

        if(!sharedPreferences.getString(usernameKey, "").equals("")){
            // "username" key exists in SharedPreferences obj which means that a user
            // was logged in before the app closed.
            // Get the name of that user from SharedPreferences using
            // sharedPreferences.getString(usernameKey, "").
            // Use intent to start the second activity welcoming the user.
            goToActivity2();
        } else {
            // SharedPreferences has no username key set.
            // Start screen 1, that is the main activity.
            setContentView(R.layout.activity_main);
        }
    }
}