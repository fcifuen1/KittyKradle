package cmsc491.kittykradle;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchActivity extends SidebarActivity
{
    private TextView header, minAge, maxAge;
    private EditText location;
    private Spinner breed, sex, age, size;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        header = (TextView) findViewById(R.id.header);
        location = (EditText) findViewById(R.id.location);
        breed = (Spinner) findViewById(R.id.breed);
        sex = (Spinner) findViewById(R.id.sex);
        minAge= findViewById(R.id.min_age);
        maxAge=findViewById(R.id.max_age);
        size = (Spinner) findViewById(R.id.size);
        searchBtn = (Button) findViewById(R.id.searchBTN);
        // Set the header to open sans font
        //header.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opansans.ttf"));

        // Set options for breed
        ArrayAdapter<CharSequence> options_breed =
                ArrayAdapter.createFromResource(this, R.array.options_breed, android.R.layout.simple_spinner_item);
        options_breed.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breed.setAdapter(options_breed);

        // Set options for sex
        ArrayAdapter<CharSequence> options_sex =
                ArrayAdapter.createFromResource(this, R.array.options_sex, android.R.layout.simple_spinner_item);
        options_sex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(options_sex);

        // Set options for age


        // Set options for size
        ArrayAdapter<CharSequence> options_size =
                ArrayAdapter.createFromResource(this, R.array.options_size, android.R.layout.simple_spinner_item);
        options_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        size.setAdapter(options_size);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
    }

    private void search()
    {
        Intent i = new Intent(this, SearchResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("zipcode", location.getText().toString());
        if(breed.getSelectedItemPosition()>0){
            bundle.putString("breed", breed.getSelectedItem().toString());
        }
        if(sex.getSelectedItemPosition()>0){
            bundle.putString("sex", sex.getSelectedItem().toString().equals("male")?"M":"F");
        }
        bundle.putString("minAge", minAge.getText().toString());
        bundle.putString("maxAge", maxAge.getText().toString());
        if(size.getSelectedItemPosition()>0) {
            bundle.putString("size", size.getSelectedItem().toString());
        }
        i.putExtras(bundle);
        startActivity(i);
    }
}
