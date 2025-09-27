package com.daiatech.app.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.daiatech.ui.R

@Composable
fun KButton(
    modifier: Modifier = Modifier,
    content: String? = null,
    leftIcon: Painter? = null,
    rightIcon: Painter? = null,
    buttonVariation: ButtonVariation,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    KButtonLayout(
        modifier = modifier,
        content = {
            if (content != null) {
                Text(
                    text = content,
                    style = when (buttonVariation.textStyle) {
                        ButtonTextStyle.SMALL -> MaterialTheme.typography.labelMedium
                        ButtonTextStyle.REGULAR -> MaterialTheme.typography.labelLarge
                    }
                )
            }
        },
        leftIcon = {
            if (leftIcon != null) {
                Icon(
                    painter = leftIcon,
                    contentDescription = "left image",
                    modifier = Modifier.size(16.dp)
                )
            }
        },
        rightIcon = {
            if (rightIcon != null) {
                Icon(
                    painter = rightIcon,
                    contentDescription = "right image",
                    modifier = Modifier.size(16.dp)
                )
            }
        },
        buttonVariation = buttonVariation,
        isEnabled = isEnabled,
        onClick = onClick
    )
}

@Preview
@Composable
private fun KButtonEnablePrev() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp)
    ) {
        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.PrimaryButtonRegular,
            isEnabled = true,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.PrimaryButtonSmall,
            isEnabled = true,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.SecondaryButtonSmall,
            isEnabled = true,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.SecondaryButtonRegular,
            isEnabled = true,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.ErrorButtonSmall,
            isEnabled = true,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.ErrorButtonRegular,
            isEnabled = true,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.TertiaryButtonRegular,
            isEnabled = true,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.AccentOutlineRegular,
            isEnabled = true,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.AccentFillRegular,
            isEnabled = true,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun KButtonDisablePrev() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp)
    ) {
        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.PrimaryButtonRegular,
            isEnabled = false,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.PrimaryButtonSmall,
            isEnabled = false,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.SecondaryButtonSmall,
            isEnabled = false,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.SecondaryButtonRegular,
            isEnabled = false,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.ErrorButtonSmall,
            isEnabled = false,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.ErrorButtonRegular,
            isEnabled = false,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.TertiaryButtonRegular,
            isEnabled = false,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.AccentOutlineRegular,
            isEnabled = false,
            onClick = {}
        )

        KButton(
            content = "Click Here",
            leftIcon = painterResource(R.drawable.ic_alert),
            rightIcon = painterResource(R.drawable.ic_alert),
            buttonVariation = ButtonVariation.AccentFillRegular,
            isEnabled = false,
            onClick = {}
        )
    }
}
