package com.kedirilagi.astro.service

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class FCMService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)

        val refreshToken = FirebaseMessaging.getInstance().token

        Timber.e("refresh token : $refreshToken")
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        showNotification(p0.notification!!.title.toString(), p0.notification!!.body.toString())
    }

    private fun showNotification(title: String, message: String) {
        val builder = NotificationCompat.Builder(this, "test-notif")
            .setContentText(title)
            .setContentTitle(message)

        val manager = NotificationManagerCompat.from(this)
        manager.notify(1, builder.build())
    }
}