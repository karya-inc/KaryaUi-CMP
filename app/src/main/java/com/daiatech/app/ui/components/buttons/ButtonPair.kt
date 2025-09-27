package com.daiatech.app.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daiatech.app.ui.components.spacers.VerticalSpacer
import com.daiatech.app.ui.theme.Dimens
import com.daiatech.app.ui.theme.UITheme
import com.daiatech.app.ui.theme.backgroundDark
import com.daiatech.app.ui.theme.errorColor
import com.daiatech.ui.R

@Composable
fun ButtonPair(
    modifier: Modifier = Modifier,
    positiveText: String,
    onPositive: () -> Unit,
    positiveEnabled: Boolean,
    positiveIcon: Painter = painterResource(id = R.drawable.ic_arrow_forward),
    negativeText: String,
    onNegative: () -> Unit,
    negativeEnabled: Boolean,
    negativeIcon: Painter = painterResource(id = R.drawable.ic_close),
    error: String? = null,
    onErrorUpdate: (String?) -> Unit = {},
    actionButton: (@Composable () -> Unit)? = null
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(backgroundDark)
            .padding(Dimens.S)
    ) {
        error?.let {
            Text(text = error, color = errorColor, style = MaterialTheme.typography.labelSmall)
        }
        VerticalSpacer(8.dp)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            actionButton?.let { it() }
            KButton(
                modifier = Modifier.weight(1f),
                content = negativeText,
                leftIcon = negativeIcon,
                buttonVariation = ButtonVariation.ErrorButtonRegular,
                isEnabled = negativeEnabled,
                onClick = {
                    onErrorUpdate(null)
                    onNegative()
                }
            )

            KButton(
                modifier = Modifier.weight(1f),
                content = positiveText,
                rightIcon = positiveIcon,
                buttonVariation = ButtonVariation.PrimaryButtonRegular,
                isEnabled = positiveEnabled,
                onClick = {
                    onErrorUpdate(null)
                    onPositive()
                }
            )
        }
    }
}

@Preview
@Composable
fun ResponsePairButtonPrev() {
    UITheme {
        Column(Modifier.padding(16.dp)) {
            ButtonPair(
                positiveText = "Accept",
                onPositive = { /*TODO*/ },
                positiveEnabled = true,
                negativeText = "Reject",
                onNegative = { /*TODO*/ },
                negativeEnabled = true
            )
        }
    }
}

@Preview
@Composable
fun ResponsePairButtonPrev1() {
    UITheme {
        Column(Modifier.padding(16.dp)) {
            ButtonPair(
                positiveText = "Accept",
                onPositive = { /*TODO*/ },
                positiveEnabled = true,
                negativeText = "Reject",
                onNegative = { /*TODO*/ },
                negativeEnabled = true,
                negativeIcon = painterResource(id = R.drawable.ic_arrow_back_24),
                error = "This is an error message"
            )
        }
    }
}
