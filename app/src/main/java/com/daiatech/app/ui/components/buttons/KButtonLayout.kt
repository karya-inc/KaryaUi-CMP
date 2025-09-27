package com.daiatech.app.ui.components.buttons

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun KButtonLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
    leftIcon: @Composable () -> Unit = {},
    rightIcon: @Composable () -> Unit = {},
    contentPaddingValues: PaddingValues = ButtonDefaults.ContentPadding,
    buttonVariation: ButtonVariation,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    borderModifier: Modifier = Modifier
) {
    Button(
        onClick = { onClick() },
        modifier = modifier.height(buttonVariation.buttonSize.value).then(borderModifier),
        enabled = isEnabled,
        shape = RoundedCornerShape(8.dp),
        colors = buttonVariation.colors,
        border = buttonVariation.borderColor,
        contentPadding = contentPaddingValues,
        interactionSource = interactionSource
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            leftIcon()
            content()
            rightIcon()
        }
    }
}
