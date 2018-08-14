package com.betaromar.omar1betar.sapi7rabak;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class SettingActivity extends AppCompatActivity {
    CheckBox useVibration,useSound;

    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        useVibration = (CheckBox)findViewById(R.id.use_vibration);
        useSound = (CheckBox)findViewById(R.id.use_sound);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();
        if(preferences.contains("checked") && preferences.getBoolean("checked",false) == true) {
            useVibration.setChecked(true);
        }else {
            useVibration.setChecked(false);
        }
        useVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                onClickChange();
            }
        });
        useSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
        //to dispay back button on nav bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // to display back button on nav bar
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    public boolean onClickChange(){
        final SharedPreferences.Editor editor = preferences.edit();
        if(useVibration.isChecked()) {
            editor.putBoolean("checked", true);
            editor.apply();
            useVibrtion();
        }else{
            editor.putBoolean("checked", false);
            editor.apply();
        }
        return true;
    }

    public void useVibrtion() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }
}
