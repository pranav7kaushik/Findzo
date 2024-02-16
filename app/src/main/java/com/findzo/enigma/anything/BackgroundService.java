package com.findzo.enigma.anything;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Date;

import static com.findzo.enigma.anything.App.CHANNEL_ID;

public class BackgroundService extends Service {
    private BroadcastReceiver bcast = new bcast();

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter fltr = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
        fltr.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        fltr.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        fltr.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(bcast, fltr);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(bcast);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Findzo")
                .setContentText(input)
                .setSmallIcon(R.drawable.image)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent)
                .build();

        int iD = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        startForeground(iD,notification);
        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}
