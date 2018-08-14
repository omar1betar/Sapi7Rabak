package com.betaromar.omar1betar.sapi7rabak;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView click, clickReset;
    int number = 0;
    int highScore;
    TextView view_number, textView, highScoretv;
    CheckBox afterPray;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_number = (TextView) findViewById(R.id.view_number);
        click = (ImageView) findViewById(R.id.click);
        afterPray = (CheckBox) findViewById(R.id.afterPray);
        textView = (TextView) findViewById(R.id.text_view);
        clickReset = (ImageView) findViewById(R.id.click_reset);
        highScoretv = (TextView) findViewById(R.id.last_score);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.click_button);
                number++;
                click.startAnimation(myAnim);
                view_number.setText(String.valueOf(number));
                SettingActivity settingActivity =new SettingActivity();
                checkbox_clicked();
            }
        });

        clickReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = 0;
                Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.reset_button);
                clickReset.startAnimation(myAnim);
                view_number.setText(String.valueOf(number));
            }
        });

        setHighScore();
        setView_number();
        // to show high score in open
        highScoretv.setText(Integer.toString(highScore));
    }

    @Override
    protected void onStart() {
        super.onStart();
        holdNumberView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getHighScore();
        holdNumberView();
    }

    private void setView_number() {
        SharedPreferences per = this.getSharedPreferences("mypref", Context.MODE_PRIVATE);
        number = per.getInt("number", 0);
        per.edit().putInt("number", number).apply();
        view_number.setText(String.valueOf(number));
    }

    private void holdNumberView() {
        SharedPreferences per = this.getSharedPreferences("mypref", Context.MODE_PRIVATE);
        per.edit().putInt("number", number).apply();
    }

    private void setHighScore() {
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        highScore = prefs.getInt("score", 0);
    }

    private void getHighScore() {
        //save the value on shared pref while paused
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        if (highScore > number) {
            highScoretv.setText(Integer.toString(highScore));
        } else {
            highScore = number;
            highScoretv.setText(Integer.toString(highScore));
            prefs.edit().putInt("score", highScore).apply();
        }
    }

    public void checkbox_clicked() {
        if (afterPray.isChecked()) {

            textView = (TextView) findViewById(R.id.text_view);

            if (number == 1) {
                textView.setText("سبحان الله\n سبحان الله \n سبحان الله \n سبحان الله ");
            }
            if (number == 33) {
                useVibrtion();
                textView.setText("الحمد الله\n الحمد لله \n الحمد لله \n الحمد لله ");
            }
            if (number == 66) {
                useVibrtion();
                textView.setText("الله اكبر \n الله اكبر \n الله اكبر \n الله اكبر ");
            }
            if (number == 99) {
                useVibrtion();
                textView.setText("لا اله الا الله \n لا اله الا الله \n لا اله الا الله \n لا اله الا الله");
            }
        } else {
            textView.setText("سبحان الله وبحمده\n سبحان الله العظيم\n أستغفر الله العظيم \n لا اله الا الله");
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.azkar_sapah) {
            Intent intent = new Intent(this, AzkarSpahActivity.class);
            startActivity(intent);
        }
        if (id == R.id.azkar_masaa) {
            Intent intent = new Intent(this, AzkarMasaaActivity.class);
            startActivity(intent);
        }
        if (id == R.id.settings) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }
        if (id == R.id.about_us) {

        }

        return super.onOptionsItemSelected(item);
    }
}











