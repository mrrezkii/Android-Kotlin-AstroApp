package com.kedirilagi.astro.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kedirilagi.astro.data.view.activity.StatusActivity

class AlarmService : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val i = Intent(context, StatusActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context!!.startActivity(i)
    }
}