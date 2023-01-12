package de.hawlandshut.pluto23_gkw;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PostActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "xxPostActivity";

    EditText mEditTextTitle;
    EditText mEditTextText;
    Button mButtonPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mButtonPost = findViewById( R.id.postButtonPost);
        mEditTextText = findViewById( R.id.postEditTextText);
        mEditTextTitle = findViewById( R.id.postEditTextTitle);
        mButtonPost.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.postButtonPost:
                doPost();
                return;
        }
    }

    private void doPost() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            // Nur zur Sicherheit - sollte nicht auftreten
            return;
        }
        String title = mEditTextTitle.getText().toString();
        String body = mEditTextText.getText().toString();

        if (body.equals("")){
            String msg = "Please enter message!";
            Toast.makeText(getApplicationContext(),  msg, Toast.LENGTH_LONG).show();
            return;
        }

        Map<String, Object> post = new HashMap<>();
        post.put("uid", user.getUid());
        post.put("email", user.getEmail());
        post.put("title", title);
        post.put("body", body);
        post.put("createdAt", new Date() );


        // Add the post document to Firestore
        FirebaseFirestore.getInstance().collection("posts")
                .add( post )
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Post added with id :" + documentReference.getId());
                        String msg = "Post sent.";
                        mEditTextTitle.setText("");
                        mEditTextText.setText("");
                        Toast.makeText(getApplicationContext(),  msg, Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Posting error : " + e);
                        String msg = "Posting failed.";
                        Toast.makeText(getApplicationContext(),  msg, Toast.LENGTH_LONG).show();
                    }
                });

        Toast.makeText(getApplicationContext(), "You pressed Post.", Toast.LENGTH_LONG).show();
    }
}