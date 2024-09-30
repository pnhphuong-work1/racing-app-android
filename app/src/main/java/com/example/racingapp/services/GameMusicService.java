package com.example.racingapp.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.racingapp.R;

public class GameMusicService extends Service {
    MediaPlayer mediaPlayer;
    int randomSound = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        randomSound = (int) (Math.random() * 10);
        if (randomSound > 5) mediaPlayer = MediaPlayer.create(this, R.raw.racing_sound_1);
        if (randomSound <= 5) mediaPlayer = MediaPlayer.create(this, R.raw.racing_sound_2);
        mediaPlayer.start();
        return super.onStartCommand(intent,flags,startId);
        //return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
