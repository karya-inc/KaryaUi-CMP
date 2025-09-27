package com.daiatech.app.ui.components.cards

import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import com.daiatech.app.ui.theme.neutral
import com.daiatech.app.ui.theme.onPrimary
import com.daiatech.app.ui.theme.primary

val primaryCardColors
    @Composable get() = CardDefaults.cardColors(
        containerColor = neutral,
        contentColor = onPrimary,
        disabledContainerColor = primary.copy(0.4f),
        disabledContentColor = onPrimary.copy(0.4f)
    )
