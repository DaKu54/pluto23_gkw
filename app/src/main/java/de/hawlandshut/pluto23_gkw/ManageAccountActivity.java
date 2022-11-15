package de.hawlandshut.pluto23_gkw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        mTextViewMail.setText("E-Mail : hans.huber@gmail.com" );

        // Register Listeners
        mButtonSignOut.setOnClickListener( this );
        mButtonDeleteAccount.setOnClickListener( this );
        mButtonSendActivationMail.setOnClickListener( this );

    }

    @Override
    public void onClick(View view) {
        switch( view.getId() ){
            case R.id.manage_account_button_sign_out:
                doSignOut();
                return;

            case R.id.manage_account_button_send_activation_mail:
                doSendActivationMail();
                return;

            case R.id.manage_account_button_delete_account:
                doDeleteAccount();
        }
    }

    private void doDeleteAccount() {
        Toast.makeText(getApplicationContext(), "You pressed DeleteAccount.", Toast.LENGTH_LONG).show();
    }

    private void doSendActivationMail() {
        Toast.makeText(getApplicationContext(), "You pressed Send Act Mail.", Toast.LENGTH_LONG).show();
    }

    private void doSignOut() {
        Toast.makeText(getApplicationContext(), "You pressed Sign Out.", Toast.LENGTH_LONG).show();
    }
}