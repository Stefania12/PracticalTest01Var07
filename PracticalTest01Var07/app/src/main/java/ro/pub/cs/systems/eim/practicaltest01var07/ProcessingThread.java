package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

public class ProcessingThread extends Thread {
    private Context context;
    private AtomicBoolean isRunning = new AtomicBoolean(true);

    public ProcessingThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.d(Constants.TAG, "Thread has started! PID: " + android.os.Process.myPid() +
                " TID: " + android.os.Process.myTid());

        while (isRunning.get()) {
            sendMessage();
            sleep();
        }
        Log.d(Constants.TAG, "Thread has stopped!");
    }

    private void sleep() {
        try {
            sleep(Constants.SLEEP_MS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        Log.d(Constants.TAG, "Sent Message");
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_NAME);
        //intent.putExtra(key,value);
        intent.putExtra("text1", String.valueOf((int)Math.floor(Math.random()*10)));
        intent.putExtra("text2", String.valueOf((int)Math.floor(Math.random()*10)));
        intent.putExtra("text3", String.valueOf((int)Math.floor(Math.random()*10)));
        intent.putExtra("text4", String.valueOf((int)Math.floor(Math.random()*10)));
        context.sendBroadcast(intent);
    }

    public void stopThread() {
        Log.d(Constants.TAG, "Stopped Thread");
        isRunning.set(false);
    }
}
