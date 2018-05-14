package cmsc491.kittykradle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Settings extends SidebarActivity {

    boolean editProfileTextDisplayed= false;
    boolean facebookLinkTextDisplayed= false;
    boolean languageTextDisplayed= false;
    boolean notificationTextDisplayed= false;
    boolean changePasswordTextDisplayed= false;
    boolean deleteAccTextDisplayed= false;
    boolean logoutTextDisplayed= false;

    Button contactBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        contactBTN = (Button) findViewById(R.id.toContact);

        final TextView editProfile = findViewById(R.id.setting_editprofile);
        ImageButton editProfileBTN = findViewById(R.id.setting_editProfileBTN);
        editProfileBTN.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                editProfileTextDisplayed = !editProfileTextDisplayed;
                if(editProfileTextDisplayed) {
                    editProfile.setText("1. Go to your profile. \n" +
                            "2. Click About at the bottom of your cover photo. \n" +
                            "3. In the left column, click the section you'd like the change. \n" +
                            "4. Hover over the information you'd like to edit. \n" +
                            "5. To the right of that section, click Edit or Options, then select Edit.");
                }
                else {
                    editProfile.setText("");
                }
            }
        });

        contactBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToContact();
            }
        });
    }
    private void goToContact(){
        Intent i = new Intent(this, ContactActivity.class);
        startActivity(i);
    }
}
