package com.medina.juanantonio.sarcastifier.common.receivers

import android.content.*
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.core.app.RemoteInput
import com.medina.juanantonio.sarcastifier.R
import com.medina.juanantonio.sarcastifier.common.Constants.BUNDLE_KEY.MESSAGE_INPUT
import com.medina.juanantonio.sarcastifier.common.extensions.createNotification
import com.medina.juanantonio.sarcastifier.common.extensions.sarcastify

class SarcastifierReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val remoteInputBundle = RemoteInput.getResultsFromIntent(intent)

        remoteInputBundle?.let {
            val inputMessage = remoteInputBundle.getCharSequence(MESSAGE_INPUT).toString()
            Toast.makeText(
                context,
                context.getText(R.string.copied_to_clipboard),
                Toast.LENGTH_SHORT
            ).show()

            val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(
                context.getString(R.string.clipboard_label),
                inputMessage.sarcastify()
            )
            clipboard.setPrimaryClip(clip)

            context.createNotification()
        }
    }
}