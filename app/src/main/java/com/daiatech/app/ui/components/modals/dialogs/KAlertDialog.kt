package com.daiatech.app.ui.components.modals.dialogs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daiatech.app.ui.components.buttons.ButtonVariation
import com.daiatech.app.ui.components.buttons.KButton
import com.daiatech.app.ui.theme.errorColor
import com.daiatech.ui.R

@Composable
fun KAlertDialog(
    modifier: Modifier = Modifier,
    alertIcon: Painter? = painterResource(R.drawable.ic_info),
    title: String?,
    message: String?,
    content: @Composable () -> Unit = {},
    firstButtonText: String,
    isFirstButtonEnabled: Boolean = true,
    onFirstButtonClicked: () -> Unit,
    secondButtonText: String,
    onSecondButtonClicked: () -> Unit = {},
    alignment: DialogActionAlignment = DialogActionAlignment.HORIZONTAL
) {
    KDialogLayout(
        modifier = modifier,
        onDismissRequest = {},
        icon = {
            if (alertIcon != null) {
                Icon(
                    painter = alertIcon,
                    contentDescription = null,
                    tint = errorColor
                )
            }
        },
        title = {
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        content = {
            if (message != null) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = message,
                    color = Color.DarkGray,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            content()
        },
        actions = { buttonModifier, reversed ->
            val buttons = listOf<@Composable () -> Unit>(
                {
                    KButton(
                        modifier = buttonModifier,
                        content = firstButtonText,
                        buttonVariation = ButtonVariation.PrimaryButtonRegular,
                        isEnabled = isFirstButtonEnabled,
                        onClick = { onFirstButtonClicked() }
                    )
                },
                {
                    KButton(
                        modifier = buttonModifier,
                        content = secondButtonText,
                        buttonVariation = ButtonVariation.SecondaryButtonRegular,
                        isEnabled = true,
                        onClick = { onSecondButtonClicked() }
                    )
                }
            )
            val finalButtons = if (reversed) buttons.reversed() else buttons
            finalButtons.forEach { it() }
        },
        actionsAlignment = alignment
    )
}

@Preview
@Composable
private fun AlertDialogPrev1() {
    KAlertDialog(
        title = "Internet access is required",
        message = "Allow internet for future updates",
        firstButtonText = "Allow",
        onFirstButtonClicked = {},
        secondButtonText = "No need",
        onSecondButtonClicked = {},
        alignment = DialogActionAlignment.HORIZONTAL
    )
}

@Preview
@Composable
private fun AlertDialogPrev2() {
    KAlertDialog(
        title = "Internet access is required",
        message = "Allow internet for future updates",
        firstButtonText = "Allow",
        onFirstButtonClicked = {},
        secondButtonText = "No need",
        onSecondButtonClicked = {},
        alignment = DialogActionAlignment.VERTICAL
    )
}
