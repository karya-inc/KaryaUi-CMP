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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daiatech.app.ui.components.buttons.ButtonVariation
import com.daiatech.app.ui.components.buttons.KButton
import com.daiatech.app.ui.theme.errorColor
import com.daiatech.ui.R

@Composable
fun KErrorDialog(
    modifier: Modifier = Modifier,
    errorIcon: Int? = R.drawable.ic_info,
    title: String,
    message: String?,
    buttonText: String = stringResource(R.string.understood),
    onButtonClicked: () -> Unit,
    alignment: DialogActionAlignment = DialogActionAlignment.NONE,
    content: @Composable () -> Unit = {}
) {
    KDialogLayout(
        modifier = modifier,
        onDismissRequest = {},
        icon = {
            if (errorIcon != null) {
                Icon(
                    painter = painterResource(errorIcon),
                    contentDescription = null,
                    tint = errorColor
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
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
        actions = { buttonModifier, reverse ->
            KButton(
                modifier = buttonModifier,
                content = buttonText,
                buttonVariation = ButtonVariation.PrimaryButtonRegular,
                isEnabled = true,
                onClick = { onButtonClicked() }
            )
        },
        actionsAlignment = alignment
    )
}

@Preview
@Composable
private fun AlertDialogPrev1() {
    KErrorDialog(
        title = "Error!!",
        message = "Some error",
        onButtonClicked = {}
    )
}

@Preview
@Composable
private fun AlertDialogPrev2() {
    KErrorDialog(
        title = "Error!!",
        message = null,
        onButtonClicked = {}
    )
}
