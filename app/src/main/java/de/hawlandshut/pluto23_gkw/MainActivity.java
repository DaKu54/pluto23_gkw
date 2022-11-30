package de.hawlandshut.pluto23_gkw;

import static de.hawlandshut.pluto23_gkw.Testdata.Testdata.TEST_MAIL;
import static de.hawlandshut.pluto23_gkw.Testdata.Testdata.TEST_PASSWORD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "xx MainActivity";
    CustomAdapter mAdapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate called");

        mAdapter = new CustomAdapter();
        mRecyclerView = findViewById( R.id.main_recycler_view );
        mRecyclerView.setLayoutManager( new LinearLayoutManager( this ));
        mRecyclerView.setAdapter( mAdapter );
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Intent intent = new Intent(getApplication(), CreateAccountActivity.class);
        //startActivity(intent);
        Log.d(TAG,"onStart called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId() ){
            case R.id.menu_main_test_auth_status:
                doTestAuthStatus();
                return true;

            case R.id.menu_main_create_test_user:
                doCreateTestUser();
                return true;

            case R.id.menu_main_sign_out:
                doSignOut();
                return true;

            case R.id.menu_main_sign_in:
                doSignIn();
                return true;

            case R.id.menu_main_delete_user:
                doDeleteUser();
                return true;

            case R.id.menu_main_send_password_reset_mail:
                doSendPasswordResetMail();
                return true;

            case R.id.menu_main_send_verification_mail:
                doSendVerificationMail();
                return true;
                
        }
        return true;
    }

    private void doSendVerificationMail() {
    }

    private void doSendPasswordResetMail() {
    }

    private void doDeleteUser() {
        FirebaseUser user;
        String msg;
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            msg ="No user logged in";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }
        else {
            user.delete().addOnCompleteListener(this,
                    new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg;
                            if( task.isSuccessful()){
                                msg = "User deleted.";
                                Toast.makeText( getApplicationContext(), msg, Toast.LENGTH_LONG ).show();

                            }
                            else {
                                String error = task.getException().getMessage();
                                msg = "Deletion failed (" + error +")";
                                Toast.makeText( getApplicationContext(), msg, Toast.LENGTH_LONG ).show();
                            }
                        }
                    });
        }
    }

    private void doSignIn() {
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
        }
    }

    private void doTestAuthStatus() {
        FirebaseUser user;
        String msg;
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            msg = "No user signed in.";
            Toast.makeText( getApplicationContext(), msg, Toast.LENGTH_LONG ).show();
        }
        else {
            msg = "User: " + user.getEmail();
            Toast.makeText( getApplicationContext(), msg, Toast.LENGTH_LONG ).show();
        }
    }
    
    
    private void doCreateTestUser() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                TEST_MAIL, TEST_PASSWORD)
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                String msg;
                               if( task.isSuccessful()){
                                   msg = "User created.";
                                   Toast.makeText( getApplicationContext(), msg, Toast.LENGTH_LONG ).show();

                               }
                               else {
                                    String error = task.getException().getMessage();
                                    msg = "Failed (" + error +")";
                                   Toast.makeText( getApplicationContext(), msg, Toast.LENGTH_LONG ).show();
                               }

                            }
                        });

    }


}