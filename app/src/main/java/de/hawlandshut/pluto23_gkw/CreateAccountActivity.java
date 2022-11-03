package de.hawlandshut.pluto23_gkw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccountActivity extends AppCompatActivity {

    EditText mCreateAccountEmail;
    EditText mCreateAccountPassword;
    EditText mCreateAccountPassword1;
    Button mCreateAccountButtonCreateAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mCreateAccountEmail = findViewById( R.id.createAccountEmail);
        mCreateAccountPassword = findViewById( R.id.createAccountPassword);
        mCreateAccountPassword1 = findViewById( R.id.createAccountPassword1);
        mCreateAccountButtonCreateAccount = findViewById( R.id.createAccountButtonCreateAccount);
    }
}