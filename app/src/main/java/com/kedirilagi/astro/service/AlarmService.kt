package com.kedirilagi.astro.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import com.kedirilagi.astro.R

class AlarmService : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var mp = MediaPlayer.create(context, R.raw.alert)
        mp.start()
    }
}