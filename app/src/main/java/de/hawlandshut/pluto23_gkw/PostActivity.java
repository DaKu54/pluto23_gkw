package de.hawlandshut.pluto23_gkw;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


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
        Toast.makeText(getApplicationContext(), "You pressed Post.", Toast.LENGTH_LONG).show();
    }
}