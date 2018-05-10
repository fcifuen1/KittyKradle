package cmsc491.kittykradle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button successBTN, toRegisterBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        username = (EditText) findViewById(R.id.logInUsername);
        password = (EditText) findViewById(R.id.logInPassword);

        successBTN = (Button) findViewById(R.id.continueBTN);
        toRegisterBTN = (Button) findViewById(R.id.registerBTN);

        successBTN.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //Goes to main page
            }
        });

        toRegisterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegistration();
            }
        });
    }

    private void goToRegistration(){
        Intent intent = new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }
}
