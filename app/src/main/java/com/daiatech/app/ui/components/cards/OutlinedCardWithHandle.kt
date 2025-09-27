package com.daiatech.app.ui.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daiatech.app.ui.theme.onPrimary
import com.daiatech.app.ui.theme.onSecondary
import com.daiatech.app.ui.theme.primaryDark

@Composable
fun OutlinedCardWithHandle(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CardDefaults.outlinedShape,
    colors: CardColors = CardDefaults.outlinedCardColors(onPrimary.copy(0.05f)),
    pillColors: CardColors = CardDefaults.outlinedCardColors(onSecondary),
    elevation: CardElevation = CardDefaults.outlinedCardElevation(),
    border: BorderStroke = BorderStroke(0.1f.dp, onPrimary.copy(0.05f)),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    handleContent: (@Composable BoxScope.() -> Unit),
    content: @Composable ColumnScope.() -> Unit
) {
    var boxHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    Box(Modifier.padding(bottom = boxHeight / 2)) {
        OutlinedCard(
            onClick,
            modifier,
            enabled,
            shape,
            colors,
            elevation,
            border,
            interactionSource,
            content = {
                Column(Modifier.padding(bottom = boxHeight / 2, top = 8.dp)) {
                    content()
                }
            }
        )
        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .offset(y = boxHeight / 2)
                .background(pillColors.containerColor, CircleShape)
                .clip(CircleShape)
                .border(border, CircleShape)
                .onGloballyPositioned { boxHeight = with(density) { it.size.height.toDp() } }
        ) { handleContent() }
    }
}

@Preview
@Composable
private fun CollapsableCardWithHandle() {
    Surface {
        OutlinedCardWithHandle(onClick = { /*TODO*/ }, handleContent = {
            Text(
                text = "Expand",
                color = primaryDark,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )
        }) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Sample Text")
                Text("Sample Text")
                Text("Sample Text")
                Text("Sample Text")
                Text("Sample Text")
            }
        }
    }
}

@Preview
@Composable
private fun CollapsableCardWithHandlePrev1() {
    Surface {
        OutlinedCardWithHandle(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ },
            handleContent = {
                Text(
                    text = "Expand",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }
        ) {
        }
    }
}
