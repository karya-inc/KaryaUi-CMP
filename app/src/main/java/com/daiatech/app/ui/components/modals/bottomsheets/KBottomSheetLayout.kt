package com.daiatech.app.ui.components.modals.bottomsheets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.daiatech.app.ui.theme.Dimens
import com.daiatech.app.ui.theme.UITheme
import com.daiatech.app.ui.theme.onPrimary
import com.daiatech.app.ui.theme.onSecondary
import com.daiatech.app.ui.theme.primary
import com.daiatech.app.ui.theme.primaryDark
import com.daiatech.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KBottomsheetLayout(
    modifier: Modifier = Modifier,
    message: String? = null,
    action: (@Composable (Modifier) -> Unit)? = null,
    title: String? = null,
    imgPainter: Painter? = null,
    paddingValues: Dp = Dimens.M,
    onDismissRequest: () -> Unit = {}
) {
    ModalBottomSheet(
        modifier = Modifier.systemBarsPadding(),
        onDismissRequest = { onDismissRequest() },
        containerColor = Color.White,
        dragHandle = null,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
            confirmValueChange = { true }
        )
    ) {
        KBottomsheetLayoutImpl(
            modifier = modifier,
            message = message,
            action = action,
            title = title,
            imgPainter = imgPainter,
            paddingValues = paddingValues
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun KBottomsheetLayoutImpl(
    modifier: Modifier = Modifier,
    message: String? = null,
    action: (@Composable (Modifier) -> Unit)? = null,
    title: String? = null,
    imgPainter: Painter? = null,
    paddingValues: Dp = Dimens.M
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.M)
            ) {
                if (imgPainter != null) {
                    Icon(
                        painter = imgPainter,
                        contentDescription = "icon",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                    )
                }
                if (title != null) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(500))
                    )
                }
                if (message != null) {
                    Text(
                        text = message,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                if (action != null) {
                    HorizontalDivider(
                        Modifier
                            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                            .fillMaxWidth()
                            .border(1.dp, Color.LightGray)
                    )
                    action(modifier)
                }
            }
        }
    }
}

val primaryButtonColors
    @Composable get() = ButtonDefaults.buttonColors(
        contentColor = onSecondary,
        containerColor = primaryDark,
        disabledContainerColor = primary.copy(0.4f),
        disabledContentColor = onPrimary.copy(0.4f)
    )

@Preview
@Composable
private fun KBottomSheetLayoutPrev() {
    UITheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray) // Fake background
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                tonalElevation = 4.dp,
                shadowElevation = 8.dp
            ) {
                KBottomsheetLayoutImpl(
                    title = stringResource(R.string.add_a_new_account),
                    message = stringResource(R.string.Add_a_new_account_to_this_mobile_phone_so_multiple_people_can_use_Karya_to_earn_and_learn),
                    imgPainter = painterResource(R.drawable.new_account),
                    action = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                modifier = Modifier.height(56.dp),
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = primaryDark
                                ),
                                border = BorderStroke(2.dp, primaryDark),
                                elevation = ButtonDefaults.buttonElevation(4.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(primaryDark, CircleShape)
                                        .padding(6.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_arrow_left),
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp),
                                        tint = Color.White
                                    )
                                }
                            }

                            Spacer(Modifier.width(8.dp))

                            Button(
                                modifier = Modifier.weight(5f).height(56.dp),
                                onClick = {},
                                colors = primaryButtonColors,
                                elevation = ButtonDefaults.buttonElevation(4.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = stringResource(R.string.yes),
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Box(
                                        modifier = Modifier
                                            .background(Color.White, CircleShape)
                                            .padding(6.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.ic_arrow_right),
                                            contentDescription = null,
                                            modifier = Modifier.size(16.dp),
                                            tint = primaryDark
                                        )
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}
