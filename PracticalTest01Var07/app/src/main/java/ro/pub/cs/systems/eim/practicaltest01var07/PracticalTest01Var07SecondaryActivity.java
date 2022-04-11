package ro.pub.cs.systems.eim.practicaltest01var07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {
    EditText text1, text2, text3, text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Constants.TAG, "Secondary activity onCreate()");
        setContentView(R.layout.activity_practical_test01_var07_secondary);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);

        Intent intent = getIntent();
        if (intent != null) { //intent.getExtras().containsKey(key)
            Log.d(Constants.TAG, "Secondary activity received intent");
            text1.setText(intent.getExtras().getString("text1"));
            text2.setText(intent.getExtras().getString("text2"));
            text3.setText(intent.getExtras().getString("text3"));
            text4.setText(intent.getExtras().getString("text4"));
        }

        findViewById(R.id.switch_activity_button).setOnClickListener((v) -> {
            returnToCallingActivity(Activity.RESULT_OK);
        });

        findViewById(R.id.sum).setOnClickListener((v) -> {
            int sum = Integer.parseInt(text1.getText().toString()) +
                    Integer.parseInt(text2.getText().toString()) +
                    Integer.parseInt(text3.getText().toString()) +
                    Integer.parseInt(text4.getText().toString());
            Toast.makeText(this, "Sum: " + sum, Toast.LENGTH_LONG).show();
        });

        findViewById(R.id.product).setOnClickListener((v) -> {
            int prod = Integer.parseInt(text1.getText().toString()) *
                    Integer.parseInt(text2.getText().toString()) *
                    Integer.parseInt(text3.getText().toString()) *
                    Integer.parseInt(text4.getText().toString());
            Toast.makeText(this, "Prod: " + prod, Toast.LENGTH_LONG).show();
        });
    }

    private void returnToCallingActivity(int result) {
        Log.d(Constants.TAG, "Secondary activity returnToCallingActivity()");
        Intent intent = null;
        // intent = new Intent(); intent.setExtras();
        setResult(result, intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constants.TAG, "Secondary activity onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Constants.TAG, "Secondary activity onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constants.TAG, "Secondary activity onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Constants.TAG, "Secondary activity onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Constants.TAG, "Secondary activity onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG, "Secondary activity onDestroy()");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(Constants.TAG, "Secondary activity onSaveInstanceState()");

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(Constants.TAG, "Secondary activity onRestoreInstanceState()");
    }
}