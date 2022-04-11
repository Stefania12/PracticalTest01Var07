package ro.pub.cs.systems.eim.practicaltest01var07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {
    EditText text1, text2, text3, text4;
    IntentFilter intentFilter = new IntentFilter();

    BroadcastReceiver messageBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.TAG, "Received message with action " + intent.getAction());

            setTexts(intent.getExtras().getString("text1"),
                    intent.getExtras().getString("text2"),
                    intent.getExtras().getString("text3"),
                    intent.getExtras().getString("text4"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Constants.TAG, "Main activity onCreate()");
        setContentView(R.layout.activity_practical_test01_var07_main);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);

        findViewById(R.id.switch_activity_button).setOnClickListener((v) -> {
            if (isInteger(text1.getText().toString()) &&
                    isInteger(text2.getText().toString()) &&
                    isInteger(text3.getText().toString()) &&
                    isInteger(text4.getText().toString())) {
                callActivityWithIntent();
            } else {
                Log.d(Constants.TAG, "nope");
            }
        });

        intentFilter.addAction(Constants.ACTION_NAME);
        this.callServiceStart();
    }

    private boolean isInteger(String x) {
        Log.d(Constants.TAG, "isInteger " + x);
        boolean gotError = false;

        try {
            int a = Integer.parseInt(x);
        } catch (NumberFormatException e) {
            gotError = true;
            Log.d(Constants.TAG, "not integer");
        }

        return !gotError;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constants.TAG, "Main activity onStart()");
    }

    @Override
    protected void onPause() {
        Log.d(Constants.TAG, "Main activity onPause()");
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constants.TAG, "Main activity onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Constants.TAG, "Main activity onResume()");
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Constants.TAG, "Main activity onRestart()");
    }

    @Override
    protected void onDestroy() {
        Log.d(Constants.TAG, "Main activity onDestroy()");
        this.stopService();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(Constants.TAG, "Main activity onSaveInstanceState()");

        //savedInstanceState.putString("key", "value");
        savedInstanceState.putString("text1", text1.getText().toString());
        savedInstanceState.putString("text2", text2.getText().toString());
        savedInstanceState.putString("text3", text3.getText().toString());
        savedInstanceState.putString("text4", text4.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(Constants.TAG, "Main activity onRestoreInstanceState()");

        //a = savedInstanceState.getString("key");
        text1.setText(savedInstanceState.getString("text1"));
        text2.setText(savedInstanceState.getString("text2"));
        text3.setText(savedInstanceState.getString("text3"));
        text4.setText(savedInstanceState.getString("text4"));
    }

    private void callActivityWithIntent() {
        Log.d(Constants.TAG, "Main Activity callActivityWithIntent()");
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class); // din aceeasi aplicatie
        // intent.putExtra(key, value);
        intent.putExtra("text1", text1.getText().toString());
        intent.putExtra("text2", text2.getText().toString());
        intent.putExtra("text3", text3.getText().toString());
        intent.putExtra("text4", text4.getText().toString());
        startActivityForResult(intent, Constants.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d(Constants.TAG, "Main Activity onActivityResult()");
        /*
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
         */

        if (resultCode == Activity.RESULT_OK) {
            Log.d(Constants.TAG, "Returned with RESULT_OK");
        }
        if (resultCode == Activity.RESULT_CANCELED) {
            Log.d(Constants.TAG, "Returned with RESULT_CANCELED");
        }
    }

    private void callServiceStart() {
        Log.d(Constants.TAG, "Main Activity callServiceStart()");
        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
        // intent.putExtra(key, value);
        getApplicationContext().startService(intent);
    }

    private void stopService() {
        Log.d(Constants.TAG, "Main Activity stopService()");
        Intent intent = new Intent(this, PracticalTest01Var07Service.class);
        stopService(intent);
    }

    public void setTexts(String _text1, String _text2, String _text3, String _text4) {
        text1.setText(_text1);
        text2.setText(_text2);
        text3.setText(_text3);
        text4.setText(_text4);
    }
}