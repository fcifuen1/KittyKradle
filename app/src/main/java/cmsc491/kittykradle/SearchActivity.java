package cmsc491.kittykradle;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchActivity extends SidebarActivity
{
    private TextView header;
    private EditText location;
    private Spinner breed, sex, age, size;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        header = (TextView) findViewById(R.id.header);
        location = (EditText) findViewById(R.id.location);
        breed = (Spinner) findViewById(R.id.breed);
        sex = (Spinner) findViewById(R.id.sex);
        age = (Spinner) findViewById(R.id.age);
        size = (Spinner) findViewById(R.id.size);

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
        ArrayAdapter<CharSequence> options_age =
                ArrayAdapter.createFromResource(this, R.array.options_age, android.R.layout.simple_spinner_item);
        options_age.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(options_age);

        // Set options for size
        ArrayAdapter<CharSequence> options_size =
                ArrayAdapter.createFromResource(this, R.array.options_size, android.R.layout.simple_spinner_item);
        options_size.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        size.setAdapter(options_size);
    }

    private void search(View view)
    {
        Intent i = new Intent(this, SearchResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("zipcode", location.getText().toString());
        bundle.putString("breed", breed.getSelectedItem().toString());
        bundle.putString("sex", sex.getSelectedItem().toString());
        bundle.putString("age", age.getSelectedItem().toString());
        bundle.putString("size", size.getSelectedItem().toString());
        startActivity(i);
    }
}
