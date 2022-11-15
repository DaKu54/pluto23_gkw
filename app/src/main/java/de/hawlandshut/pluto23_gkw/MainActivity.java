package de.hawlandshut.pluto23_gkw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "xx MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(getApplication(), PostActivity.class);
        startActivity(intent);
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
            case R.id.menu_main_test1:
                Toast.makeText(getApplicationContext(), "You pressed Test 1", Toast.LENGTH_LONG).show();
                return true;

            case R.id.menu_main_test2:
                Toast.makeText(getApplicationContext(), "You pressed Test 2", Toast.LENGTH_LONG).show();
                return true;

            case R.id.menu_main_test3:
                Toast.makeText(getApplicationContext(), "You pressed Test 3", Toast.LENGTH_LONG).show();
                return true;
        }
        return true;
    }
}