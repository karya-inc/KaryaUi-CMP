package com.daiatech.app.ui.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daiatech.app.ui.theme.primary

@Composable
fun OtpInputField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 6,
    enabled: Boolean = true,
    onOtpTextChange: (String, Boolean) -> Unit,
    onSubmit: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        if (otpText.length > otpCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpCount characters")
        }
    }

    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        BasicTextField(
            modifier = modifier,
            enabled = enabled,
            singleLine = true,
            value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
            onValueChange = { newValue ->
                val filteredText = newValue.text.filter { it.isDigit() }
                if (filteredText.length <= otpCount) {
                    onOtpTextChange.invoke(filteredText, filteredText.length == otpCount)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onSubmit(otpText)
            }),
            visualTransformation = NumericInputFilter,
            decorationBox = {
                Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    repeat(otpCount) { index ->
                        Spacer(modifier = Modifier.width(4.dp))
                        CharView(
                            index = index,
                            text = otpText
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }
        )
    }
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index < text.length -> text[index].toString()
        else -> "0"
    }

    Box(
        modifier = Modifier
            .width(40.dp)
            .height(50.dp)
            .background(Color.White)
            .border(
                1.dp,
                when {
                    isFocused -> primary
                    else -> Color.Black
                },
                RoundedCornerShape(6.dp)
            )
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = char,
            fontSize = 20.sp,
            style = MaterialTheme.typography.headlineMedium,
            color = if (char == "0" && index >= text.length) {
                Color.Gray
            } else {
                Color.Black
            },
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OtpTextFieldPreview() {
    var otpValue by remember { mutableStateOf("12") }
    OtpInputField(
        modifier = Modifier.padding(16.dp),
        otpText = otpValue,
        onOtpTextChange = { newValue, isComplete ->
            otpValue = newValue
        },
        onSubmit = {
        }
    )
}

@Preview(showBackground = true)
@Composable
fun OtpTextFieldDisabledPreview() {
    var otpValue by remember { mutableStateOf("12") }
    OtpInputField(
        modifier = Modifier.padding(16.dp),
        otpText = otpValue,
        onOtpTextChange = { newValue, _ ->
            otpValue = newValue
        },
        onSubmit = {},
        enabled = false
    )
}

object NumericInputFilter : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val numericText = text.text.filter { it.isDigit() }
        val numericAnnotatedString = AnnotatedString(numericText)

        return TransformedText(numericAnnotatedString, OffsetMapping.Identity)
    }
}
