package com.daiatech.app.ui.components.modals.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.daiatech.app.ui.theme.onSecondary
import com.daiatech.app.ui.theme.roundedDialogShape

@Composable
fun KDialogLayout(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    icon: @Composable () -> Unit = {},
    title: @Composable RowScope.() -> Unit = {},
    content: @Composable () -> Unit = {},
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    actions: (@Composable (Modifier, Boolean) -> Unit)? = null,
    actionsAlignment: DialogActionAlignment = DialogActionAlignment.HORIZONTAL
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Column(
            modifier
                .fillMaxWidth(0.8f)
                .clip(roundedDialogShape)
                .background(onSecondary)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                icon()
                title()
            }
            content()
            if (actions != null) {
                HorizontalDivider(Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp).fillMaxWidth().border(1.dp, Color.LightGray))
                when (actionsAlignment) {
                    DialogActionAlignment.VERTICAL -> Column(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        actions(Modifier.fillMaxWidth(), false)
                    }
                    DialogActionAlignment.HORIZONTAL -> Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        actions(Modifier.weight(1f), true)
                    }

                    DialogActionAlignment.NONE -> Column(
                        modifier = Modifier.padding(16.dp).fillMaxWidth()
                    ) {
                        actions(Modifier.fillMaxWidth(), false)
                    }
                }
            }
        }
    }
}
