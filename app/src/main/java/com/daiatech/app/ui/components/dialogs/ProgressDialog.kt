package com.daiatech.app.ui.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.daiatech.app.ui.theme.UITheme
import com.daiatech.ui.R

@Composable
fun ProgressDialog(
    modifier: Modifier = Modifier,
    dimColor: Color = Color.Black.copy(alpha = 0.6f),
    properties: DialogProperties = DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false,
        usePlatformDefaultWidth = false
    ),
    lottieResId: Int,
    extras: @Composable ColumnScope.() -> Unit = {}
) {
    KDialog(modifier = modifier, onDismissRequest = { }, properties = properties, dimColor = dimColor) {
        Column(
            Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieResId))
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(100.dp)
            )
            extras()
        }
    }
}

@Preview
@Composable
private fun ProgressDialogPrev() {
    UITheme {
        Column(Modifier.fillMaxSize()) {
            Surface { ProgressDialog(lottieResId = R.raw.karya_generic_loader) }
        }
    }
}
