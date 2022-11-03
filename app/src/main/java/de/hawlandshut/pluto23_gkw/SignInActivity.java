package de.hawlandshut.pluto23_gkw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    // 3.a Declare Variables for UI Elements
    EditText mSignInEmail;
    EditText mSignInPassword;
    Button mSignInButtonSignIn;
    Button mSignInButtonResetPassword;
    Button mSignInButtonCreateAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //3.b Initialize UI Variables
        mSignInEmail = findViewById( R.id.signInEmail);
        mSignInPassword = findViewById( R.id.signInPassword);
        mSignInButtonSignIn = findViewById( R.id.signInButtonSignIn);
        mSignInButtonResetPassword = findViewById( R.id.signInButtonResetPassword);
        mSignInButtonCreateAccount = findViewById( R.id.signInButtonCreateAccount);

        // 3.c Add Listener
        mSignInButtonSignIn.setOnClickListener( this );
        mSignInButtonResetPassword.setOnClickListener( this );
        mSignInButtonCreateAccount.setOnClickListener( this );

    }

    @Override
    public void onClick(View view) {
        switch( view.getId()){
            case R.id.signInButtonSignIn:
                doSignIn();
                return;

            case R.id.signInButtonResetPassword:
                doResetPassword();
                return;

            case R.id.signInButtonCreateAccount:
                Toast.makeText(getApplicationContext(), "You pressed CREATE ACCOUNT", Toast.LENGTH_LONG).show();
                return;



        }
    }

    private void doResetPassword() {
        Toast.makeText(getApplicationContext(), "You pressed RESET PASSWORD", Toast.LENGTH_LONG).show();
    }

    private void doSignIn() {
        Toast.makeText(getApplicationContext(), "You pressed SIGN IN", Toast.LENGTH_LONG).show();
    }
}