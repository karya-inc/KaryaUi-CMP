/** Copyright (c) 2023 DAIA Tech Pvt Ltd */

package com.daiatech.app.ui.components.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.daiatech.app.ui.components.textfields.DashedNumbersTransformation
import com.daiatech.app.ui.theme.Dimens
import com.daiatech.app.ui.theme.UITheme
import com.daiatech.ui.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Composable
fun OTPVerificationDialog(
    phoneNumber: String,
    timeoutDurationSeconds: Int,
    otpLength: Int,
    onSubmit: (String) -> Boolean,
    onResend: () -> Unit
) {
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var otp by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    var otpTimeout by remember { mutableIntStateOf(timeoutDurationSeconds) }

    val startResendOTPTimeout: suspend CoroutineScope.() -> Unit = {
        while (isActive && otpTimeout > 0) {
            delay(1000)
            otpTimeout -= 1
        }
    }

    var resendOTPJob: Job? = null

    val resend = stringResource(id = R.string.resend_OTP)
    val resendText = remember(otpTimeout) {
        if (otpTimeout != 0) {
            "$resend (${otpTimeout}s)"
        } else {
            resend
        }
    }

    LaunchedEffect(Unit) {
        resendOTPJob = scope.launch { startResendOTPTimeout() }
    }

    NumericInputDialog(
        input = otp,
        onValueChange = { otp = it },
        visualTransformation = DashedNumbersTransformation(otpLength / 2),
        onDismissRequest = { },
        title = { Text(text = stringResource(R.string.otp_title)) },
        subtitle = {
            Image(
                painter = painterResource(id = R.drawable.ic_otp),
                contentDescription = null,
                modifier = Modifier.size(Dimens.XXXL)
            )
        },
        description = {
            Text(
                text = stringResource(
                    id = R.string.msg_input_otp,
                    otpLength,
                    phoneNumber
                ),
                textAlign = TextAlign.Center
            )
        },
        onNegative = {
            resendOTPJob?.cancel()
            resendOTPJob = scope.launch { startResendOTPTimeout() }
            onResend()
        },
        enableNegative = otpTimeout == 0,
        negativeText = resendText,
        onPositive = { onSubmit(otp) },
        errorMessage = errorMessage,
        setErrorState = { errorMessage = it },
        label = { Text(text = stringResource(id = R.string.otp)) },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
}

@Preview
@Composable
fun OTPVerificationDialogPreview() {
    UITheme {
        OTPVerificationDialog(
            phoneNumber = "9999999999",
            onSubmit = { true },
            otpLength = 6,
            timeoutDurationSeconds = 30
        ) {
        }
    }
}
