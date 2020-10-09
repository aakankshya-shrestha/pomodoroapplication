package com.example.demo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        rainSong();
        coffeeSong();


    }



    private void rainSong(){
        final MediaPlayer bg_sound = MediaPlayer.create(this, R.raw.rain_sound);
        final Button playSound = (Button) this.findViewById(R.id.switchRain);
        bg_sound.setLooping(true);
        playSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bg_sound.isPlaying())
                    bg_sound.stop();
                else
                    bg_sound.start();
            }
        });

    }


    private void coffeeSong(){
        final MediaPlayer bg_sound2 = MediaPlayer.create(this, R.raw.coffee_shop);
        final Button playSound = (Button) this.findViewById(R.id.switchCoffee);
        bg_sound2.setLooping(true);
        playSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bg_sound2.isPlaying())
                    bg_sound2.stop();
                else
                    bg_sound2.start();
            }
        });
    }



}
