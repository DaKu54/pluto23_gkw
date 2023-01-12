package de.hawlandshut.pluto23_gkw;

import static de.hawlandshut.pluto23_gkw.Testdata.Testdata.TEST_MAIL;
import static de.hawlandshut.pluto23_gkw.Testdata.Testdata.TEST_PASSWORD;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import de.hawlandshut.pluto23_gkw.model.Post;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "xx MainActivity";
    CustomAdapter mAdapter;
    RecyclerView mRecyclerView;

    ListenerRegistration mListenerRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate called");

        mAdapter = new CustomAdapter();
        mRecyclerView = findViewById(R.id.main_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =  FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) { // Falls kein User angemeldet gehe zu SignIn
            Log.d(TAG, "No user - going to SignIn");
            Intent intent = new Intent(getApplication(), SignInActivity.class);
            startActivity(intent);
            if (mListenerRegistration != null) {
                mListenerRegistration.remove();
                mListenerRegistration=null;
            }
        } else {
            if (mListenerRegistration == null)
                mListenerRegistration = getQueryListener();
        }
    }

    ListenerRegistration getQueryListener(){
        // Abfrage
        Query query = FirebaseFirestore.getInstance()
                .collection("posts")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .limit(10);
        return query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                mAdapter.mPostList.clear(); // List im Adapter leeren

                // Nun Liste neu aufbauen
                // laufe dazu durch alle Dokumente im snapshot
                for (QueryDocumentSnapshot doc : snapshot){
                    Post receivedPost = Post.fromDocument( doc );
                    mAdapter.mPostList.add( receivedPost );
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_main_manage_account:
                intent = new Intent(getApplication(), ManageAccountActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_main_post:
                intent = new Intent(getApplication(), PostActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_main_crash:
                throw new RuntimeException("Test Crash");

        }
        return true;
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

    private void doSendPasswordResetMail() {
        FirebaseAuth.getInstance().sendPasswordResetEmail(
                        TEST_MAIL)
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

    private void doDeleteUser() {
        FirebaseUser user;
        String msg;
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            msg = "No user logged in";
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

                            } else {
                                String error = task.getException().getMessage();
                                msg = "Deletion failed (" + error + ")";
                                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void doSignIn() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        TEST_MAIL, TEST_PASSWORD)
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                String msg;
                                if (task.isSuccessful()) {
                                    msg = "User signed in.";
                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                } else {
                                    String error = task.getException().getMessage();
                                    msg = "Failed (" + error + ")";
                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                }

                            }
                        });

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
        if (user == null) {
            msg = "No user signed in.";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        } else {
            msg = "User: " + user.getEmail() + " (" + user.isEmailVerified() + ")";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
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
                                if (task.isSuccessful()) {
                                    msg = "User created.";
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