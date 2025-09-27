package com.daiatech.app.ui.components.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daiatech.app.ui.theme.UITheme
import com.daiatech.app.ui.util.DECIMAL_MATCH_REGEX
import com.daiatech.app.ui.util.PLAIN_NUMERIC_MATCH_REGEX

/**
 * @param errorMessage null when no error, when [errorMessage] is non null the component will show an error state with this message
 */
@Composable
fun NumericInputField(
    /** Essential fields */
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: (String) -> Boolean,

    /** Optional fields */
    modifier: Modifier = Modifier,
    allowDecimal: Boolean = false,
    errorMessage: String? = null,
    setErrorState: (message: String?) -> Unit = { },
    label: @Composable () -> Unit = {},
    leadingIcon: (@Composable () -> Unit)? = null,
    invalidDecimalMessage: String = "Only decimal numeric inputs are allowed",
    invalidNumberMessage: String = "Only numeric inputs are allowed",
    blankInputErrorMessage: String = "Blank inputs are not allowed!",
    focusRequester: FocusRequester = remember { FocusRequester() },
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        errorContainerColor = Color.White,
        disabledContainerColor = Color.White
    ),
    shape: Shape = RoundedCornerShape(8.dp),
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val focusManager: FocusManager = LocalFocusManager.current

    val numericInputFormat =
        remember { Regex(if (allowDecimal) DECIMAL_MATCH_REGEX else PLAIN_NUMERIC_MATCH_REGEX) }
    val keyboardType =
        remember { if (allowDecimal) KeyboardType.Number else KeyboardType.NumberPassword }

    TextInputField(
        value = value,
        errorMessage = errorMessage,
        onValueChange = {
            if (it.matches(numericInputFormat)) {
                onValueChange(it)
                setErrorState(null)
            } else {
                setErrorState(if (allowDecimal) invalidDecimalMessage else invalidNumberMessage)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            if (value.isNotBlank()) {
                val success = onSubmit(value)
                if (success) {
                    focusManager.clearFocus()
                }
            } else {
                setErrorState(blankInputErrorMessage)
            }
        },
        singleLine = true,
        label = label,
        modifier = modifier
            .padding(8.dp)
            .focusRequester(focusRequester),
        leadingIcon = leadingIcon,
        trailingIcon = {
            IconButton(onClick = {
                onValueChange("")
                focusManager.clearFocus()
            }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "clear",
                    tint = Color(0xFF6A6B6B)
                )
            }
        },
        supportingText = { Text(text = errorMessage ?: "") },
        visualTransformation = visualTransformation,
        onSubmit = { true }
    )
}

@Preview
@Composable
fun NumericInputPreview() {
    UITheme {
        Column {
            var text by remember { mutableStateOf("") }
            var isError by remember { mutableStateOf(false) }
            var errorMessage by remember { mutableStateOf<String?>(null) }

            NumericInputField(
                value = text,
                onValueChange = {
                    if (it.length > 10) {
                        isError = true
                        errorMessage = "Phone number's length should be 10 digit long"
                    } else {
                        text = it
                    }
                },
                onSubmit = {
                    if (text.length == 10) {
                        true
                    } else {
                        isError = true
                        errorMessage = "Phone number's length should be 10 digit long"
                        false
                    }
                },
                label = { Text(text = "Enter your phone number") },
                errorMessage = errorMessage,
                setErrorState = { m ->
                    errorMessage = m
                }
            )
        }
    }
}
