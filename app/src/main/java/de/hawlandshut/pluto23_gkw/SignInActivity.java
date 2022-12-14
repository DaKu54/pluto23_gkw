package de.hawlandshut.pluto23_gkw;

import static de.hawlandshut.pluto23_gkw.Testdata.Testdata.TEST_MAIL;
import static de.hawlandshut.pluto23_gkw.Testdata.Testdata.TEST_PASSWORD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = "xx SignInAct.";

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

        // TODO: Testdaten - später löschen
        mSignInEmail.setText( TEST_MAIL );
        mSignInPassword.setText( TEST_PASSWORD);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Log.d(TAG, "User not null  - coming from CreateAccount");
            finish();
        }
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
                Intent intent = new Intent(getApplication(), CreateAccountActivity.class);
                startActivity(intent);
                return;
        }
    }

    private void doResetPassword() {
        String email = mSignInEmail.getText().toString();

        // TODO: Check email

        FirebaseAuth.getInstance().sendPasswordResetEmail(
                        email)
        .addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg;
                        if (task.isSuccessful()) {
                            msg = "E-Mail sent.";
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        } else {
                            String error = task.getException().getMessage();
                            msg = "Failed (" + error + ")";
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        }
                    }
                });
}

    private void doSignIn() {
        String email, password;

        email = mSignInEmail.getText().toString();
        password = mSignInPassword.getText().toString();

        // TODO: Check email, check password

        FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        email, password)
        .addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String msg;
                        if (task.isSuccessful()) {
                            msg = "User signed in.";
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            String error = task.getException().getMessage();
                            msg = "Failed (" + error + ")";
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}