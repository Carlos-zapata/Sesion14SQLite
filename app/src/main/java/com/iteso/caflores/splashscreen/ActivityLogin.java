package com.iteso.caflores.splashscreen;


import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.iteso.caflores.splashscreen.beans.User;

public class ActivityLogin extends AppCompatActivity {

    protected EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.activity_login_username);
        password = (EditText) findViewById(R.id.activity_login_pwd);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_signin:

                User user = new User();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.savePreferences(this);

                Intent intent = new Intent(this, ActivityMain.class);
                startActivity(intent);
                finish();

                break;
        }
    }

    public void savePreferences() {
        User user = new User();
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());
        user.setLogged(true);
        SharedPreferences sharedPreferences = getSharedPreferences("com.iteso.caflores.splashscreen.CACAHUATE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USER", user.getUsername());
        editor.putString("PWD", user.getPassword());
        editor.putBoolean("LOGGED", user.isLogged());
        editor.apply();
    }
}
