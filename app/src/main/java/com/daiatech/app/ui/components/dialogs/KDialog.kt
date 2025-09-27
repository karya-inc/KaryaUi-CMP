package com.daiatech.app.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.daiatech.app.ui.components.buttons.KTextButton
import com.daiatech.app.ui.theme.UITheme
import com.daiatech.app.ui.theme.backgroundPrompt
import com.daiatech.app.ui.theme.backgroundPrompt1

@Composable
fun KDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    dimColor: Color = Color.Black.copy(alpha = 0f),
    actions: @Composable (RowScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(dimColor)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    if (properties.dismissOnClickOutside) {
                        onDismissRequest()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(backgroundPrompt)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { /* Prevent click propagation */ }
            ) {
                Column(Modifier.padding(16.dp)) { content() }
                actions?.let {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(backgroundPrompt1)
                            .padding(16.dp)
                    ) {
                        actions()
                    }
                }
            }
        }
    }
}

@Composable
fun KDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    actions: @Composable (RowScope.() -> Unit)? = null,
    title: (@Composable () -> Unit),
    content: @Composable ColumnScope.() -> Unit
) {
    KDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        properties = properties,
        actions = actions,
        content = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ProvideTextStyle(value = MaterialTheme.typography.headlineSmall, content = title)
            }
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    )
}

@Preview
@Composable
fun KDialogPrev() {
    UITheme {
        Column(Modifier.padding(16.dp)) {
            KDialog(
                onDismissRequest = { },
                actions = {
                    KTextButton(onClick = { }) {
                        Text(text = "Dismiss")
                    }
                }
            ) {
                Column {
                    Text(
                        text = "Null error occurred",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Some random error message!")
                }
            }
        }
    }
}
