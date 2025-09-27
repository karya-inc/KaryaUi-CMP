package com.daiatech.app.ui.util

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.Locale
import java.util.concurrent.TimeUnit

/** [DECIMAL_MATCH_REGEX] allows an empty string or a decimal input with leading or trailing zeros */
const val DECIMAL_MATCH_REGEX = "^\\d*\\.?\\d*|^\$"

/** [PLAIN_NUMERIC_MATCH_REGEX] allows an empty string or a decimal input with leading or trailing zeros */
const val PLAIN_NUMERIC_MATCH_REGEX = "(^[0-9]+\$|^\$)"

const val ENGLISH_MATCH_REGEX = "(^[a-zA-z\\s]+\$|^\$)"

fun formatDuration(durationMs: Long): String {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(durationMs)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(durationMs) % 60
    return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
}

@Composable
fun getUriFromRawResId(id: Int): Uri {
    val context = LocalContext.current
    return getUriFromRawResId(context, id)
}

fun getUriFromRawResId(context: Context, id: Int): Uri {
    return Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(context.packageName)
        .appendPath("$id")
        .build()
}

fun Context.shortToast(stringRes: Int) {
    Toast.makeText(this, this.getString(stringRes), Toast.LENGTH_SHORT).show()
}

fun Context.longToast(stringRes: Int) {
    Toast.makeText(this, this.getString(stringRes), Toast.LENGTH_LONG).show()
}
