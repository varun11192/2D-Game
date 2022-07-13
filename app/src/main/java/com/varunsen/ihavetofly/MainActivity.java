package com.varunsen.ihavetofly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isMute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.play_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });

        TextView highscore = findViewById(R.id.highScore_tv);

        SharedPreferences sharedPreferences  = getSharedPreferences("game", MODE_PRIVATE);
        highscore.setText("HighsScore: " + sharedPreferences.getInt("highscore", 0));
        isMute = sharedPreferences.getBoolean("isMute", false);

        ImageView volumeControl = findViewById(R.id.volume_cntrl);

        if (isMute){
            volumeControl.setImageResource(R.drawable.ic_baseline_volume_off_24);
        } else {
            volumeControl.setImageResource(R.drawable.ic_baseline_volume_up_24);
        }

        volumeControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isMute = !isMute;
                if (isMute){
                    volumeControl.setImageResource(R.drawable.ic_baseline_volume_off_24);
                } else {
                    volumeControl.setImageResource(R.drawable.ic_baseline_volume_up_24);
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isMute", isMute);
                editor.apply();
            }
        });

    }
}