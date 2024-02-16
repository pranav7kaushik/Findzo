package com.findzo.enigma.anything;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class contentInfo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    EditText edit1, edit2;
    String st1, st2;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_info);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.c3));


        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);


        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(contentInfo.this);


        save = findViewById(R.id.button);
        edit1 = findViewById(R.id.status_change);
        edit2 = findViewById(R.id.status_change_valueables);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        st1 = pref.getString("st1", "");
        edit1.setText(st1);
        st2 = pref.getString("st2", "");
        edit2.setText(st2);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.findzo:
                Toast.makeText(this, "What is findzo", Toast.LENGTH_LONG).show();
                break;
            case R.id.help:
                Toast.makeText(this, "help", Toast.LENGTH_LONG).show();
                break;
            case R.id.shop:
                Intent s = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Espressif-ESP32-DEVKITC-ESP32-Core-Board/dp/B01N0SB08Q"));
                startActivity(s);
                break;
            case R.id.about:
                Intent abt = new Intent(this,aboutus.class);
                startActivity(abt);
                break;
            case R.id.contact:
                Toast.makeText(this, "Send", Toast.LENGTH_LONG).show();
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

    public void save(View view) {
        st1 = edit1.getText().toString();
        st2 = edit2.getText().toString();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("st1", st1);
        editor.putString("st2", st2);
        editor.apply();
        Toast.makeText(this, "Saved the Information.", Toast.LENGTH_LONG).show();
    }
}
