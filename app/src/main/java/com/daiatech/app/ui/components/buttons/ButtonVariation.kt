package com.daiatech.app.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.ButtonColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.daiatech.app.ui.theme.primaryDark

sealed class ButtonVariation(
    val colors: ButtonColors,
    val buttonSize: ButtonSize,
    val borderColor: BorderStroke? = null,
    val textStyle: ButtonTextStyle
) {
    data object PrimaryButtonSmall : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFF008857),
            contentColor = Color(0xFFFFFFFF),
            disabledContainerColor = Color(0XFF008857).copy(alpha = 0.5f),
            disabledContentColor = Color(0xFFFFFFFF).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.SMALL,
        textStyle = ButtonTextStyle.SMALL
    )

    data object PrimaryButtonRegular : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFF008857),
            contentColor = Color(0xFFFFFFFF),
            disabledContainerColor = Color(0XFF008857).copy(alpha = 0.5f),
            disabledContentColor = Color(0xFFFFFFFF).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.REGULAR,
        textStyle = ButtonTextStyle.REGULAR
    )

    data object SecondaryButtonSmall : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFFFFFFFF),
            contentColor = Color(0xFF008857),
            disabledContainerColor = Color(0XFFFFFFFF).copy(alpha = 0.5f),
            disabledContentColor = Color(0xFF008857).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.SMALL,
        borderColor = BorderStroke(2.dp, primaryDark),
        textStyle = ButtonTextStyle.SMALL
    )

    data object SecondaryButtonRegular : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFFFFFFFF),
            contentColor = Color(0xFF008857),
            disabledContainerColor = Color(0XFFFFFFFF).copy(alpha = 0.5f),
            disabledContentColor = Color(0xFF008857).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.REGULAR,
        borderColor = BorderStroke(2.dp, primaryDark),
        textStyle = ButtonTextStyle.REGULAR
    )

    data object SecondaryErrorButton : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFFFFFFFF),
            contentColor = Color(0XFFDE3730),
            disabledContainerColor = Color(0XFFFFFFFF).copy(alpha = 0.5f),
            disabledContentColor = Color(0XFFDE3730).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.REGULAR,
        borderColor = BorderStroke(2.dp, Color(0XFFDE3730)),
        textStyle = ButtonTextStyle.REGULAR
    )

    data object ErrorButtonSmall : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFFDE3730),
            contentColor = Color(0xFFFFFFFF),
            disabledContainerColor = Color(0XFFDE3730).copy(alpha = 0.5f),
            disabledContentColor = Color(0xFFFFFFFF).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.SMALL,
        textStyle = ButtonTextStyle.SMALL
    )

    data object ErrorButtonRegular : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFFDE3730),
            contentColor = Color(0xFFFFFFFF),
            disabledContainerColor = Color(0XFFDE3730).copy(alpha = 0.5f),
            disabledContentColor = Color(0xFFFFFFFF).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.REGULAR,
        textStyle = ButtonTextStyle.REGULAR
    )

    data object TertiaryButtonRegular : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color(0xFF008857),
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color(0xFF008857).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.REGULAR,
        textStyle = ButtonTextStyle.REGULAR
    )

    data object AccentOutlineRegular : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFFFFFFFF),
            contentColor = Color.Black,
            disabledContainerColor = Color(0XFFFFFFFF).copy(alpha = 0.5f),
            disabledContentColor = Color.Black.copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.REGULAR,
        borderColor = BorderStroke(2.dp, Color(0XFF007BCB)),
        textStyle = ButtonTextStyle.REGULAR
    )

    data object AccentFillRegular : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFF007BCB),
            contentColor = Color(0xFFFFFFFF),
            disabledContainerColor = Color(0XFF007BCB).copy(alpha = 0.5f),
            disabledContentColor = Color(0xFFFFFFFF).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.REGULAR,
        textStyle = ButtonTextStyle.REGULAR
    )

    data object IconErrorButtonRegular : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFFFFFFFF),
            contentColor = Color(0XFFDE3730),
            disabledContainerColor = Color(0XFFFFFFFF).copy(alpha = 0.5f),
            disabledContentColor = Color(0XFFDE3730).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.REGULAR,
        borderColor = BorderStroke(8.dp, Color(0XFFDE3730)),
        textStyle = ButtonTextStyle.REGULAR
    )

    data object IconPrimaryButtonRegular : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFFFFFFFF),
            contentColor = Color(0xFF008857),
            disabledContainerColor = Color(0XFFFFFFFF).copy(alpha = 0.5f),
            disabledContentColor = Color(0xFF008857).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.REGULAR,
        borderColor = BorderStroke(8.dp, primaryDark),
        textStyle = ButtonTextStyle.REGULAR
    )

    data object IconSecondaryButtonRegular : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFFFFFFFF),
            contentColor = Color(0xFF008857),
            disabledContainerColor = Color(0XFFFFFFFF).copy(alpha = 0.5f),
            disabledContentColor = Color(0xFF008857).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.REGULAR,
        borderColor = BorderStroke(1.dp, primaryDark),
        textStyle = ButtonTextStyle.REGULAR
    )

    data object IconTertiaryButtonRegular : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color(0xFF008857),
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color(0xFF008857).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.REGULAR,
        textStyle = ButtonTextStyle.REGULAR
    )

    data object IconErrorButtonSmall : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFFFFFFFF),
            contentColor = Color(0XFFDE3730),
            disabledContainerColor = Color(0XFFFFFFFF).copy(alpha = 0.5f),
            disabledContentColor = Color(0XFFDE3730).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.SMALL,
        borderColor = BorderStroke(4.dp, Color(0XFFDE3730)),
        textStyle = ButtonTextStyle.REGULAR
    )

    data object IconPrimaryButtonSmall : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFFFFFFFF),
            contentColor = Color(0xFF008857),
            disabledContainerColor = Color(0XFFFFFFFF).copy(alpha = 0.5f),
            disabledContentColor = Color(0xFF008857).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.SMALL,
        borderColor = BorderStroke(4.dp, primaryDark),
        textStyle = ButtonTextStyle.REGULAR
    )

    data object IconSecondaryButtonSmall : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color(0XFFFFFFFF),
            contentColor = Color(0xFF008857),
            disabledContainerColor = Color(0XFFFFFFFF).copy(alpha = 0.5f),
            disabledContentColor = Color(0xFF008857).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.SMALL,
        borderColor = BorderStroke(1.dp, primaryDark),
        textStyle = ButtonTextStyle.REGULAR
    )

    data object IconTertiaryButtonSmall : ButtonVariation(
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color(0xFF008857),
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color(0xFF008857).copy(alpha = 0.5f)
        ),
        buttonSize = ButtonSize.SMALL,
        textStyle = ButtonTextStyle.REGULAR
    )
}

enum class ButtonSize(val value: Dp) {
    SMALL(32.dp),
    REGULAR(56.dp)
}

enum class ButtonTextStyle {
    SMALL,
    REGULAR
}
