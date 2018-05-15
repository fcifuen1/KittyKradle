package cmsc491.kittykradle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CatCareActivity extends SidebarActivity {

    boolean safetyTextDisplayed = false;
    boolean feedingTextDisplayed = false;
    boolean healthyTextDisplayed = false;
    boolean litterboxTextDisplayed = false;
    boolean trainingTextDisplayed = false;

    Button contactBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_care);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kitty Kradlez");

        final TextView safetyText = findViewById(R.id.safetyText);
        ImageButton safetyButton = findViewById(R.id.safetyButton);
        safetyButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                safetyTextDisplayed = !safetyTextDisplayed;
                if(safetyTextDisplayed) {
                    safetyText.setText("Keep your cats safe by keeping them indoors, safely confined to your property, or walked on a harness and leash.");
                }
                else {
                    safetyText.setText("");
                }
            }
        });

        final TextView feedingText = findViewById(R.id.feedingText);
        ImageButton feedingButton = findViewById(R.id.feedingButton);
        feedingButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                feedingTextDisplayed = !feedingTextDisplayed;
                if(feedingTextDisplayed) {
                    feedingText.setText("You should feed your cat twice per day using cat food appropriate to its age and size.");
                }
                else {
                    feedingText.setText("");
                }
            }
        });

        final TextView healthyText = findViewById(R.id.healthyText);
        ImageButton healthyButton = findViewById(R.id.healthyButton);
        healthyButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                healthyTextDisplayed = !healthyTextDisplayed;
                if(healthyTextDisplayed) {
                    healthyText.setText("You should bring your cat to the vet for a regular checkup once per year, or twice per year for cats over the age of 7 or 8. For kittens under 1 year old, they should see the vet once every three to four weeks.");
                }
                else {
                    healthyText.setText("");
                }
            }
        });

        final TextView litterboxText = findViewById(R.id.litterboxText);
        ImageButton litterboxButton = findViewById(R.id.litterboxButton);
        litterboxButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                litterboxTextDisplayed = !litterboxTextDisplayed;
                if(litterboxTextDisplayed) {
                    litterboxText.setText("You should scoop your cats litter box every day, removing any solid waste or clumps of urine. Additionally, change the box as needed to keep it clean and ensure it can absorb odors.");
                }
                else {
                    litterboxText.setText("");
                }
            }
        });

        final TextView trainingText = findViewById(R.id.trainingText);
        ImageButton trainingButton = findViewById(R.id.trainingButton);
        trainingButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                trainingTextDisplayed = !trainingTextDisplayed;
                if(trainingTextDisplayed) {
                    trainingText.setText("Use tasty treats and be patient when training your cat, and remember that cats don't respond well to punishments.");
                }
                else {
                    trainingText.setText("");
                }
            }
        });

        contactBtn = (Button) findViewById(R.id.contactButton);
        contactBtn.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                toContact();
            }
        });
    }
    private void toContact(){
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

}
