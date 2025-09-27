/** Copyright (c) 2023 DAIA Tech Pvt Ltd */

package com.daiatech.app.ui.components.spacers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalSpacer(width: Dp = 16.dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun VerticalSpacer(height: Dp = 16.dp) {
    Spacer(modifier = Modifier.height(height))
}
