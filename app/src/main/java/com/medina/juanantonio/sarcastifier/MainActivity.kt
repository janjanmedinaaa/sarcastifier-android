package com.medina.juanantonio.sarcastifier

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.medina.juanantonio.sarcastifier.common.Constants.CHANNELS.CHANNEL_NAME
import com.medina.juanantonio.sarcastifier.common.Constants.CHANNELS.SARCASTIFIER_INPUT_CHANNEL
import com.medina.juanantonio.sarcastifier.common.extensions.createNotification
import com.medina.juanantonio.sarcastifier.common.extensions.getEmojiByUnicode
import com.medina.juanantonio.sarcastifier.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.textviewInspiredFrom.text =
            getString(R.string.inspired_from, getEmojiByUnicode(0x1F525))

        binding.textviewLink.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.repo_link)))
            )
        }

        createNotificationChannels()
        createNotification()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val sarcastifierChannel =
                NotificationChannel(
                    SARCASTIFIER_INPUT_CHANNEL,
                    CHANNEL_NAME,
                    IMPORTANCE_HIGH
                )

            sarcastifierChannel.description = getString(R.string.channel_description)

            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(sarcastifierChannel)
        }
    }
}
