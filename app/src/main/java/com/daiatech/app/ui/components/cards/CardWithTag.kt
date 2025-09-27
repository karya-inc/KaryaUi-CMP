package com.daiatech.app.ui.components.cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.daiatech.ui.R

private val wrapHeight = 4.dp
private val tagHeight = 24.dp
private val tagBoxSize = 72.dp

data class TagCardColor(
    val tagColor: Color,
    val tagContainerColor: Color,
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
) {
    companion object {
        @Composable
        fun default(
            tagColor: Color = Color(0xFF00497C),
            tagContainerColor: Color = Color(0xFF9ECAFF),
            containerColor: Color = CardDefaults.elevatedCardColors().containerColor,
            contentColor: Color = CardDefaults.elevatedCardColors().contentColor,
            disabledContainerColor: Color = CardDefaults.elevatedCardColors().disabledContainerColor,
            disabledContentColor: Color = CardDefaults.elevatedCardColors().disabledContentColor
        ): TagCardColor {
            return TagCardColor(
                tagColor = tagColor,
                tagContainerColor = tagContainerColor,
                containerColor = containerColor,
                contentColor = contentColor,
                disabledContainerColor = disabledContainerColor,
                disabledContentColor = disabledContentColor
            )
        }
    }
}

@Composable
fun CardWithTag(
    tag: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CardDefaults.elevatedShape,
    colors: TagCardColor = TagCardColor.default(),
    elevation: CardElevation = CardDefaults.elevatedCardElevation(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    tagDecoration: @Composable (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(modifier = Modifier.padding(wrapHeight)) {
        val mod = remember(tag) {
            if (tag != null) {
                modifier.sizeIn(tagBoxSize + wrapHeight, tagBoxSize + wrapHeight)
            } else {
                modifier
            }
        }
        ElevatedCard(
            onClick = onClick,
            modifier = mod,
            shape = shape,
            enabled = enabled,
            elevation = elevation,
            interactionSource = interactionSource,
            colors = CardDefaults.elevatedCardColors(
                contentColor = colors.contentColor,
                containerColor = colors.containerColor,
                disabledContentColor = colors.disabledContentColor,
                disabledContainerColor = colors.disabledContainerColor
            ),
            content = content
        )
        if (tag != null) {
            Tag(
                tag = tag,
                contentColor = colors.tagColor,
                containerColor = colors.tagContainerColor,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(wrapHeight, (-wrapHeight)),
                tagDecoration = tagDecoration,
                tagHeight = tagHeight,
                size = tagBoxSize
            )
        }
    }
}

@Composable
private fun Tag(
    tag: String,
    contentColor: Color,
    containerColor: Color,
    tagDecoration: (@Composable () -> Unit)?,
    modifier: Modifier = Modifier,
    tagHeight: Dp,
    size: Dp
) {
    val density = LocalDensity.current
    val rWidth = with(density) { tagHeight.toPx() }
    val wrapHeight = remember { with(density) { wrapHeight.toPx() } }

    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(2.dp))
    ) {
        Canvas(modifier = Modifier.size(size)) {
            val path = Path().apply {
                lineTo(rWidth, 0f)
                lineTo(this@Canvas.size.width, this@Canvas.size.height - rWidth)
                lineTo(this@Canvas.size.width, this@Canvas.size.height)
            }
            drawPath(
                path = path,
                color = containerColor
            )
            drawPath(
                path = Path().apply {
                    lineTo(wrapHeight, wrapHeight)
                    lineTo(0F, wrapHeight)
                },
                color = contentColor
            )

            drawPath(
                path = Path().apply {
                    moveTo(this@Canvas.size.width, this@Canvas.size.height)
                    lineTo(this@Canvas.size.width - wrapHeight, this@Canvas.size.height)
                    lineTo(
                        this@Canvas.size.width - wrapHeight,
                        this@Canvas.size.height - wrapHeight
                    )
                },
                color = contentColor
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer {
                    rotationZ = 45f
                    translationX = rWidth / 4
                    translationY = (-rWidth) / 4
                }
        ) {
            Text(
                text = tag,
                color = contentColor,
                maxLines = 1,
                style = MaterialTheme.typography.labelSmall
            )
            if (tagDecoration != null) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .offset(2.dp, (-4).dp)
                ) {
                    tagDecoration()
                }
            }
        }
    }
}

@Preview
@Composable
private fun TagPrev() {
    Column {
        Tag(
            tag = "Main",
            contentColor = TagCardColor.default().tagColor,
            containerColor = TagCardColor.default().tagContainerColor,
            tagDecoration = null,
            tagHeight = tagHeight,
            size = tagBoxSize
        )
    }
}

@Preview
@Composable
private fun CardWithTagPrev() {
    Column {
        CardWithTag(
            tag = "Main",
            onClick = { },
            tagDecoration = {
                Image(
                    painter = painterResource(id = R.drawable.new_task_decoration),
                    contentDescription = null,
                    modifier = Modifier.graphicsLayer { rotationZ = -45F }
                )
            }
        ) {
            Column(Modifier.padding(16.dp)) {
                Text(text = "Hello World")
            }
        }
    }
}

@Preview
@Composable
private fun CardWithTagPrev1() {
    Column {
        CardWithTag(
            tag = null,
            onClick = { },
            tagDecoration = {
            }
        ) {
            Column(Modifier.padding(16.dp)) {
                Text(text = "Hello World")
            }
        }
    }
}
