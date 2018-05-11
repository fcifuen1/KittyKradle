package cmsc491.kittykradle;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RegistrationActivity extends AppCompatActivity {
    //UI Components
    EditText username, password, userEmail;
    Button toLogInBTN;

    //URL link to server-side
    Bundle extras;
    String urlLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        //Make UI component accessible
        username = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.userPassword);
        userEmail = (EditText) findViewById(R.id.email);
        toLogInBTN = (Button) findViewById(R.id.registerBTN);
        //Receives the url link from log in activity
        extras = getIntent().getExtras();
        urlLink = extras.getString("link");

        toLogInBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    private void successRegistration(){
        finish();
    }
    private class BackgroundTask extends AsyncTask<String, String, String> {

        HttpsURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute(){super.onPreExecute(); }

        //Grunt work to send data to the server-side
        @Override
        protected String doInBackground(String... params) {
            try{
                url = new URL(urlLink);
                conn = (HttpsURLConnection) url.openConnection();
                conn.setReadTimeout(1000);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("userName",params[0])
                        .appendQueryParameter("password",params[1])
                        .appendQueryParameter("email",params[2]);
                String query = builder.build().getEncodedQuery();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(String... values){
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            if(result.equalsIgnoreCase("success")){
                successRegistration();
            }else if(result.equalsIgnoreCase("false")){
                Toast.makeText(RegistrationActivity.this, "Invalid email or Username already taken.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
