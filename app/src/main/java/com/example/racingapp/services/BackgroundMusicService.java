package com.example.racingapp.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.racingapp.R;

import java.security.Provider;

public class BackgroundMusicService extends Service {
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return null;}
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("BackgroundMusicService", "onStartCommand called");
        mediaPlayer = MediaPlayer.create(this, R.raw.background_sound);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        //return super.onStartCommand(intent,flags,startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("BackgroundMusicService", "onDestroy called");
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
