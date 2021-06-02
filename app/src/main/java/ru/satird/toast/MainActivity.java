package ru.satird.toast;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "mysettings";
    public static final String COUNTER = "count";
    SharedPreferences mSettings;
    int counter;
    TextView textCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        textCount = findViewById(R.id.text_counter);

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("COUNT");
            textCount.setText(String.valueOf(counter));
        } else {
            if (mSettings.contains(COUNTER)) {
                counter = mSettings.getInt(COUNTER, counter);
            }
            counter ++;
            textCount.setText(String.valueOf(counter));
        }

        if (counter != 0 && counter % 3 == 0) {
            Toast.makeText(getApplicationContext(), "Hello TOAST!!!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("COUNT", counter);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt("COUNT");
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(COUNTER, counter);
        editor.apply();
        super.onPause();
    }

}