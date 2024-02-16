package com.findzo.enigma.anything;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Notification;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import static com.findzo.enigma.anything.App.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    private BluetoothAdapter BA = BluetoothAdapter.getDefaultAdapter();

    private BroadcastReceiver bcast = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                String name = BA.getName();
                if (name == "Findzo") {
                    String text = "Bluetooth Connected";
                    Toast.makeText(context, "Bluetooth Connected", Toast.LENGTH_LONG).show();

                    Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

                    Intent notificationIntent = new Intent(context, MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

                    Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                            .setContentTitle("Findzo")
                            .setContentText(text)
                            .setSmallIcon(R.drawable.image)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .setContentIntent(pendingIntent)
                            .setSound(defaultSoundUri)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                            .setFullScreenIntent(null, true)
                            .build();

                    notificationManager.notify(30, notification);
                    Log.d("onReceive", action);
                    TextView t = (TextView) findViewById(R.id.status_change);
                    t.setText("Your valuables are being tracked:)");
                } else {
                    String text = "Bluetooth not connected to Findzo your valuables can't be tracked";
                    Toast.makeText(context, "Bluetooth Not to Findzo", Toast.LENGTH_LONG).show();

                    Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

                    Intent notificationIntent = new Intent(context, MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

                    Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                            .setContentTitle("Findzo")
                            .setContentText(text)
                            .setSmallIcon(R.drawable.image)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .setContentIntent(pendingIntent)
                            .setSound(defaultSoundUri)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true)
                            .setFullScreenIntent(null, true)
                            .build();

                    notificationManager.notify(40, notification);
                    TextView t = (TextView) findViewById(R.id.status_change);
                    t.setText("Bluetooth not connected to Findzo:o");
                }
            } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                TextView text = (TextView) findViewById(R.id.status_change);
                text.setText("Finzo Disconnected:(");
            } else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
                if (state == BluetoothAdapter.STATE_OFF) {
                    Toast.makeText(context, "Bluetooth is off", Toast.LENGTH_SHORT).show();
                    Log.d("BroadcastActions", "Bluetooth is off");
                } else if (state == BluetoothAdapter.STATE_TURNING_OFF) {
                    Toast.makeText(context, "Bluetooth is turning off", Toast.LENGTH_SHORT).show();
                    Log.d("BroadcastActions", "Bluetooth is turning off");
                } else if (state == BluetoothAdapter.STATE_ON) {
                    Log.d("BroadcastActions", "Bluetooth is on");
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.c3));


        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);


        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        IntentFilter fltr = new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED);
        fltr.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        fltr.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        fltr.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(bcast, fltr);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.findzo:
                Toast.makeText(MainActivity.this, "What is findzo", Toast.LENGTH_LONG).show();
                break;
            case R.id.help:
                Toast.makeText(MainActivity.this, "help", Toast.LENGTH_LONG).show();
                break;
            case R.id.shop:
                Intent s = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Espressif-ESP32-DEVKITC-ESP32-Core-Board/dp/B01N0SB08Q"));
                startActivity(s);
                break;
            case R.id.about:
                Intent i = new Intent(MainActivity.this, aboutus.class);
                startActivity(i);
                break;
            case R.id.contact:
                Toast.makeText(MainActivity.this, "Send", Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    public void startTracking(View view) {
        if (!BA.isEnabled()) {
            Intent i = new Intent(BA.ACTION_REQUEST_ENABLE);
            startActivity(i);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!BA.isEnabled()) {
                        TextView t = (TextView) findViewById(R.id.status_change);
                        t.setText("For your valueables to be tracked you need to enable bluetooth and connect to Findzo");
                    }
                }
            }, 5000);
        } else if (BA.isEnabled()) {
            String input = "Findzo is running";
            Intent serviceIntent = new Intent(this, BackgroundService.class);
            serviceIntent.putExtra("inputExtra", input);
            ContextCompat.startForegroundService(this, serviceIntent);
        }
    }

    public void stopTracking(View view) {
        Intent serviceIntent = new Intent(this, BackgroundService.class);
        stopService(serviceIntent);
    }

    public void contentinformation(View view) {
        Intent i = new Intent(MainActivity.this, contentInfo.class);
        startActivity(i);
    }
}
