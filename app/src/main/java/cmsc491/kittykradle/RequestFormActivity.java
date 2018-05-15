package cmsc491.kittykradle;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RequestFormActivity extends SidebarActivity
{
    private EditText firstname, lastname, phonenumber, address, city, zipcode;
    private Spinner state, country, backgroundcheck, petinsurance;
    Button toSubmitBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_form);

        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        phonenumber = (EditText) findViewById(R.id.phonenumber);
        address = (EditText) findViewById(R.id.address);
        city = (EditText) findViewById(R.id.city);
        state = (Spinner) findViewById(R.id.state);
        country = (Spinner) findViewById(R.id.country);
        zipcode = (EditText) findViewById(R.id.zipcode);
        backgroundcheck = (Spinner) findViewById(R.id.backgroundcheck);
        petinsurance = (Spinner) findViewById(R.id.petinsurance);
        toSubmitBTN = (Button) findViewById(R.id.submitBTN);

        // Set options for state
        ArrayAdapter<CharSequence> options_state =
                ArrayAdapter.createFromResource(this, R.array.options_state, android.R.layout.simple_spinner_item);
        options_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(options_state);

        // Set options for country
        ArrayAdapter<CharSequence> options_country =
                ArrayAdapter.createFromResource(this, R.array.options_country, android.R.layout.simple_spinner_item);
        options_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(options_country);

        // Set options for backgroundcheck
        ArrayAdapter<CharSequence> options_backgroundcheck =
                ArrayAdapter.createFromResource(this, R.array.options_backgroundcheck, android.R.layout.simple_spinner_item);
        options_backgroundcheck.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundcheck.setAdapter(options_backgroundcheck);

        // Set options for petinsurance
        ArrayAdapter<CharSequence> options_petinsurance =
                ArrayAdapter.createFromResource(this, R.array.options_petinsurance, android.R.layout.simple_spinner_item);
        options_petinsurance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        petinsurance.setAdapter(options_petinsurance);

        toSubmitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    private void submit()
    {

    }
}