package cmsc491.kittykradle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class WelcomeScreen extends AppCompatActivity
{
    private final int DELAY = 1000 * 2; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Homepage.class);
                startActivity(intent);
                finish();
            }
        }, DELAY);
    }
}
