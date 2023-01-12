package de.hawlandshut.pluto23_gkw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManageAccountActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mTextViewMail;
    TextView mTextViewAccountVerified;
    TextView mTextViewId;

    Button mButtonSignOut;
    Button mButtonSendActivationMail;
    Button mButtonDeleteAccount;
    EditText mEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        mTextViewMail = findViewById( R.id.manage_account_email);
        mTextViewAccountVerified = findViewById( R.id.manage_account_account_verified);
        mTextViewId = findViewById( R.id.manage_account_id);

        mButtonSignOut = findViewById( R.id.manage_account_button_sign_out);
        mButtonSendActivationMail = findViewById( R.id.manage_account_button_send_activation_mail);

        mEditTextPassword = findViewById( R.id.manage_account_password);
        mButtonDeleteAccount = findViewById( R.id.manage_account_button_delete_account);

        // Register Listeners
        mButtonSignOut.setOnClickListener( this );
        mButtonDeleteAccount.setOnClickListener( this );
        mButtonSendActivationMail.setOnClickListener( this );

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mTextViewMail.setText("E-Mail : " + user.getEmail());
        mTextViewAccountVerified.setText("Account verified : " + user.isEmailVerified());
        mTextViewId.setText("Technical Id : " + user.getUid());

     }

    @Override
    public void onClick(View view) {
        switch( view.getId() ){
            case R.id.manage_account_button_sign_out:
                doSignOut();
                return;

            case R.id.manage_account_button_send_activation_mail:
                doSendVerificationMail();
                return;

            case R.id.manage_account_button_delete_account:
                doDeleteAccount();
        }
    }

    private void doDeleteAccount() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            String msg = "No user logged in";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        } else {
            user.delete().addOnCompleteListener(this,
                    new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg;
                            if (task.isSuccessful()) {
                                msg = "User deleted.";
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                String error = task.getException().getMessage();
                                msg = "Deletion failed (" + error + ")";
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void doSendVerificationMail() {
        FirebaseUser user;
        String msg;
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            msg = "No user logged in";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        } else {
            user.sendEmailVerification()
                    .addOnCompleteListener(this,
                            new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    String msg;
                                    if (task.isSuccessful()) {
                                        msg = "Mail sent.";
                                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                    } else {
                                        String error = task.getException().getMessage();
                                        msg = "Failed (" + error + ")";
                                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
        }
    }

    private void doSignOut() {
        FirebaseUser user;
        String msg;
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            msg = "No user signed in!";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        } else {
            FirebaseAuth.getInstance().signOut();
            msg = "You are signed out.";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            finish();
        }
    }
}