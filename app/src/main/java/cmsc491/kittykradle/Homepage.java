package cmsc491.kittykradle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends SidebarActivity {

    Button toSearch, toFaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        toSearch = (Button) findViewById(R.id.toSearchBTN);
        toFaq = (Button) findViewById(R.id.tofaqBTN);

        toSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSearch();
            }
        });

        toFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFaq();
            }
        });
    }

    private void goToSearch(){
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
    }
    private void goToFaq(){
        Intent i = new Intent(this, FAQActivity.class);
        startActivity(i);
    }
}
