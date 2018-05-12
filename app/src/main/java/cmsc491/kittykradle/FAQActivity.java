package cmsc491.kittykradle;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class FAQActivity extends SidebarActivity {

    boolean searchTextDisplayed = false;
    boolean adoptTextDisplayed = false;
    boolean approvalTextDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView searchText = findViewById(R.id.searchText);
        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                searchTextDisplayed = !searchTextDisplayed;
                if(searchTextDisplayed) {
                    searchText.setText("You can begin your search by logging in and filling out the questionnaire, which will help you find the perfect cat. Since Kitty Kradle is updated regularly, we recommend that you keep checking back or sign up for email alerts to be notified when new cats matching your search criteria are added to the site");
                }
                else {
                    searchText.setText("");
                }
            }
        });

        final TextView adoptText = findViewById(R.id.adoptText);
        ImageButton adoptButton = findViewById(R.id.adoptButton);
        adoptButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                adoptTextDisplayed = !adoptTextDisplayed;
                if(adoptTextDisplayed) {
                    adoptText.setText("Once you've favorited a cat, go to your favorites and press the 'request' button. Then, fill out the information in the form to send an email to the adoption agency.");
                }
                else {
                    adoptText.setText("");
                }
            }
        });

        final TextView approvalText = findViewById(R.id.approvalText);
        ImageButton approvalButton = findViewById(R.id.approvalButton);
        approvalButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                approvalTextDisplayed = !approvalTextDisplayed;
                if(approvalTextDisplayed) {
                    approvalText.setText("Within a few days of sending your request, you should receive an email from the adoption agency telling you whether or not you've been approved for adoption.");
                }
                else {
                    approvalText.setText("");
                }
            }
        });
    }

}
