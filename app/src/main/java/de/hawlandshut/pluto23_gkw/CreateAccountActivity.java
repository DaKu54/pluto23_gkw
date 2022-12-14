package de.hawlandshut.pluto23_gkw;

import static de.hawlandshut.pluto23_gkw.Testdata.Testdata.TEST_MAIL;
import static de.hawlandshut.pluto23_gkw.Testdata.Testdata.TEST_PASSWORD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

        // TODO: Delete Testdata
        mCreateAccountEmail.setText( TEST_MAIL );
        mCreateAccountPassword.setText( TEST_PASSWORD );
        mCreateAccountPassword1.setText( TEST_PASSWORD );
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
        String email, password, password1;

        email = mCreateAccountEmail.getText().toString();
        password = mCreateAccountPassword.getText().toString();
        password1 = mCreateAccountPassword1.getText().toString();


        if (!password1.equals( password )){
            String msg = "Passwords do not match";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            return;
        }

        if (password.length() < 6){
            String msg = "Password too short";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            return;
        }

        // TODO: Check e-mail...

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        TEST_MAIL, TEST_PASSWORD)
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                String msg;
                                if (task.isSuccessful()) {
                                    msg = "User created.";
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