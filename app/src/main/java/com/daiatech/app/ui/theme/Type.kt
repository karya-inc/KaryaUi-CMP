package com.daiatech.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// Set of Material typography styles to start with
val Typography
    @Composable get() = Typography(
        displayLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 57.ssp,
            lineHeight = 28.ssp,
            letterSpacing = 0.4.ssp
        ),
        displayMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 45.ssp,
            lineHeight = 52.ssp,
            letterSpacing = 0.ssp
        ),
        displaySmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 36.ssp,
            lineHeight = 44.ssp,
            letterSpacing = 0.ssp
        ),
        headlineLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 32.ssp,
            lineHeight = 40.ssp,
            letterSpacing = 0.ssp
        ),
        headlineMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 28.ssp,
            lineHeight = 36.ssp,
            letterSpacing = 0.ssp
        ),
        headlineSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 24.ssp,
            lineHeight = 32.ssp,
            letterSpacing = 0.ssp
        ),
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 22.ssp,
            lineHeight = 28.ssp,
            letterSpacing = 0.ssp
        ),

        titleMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 16.ssp,
            lineHeight = 24.ssp,
            letterSpacing = 0.2.ssp
        ),

        titleSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 14.ssp,
            lineHeight = 20.ssp,
            letterSpacing = 0.2.ssp
        ),

        bodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.ssp,
            lineHeight = 24.ssp,
            letterSpacing = 0.5.ssp
        ),
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 14.ssp,
            lineHeight = 20.ssp,
            letterSpacing = 0.2.ssp
        ),
        bodySmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 12.ssp,
            lineHeight = 16.ssp,
            letterSpacing = 0.4.ssp
        ),
        labelLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 14.ssp,
            lineHeight = 20.ssp,
            letterSpacing = 0.1.ssp
        ),
        labelMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 12.ssp,
            lineHeight = 16.ssp,
            letterSpacing = 0.5.ssp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 11.ssp,
            lineHeight = 16.ssp,
            letterSpacing = 0.5.ssp
        )
    )
