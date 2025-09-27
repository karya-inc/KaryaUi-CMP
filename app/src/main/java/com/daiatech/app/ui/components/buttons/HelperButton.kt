package com.daiatech.app.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daiatech.app.ui.theme.UITheme
import com.daiatech.app.ui.theme.primary
import com.daiatech.app.ui.theme.primaryDark

@Composable
fun HelperButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isOutlined: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = if (isOutlined) Color.White else primaryDark,
        contentColor = if (isOutlined) primaryDark else Color.White,
        disabledContainerColor = if (isOutlined) Color.Transparent else primary.copy(0.9f),
        disabledContentColor = if (isOutlined) Color.Gray else Color.White
    ),
    content: @Composable RowScope.() -> Unit
) {
    val border = if (isOutlined) {
        BorderStroke(
            width = 1.dp,
            color = if (enabled) primaryDark else Color.Gray
        )
    } else {
        null
    }

    val backgroundColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor

    val pressed by interactionSource.collectIsPressedAsState()

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                border ?: BorderStroke(0.dp, Color.Transparent),
                RoundedCornerShape(8.dp)
            )
            .background(backgroundColor)
            .padding(contentPadding)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = ripple(),
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}

@Preview
@Composable
fun HelperButtonPreview() {
    UITheme {
        Column {
            HelperButton(
                onClick = { },
                modifier = Modifier.widthIn(min = 256.dp),
                enabled = true
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Resend OTP")
                Text(text = "(30 s)")
            }

            // Example of Change Number button
            HelperButton(
                onClick = { },
                isOutlined = true,
                modifier = Modifier.widthIn(min = 256.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Change Number")
            }
        }
    }
}
