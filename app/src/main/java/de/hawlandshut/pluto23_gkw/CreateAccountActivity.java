package de.hawlandshut.pluto23_gkw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{

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

        mCreateAccountButtonCreateAccount.setOnClickListener( this );
   }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch(i){
            case R.id.createAccountButtonCreateAccount:
                doCreateAccount();
                return;
        }
    }

    private void doCreateAccount() {
        Toast.makeText(getApplicationContext(), "You pressed Create Account.", Toast.LENGTH_LONG).show();
    }

}