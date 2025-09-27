package com.daiatech.app.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Dimens {
    val ZERO @Composable get() = 0.sdp
    val XXS @Composable get() = 2.sdp
    val XS @Composable get() = 4.sdp
    val S @Composable get() = 8.sdp
    val M @Composable get() = 16.sdp
    val L @Composable get() = 24.sdp
    val XL @Composable get() = 32.sdp
    val XXL @Composable get() = 48.sdp
    val XXXL @Composable get() = 64.sdp
}

/**
 * Scalable [Dp]
 */
inline val Int.sdp: Dp
    @Composable get() {
        // val configuration = LocalConfiguration.current
        // val screenWidth = configuration.screenWidthDp.toFloat()
        // return screenWidth.div(BASE_SCREEN_WIDTH).times(this).dp
        return this.dp
    }

/**
 * Scalable [TextUnit]
 */
inline val Int.ssp: TextUnit
    @Composable get() {
        // val configuration = LocalConfiguration.current
        // val screenWidth = configuration.screenWidthDp
        // return screenWidth.div(BASE_SCREEN_WIDTH).times(this).sp
        return this.sp
    }

/**
 * Scalable [TextUnit]
 */
inline val Double.ssp: TextUnit
    @Composable get() {
        // val configuration = LocalConfiguration.current
        // val screenWidth = configuration.screenWidthDp
        // return screenWidth.div(BASE_SCREEN_WIDTH).times(this).sp
        return this.sp
    }
