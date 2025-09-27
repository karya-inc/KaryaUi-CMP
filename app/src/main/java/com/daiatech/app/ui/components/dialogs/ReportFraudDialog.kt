package com.daiatech.app.ui.components.dialogs

import android.content.ActivityNotFoundException
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import com.daiatech.app.ui.components.modals.dialogs.DialogActionAlignment
import com.daiatech.app.ui.components.modals.dialogs.KAlertDialog
import com.daiatech.app.ui.util.core.Constants.KARYA_OPERATIONS_EMAIL
import com.daiatech.ui.R

@Composable
fun ReportFraudDialog(modifier: Modifier = Modifier, showDialog: (Boolean) -> Unit) {
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = "mailto:$KARYA_OPERATIONS_EMAIL".toUri()
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.report_fraud))
    }
    KAlertDialog(
        title = stringResource(R.string.how_to_report_fraud),
        message = stringResource(R.string.email_us) + "\n" + KARYA_OPERATIONS_EMAIL,
        firstButtonText = stringResource(R.string.email),
        onFirstButtonClicked = {
            try {
                context.startActivity(Intent.createChooser(intent, KARYA_OPERATIONS_EMAIL))
            } catch (e: ActivityNotFoundException) {
                Log.w("ReportFraudDialog", "No application found to handle email")
            }
        },
        secondButtonText = stringResource(R.string.back),
        onSecondButtonClicked = {
            showDialog(false)
        },
        alignment = DialogActionAlignment.VERTICAL,
        modifier = modifier
    )
}
