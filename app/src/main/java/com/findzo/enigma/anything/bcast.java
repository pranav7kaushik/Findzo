package com.findzo.enigma.anything;

import android.app.Notification;
import android.app.PendingIntent;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Date;

import static com.findzo.enigma.anything.App.CHANNEL_1_ID;

public class bcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
            String text = "Bluetooth Connected";
            Toast.makeText(context,"Bluetooth Connected",Toast.LENGTH_LONG).show();

            Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

            Intent notificationIntent = new Intent(context,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notificationIntent,0);

            Notification notification = new NotificationCompat.Builder(context,CHANNEL_1_ID)
                    .setContentTitle("Findzo")
                    .setContentText(text)
                    .setSmallIcon(R.drawable.image)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setContentIntent(pendingIntent)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setFullScreenIntent(null,true)
                    .build();

            notificationManager.notify(30,notification);

            Log.d("onReceive",action);
        }
        else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
            String text = "Bluetooth Disconnected collect your valuables where you left them!";
            Toast.makeText(context,"Bluetooth Disconnected",Toast.LENGTH_LONG).show();

            Uri defaultSoundUri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

            Intent notificationIntent = new Intent(context,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notificationIntent,0);

            Notification notification = new NotificationCompat.Builder(context,CHANNEL_1_ID)
                    .setContentTitle("Findzo")
                    .setContentText(text)
                    .setSmallIcon(R.drawable.image)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setContentIntent(pendingIntent)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("You forgot your valueables behind please go back and get them!")
                            .setBigContentTitle("Enigma")
                            .setSummaryText("Enigma is disconnected"))
                    .setFullScreenIntent(null,true)
                    .build();

            int iD = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
            notificationManager.notify(iD,notification);
            Toast.makeText(context,"Bluetooth Disconnected",Toast.LENGTH_LONG).show();
            Log.d("onReceive",action);
        }
    }
}
