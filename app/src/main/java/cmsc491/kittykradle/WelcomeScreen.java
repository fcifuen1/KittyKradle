package cmsc491.kittykradle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeScreen extends AppCompatActivity
{
    // https://medium.com/@ssaurel/create-a-splash-screen-on-android-the-right-way-93d6fb444857

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }
}
