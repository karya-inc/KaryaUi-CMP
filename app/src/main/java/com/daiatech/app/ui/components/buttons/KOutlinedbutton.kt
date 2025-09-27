package com.daiatech.app.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daiatech.app.ui.theme.Dimens
import com.daiatech.app.ui.theme.UITheme

@Composable
fun KOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(Dimens.S),
    colors: ButtonColors,
    elevation: ButtonElevation? = null,
    border: BorderStroke? = ButtonDefaults.outlinedButtonBorder(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        onClick,
        modifier,
        enabled,
        shape,
        colors,
        elevation,
        border,
        contentPadding,
        interactionSource,
        content = {
            Row(
                Modifier.padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                content()
            }
        }
    )
}

@Preview
@Composable
private fun KOutlinedButtonPrev() {
    UITheme {
        Column {
            KOutlinedButton(
                onClick = { },
                colors = primaryButtonColors
            ) {
                Text(text = "Primary")
            }
            KOutlinedButton(
                onClick = { },
                enabled = false,
                colors = primaryButtonColors
            ) {
                Text(text = "Primary")
            }
        }
    }
}

@Preview
@Composable
private fun KOutlinedButtonPrev1() {
    UITheme {
        Column {
            KOutlinedButton(
                onClick = { },
                colors = secondaryButtonColors
            ) {
                Text(text = "Primary")
            }
            KOutlinedButton(
                onClick = { },
                enabled = false,
                colors = secondaryButtonColors
            ) {
                Text(text = "Primary")
            }
        }
    }
}

@Preview
@Composable
private fun KOutlinedButtonPrev2() {
    UITheme {
        Column {
            KOutlinedButton(
                onClick = { },
                colors = errorButtonColors
            ) {
                Text(text = "Primary")
            }
            KOutlinedButton(
                onClick = { },
                enabled = false,
                colors = errorButtonColors
            ) {
                Text(text = "Primary")
            }
        }
    }
}
