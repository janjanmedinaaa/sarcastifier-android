package com.medina.juanantonio.sarcastifier.common.extensions

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_HIGH
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import com.medina.juanantonio.sarcastifier.R
import com.medina.juanantonio.sarcastifier.common.Constants
import com.medina.juanantonio.sarcastifier.common.Constants.CHANNELS.SARCASTIFIER_INPUT_CHANNEL
import com.medina.juanantonio.sarcastifier.common.receivers.SarcastifierReceiver

fun Context.createNotification() {
    val notificationManagerCompat = NotificationManagerCompat.from(this)

    val remoteInputMessage = RemoteInput.Builder(Constants.BUNDLE_KEY.MESSAGE_INPUT)
        .setLabel(getString(R.string.input_placeholder))
        .build()

    val replyIntent = Intent(this, SarcastifierReceiver::class.java)
    val replyPendingIntent = PendingIntent.getBroadcast(
        this,
        0,
        replyIntent,
        0
    )

    val replyAction = NotificationCompat.Action.Builder(
        R.drawable.ic_rick_sanchez_black,
        getString(R.string.action_label),
        replyPendingIntent
    ).addRemoteInput(remoteInputMessage).build()

    val notification = NotificationCompat.Builder(this, SARCASTIFIER_INPUT_CHANNEL)
        .setSmallIcon(R.drawable.ic_rick_sanchez_black)
        .setContentTitle(getString(R.string.app_name))
        .setContentText(getString(R.string.notification_label))
        .setPriority(PRIORITY_HIGH)
        .setOngoing(true)
        .setColor(Color.WHITE)
        .setGroup(SARCASTIFIER_INPUT_CHANNEL)
        .addAction(replyAction)

    notificationManagerCompat.notify(1, notification.build())
}