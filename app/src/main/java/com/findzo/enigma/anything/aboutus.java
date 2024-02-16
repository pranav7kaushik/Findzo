package com.findzo.enigma.anything;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class aboutus extends AppCompatActivity implements NavigationMenuItemView.OnCreateContextMenuListener, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.c3));


        drawerLayout = findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);


        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(aboutus.this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {switch(item.getItemId()){
        case R.id.findzo:
            Toast.makeText(this,"What is findzo",Toast.LENGTH_LONG).show();
            break;
        case R.id.help:
            Toast.makeText(this,"help",Toast.LENGTH_LONG).show();
            break;
        case R.id.shop:
            Intent s = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Espressif-ESP32-DEVKITC-ESP32-Core-Board/dp/B01N0SB08Q"));
            startActivity(s);
            break;
        case R.id.about:
            Intent i = new Intent(this,aboutus.class);
            startActivity(i);
            break;
        case R.id.contact:
            Toast.makeText(this,"Send",Toast.LENGTH_LONG).show();
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

    public void backToMain(View view) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void pranavLinkedin(View view) {
        Intent s = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/pranav7kaushik"));
        startActivity(s);
    }

}
