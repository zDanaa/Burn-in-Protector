package com.example.burn_in_protector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import android.widget.CompoundButton;
import android.widget.Switch;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkOverlayPermission();

        //Intents for Apps
        Intent tikTokIntent = new Intent(MainActivity.this, TikTokService.class);
        Intent igIntent = new Intent(MainActivity.this, InstagramService.class);
        Intent whatsAppIntent = new Intent(MainActivity.this, WhatsAppService.class);

        // SWITCHES FOR APPS //
        // Instagram Switch
        Switch instagram = findViewById(R.id.Instagram);
        instagram.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {startService(igIntent);}
                else {stopService(igIntent);}
            }
        });
        // TikTok Switch
        Switch tiktok = findViewById(R.id.TikTok);
        tiktok.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {startService(tikTokIntent);}
                else {stopService(tikTokIntent);}
            }
        });
        // WhatsApp Switch
        Switch whatsApp = findViewById(R.id.WhatsApp);
        whatsApp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {startService(whatsAppIntent);}
                else {stopService(whatsAppIntent);}
            }
        });

    }

    // method to ask user to grant the Overlay permission
    public void checkOverlayPermission(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                // send user to the device settings
                Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(myIntent);
            }
        }
    }
    /*
    // check for permission again when user grants it from
    // the device settings, and start the service
    @Override
    protected void onResume() {
        super.onResume();
        startService();
    }
    */

}
