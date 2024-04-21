package com.example.broadcastreceivers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReceiver = new CustomReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        this.registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    public void sendCustomBroadcast(View view) {
        Log.d("MainActivity", "sendCustomBroadcast() called");
        Intent customIntent = new Intent("com.example.broadcastreceivers.CUSTOM_ACTION");
        sendBroadcast(customIntent);
    }

    public class CustomReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String intentAction = intent.getAction();

            if (intentAction != null) {
                String toastMessage = "Unknown intent action";
                switch (intentAction) {
                    case Intent.ACTION_POWER_CONNECTED:
                        toastMessage = "Power Connected!";
                        break;
                    case Intent.ACTION_POWER_DISCONNECTED:
                        toastMessage = "Power Disconnected";
                        break;
                }
                Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();



            }
        }
    }
}
