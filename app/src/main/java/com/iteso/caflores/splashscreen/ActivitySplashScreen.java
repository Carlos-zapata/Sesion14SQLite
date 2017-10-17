package com.iteso.caflores.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import java.util.Timer;
import android.support.v7.app.AppCompatActivity;
import com.iteso.caflores.splashscreen.beans.User;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                User user = new User().getUser(ActivitySplashScreen.this);
                Intent intent;
                if( user.isLogged() ){
                    //user is logged
                    intent = new Intent(ActivitySplashScreen.this, ActivityMain.class);
                }else{
                    intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                }
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }


}
