
/** Copyright (c) 2023 DAIA Tech Pvt Ltd */

package com.daiatech.app.ui.components.textfields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daiatech.app.ui.theme.UITheme
import com.daiatech.app.ui.theme.errorColor
import com.daiatech.app.ui.theme.neutral3
import com.daiatech.app.ui.theme.primaryDark

@Composable
fun TextInputField(
    /** Essential fields */
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: (String) -> Boolean,

    /** Optional fields */
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    setErrorState: (message: String?) -> Unit = { },
    label: @Composable () -> Unit = {},
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    blankInputErrorMessage: String = "Blank inputs are not allowed!",
    focusRequester: FocusRequester = remember { FocusRequester() },
    enabled: Boolean = true,
    placeholder: @Composable () -> Unit = {},
    shape: Shape = RoundedCornerShape(8.dp),
    supportingText: (@Composable () -> Unit)? = null,
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    keyboardActions: KeyboardActions? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val focusManager: FocusManager = LocalFocusManager.current

    val effectiveLeadingIcon: (@Composable (() -> Unit))? = leadingIcon?.let {
        {
            Box(modifier = Modifier.offset(x = 8.dp)) {
                it()
            }
        }
    }

    OutlinedTextField(
        value = value,
        isError = errorMessage != null,
        onValueChange = {
            setErrorState(null)
            onValueChange(it)
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
            ?: KeyboardActions {
                if (value.isNotBlank()) {
                    val success = onSubmit(value)
                    if (success) {
                        focusManager.clearFocus()
                    }
                } else {
                    setErrorState(blankInputErrorMessage)
                }
            },
        singleLine = singleLine,
        label = label,
        modifier = modifier.focusRequester(focusRequester),
        leadingIcon = effectiveLeadingIcon,
        trailingIcon = {
            if (trailingIcon != null) {
                trailingIcon()
            } else {
                IconButton(
                    onClick = { onValueChange("") },
                    enabled = enabled,
                    modifier = Modifier.offset(x = (-8).dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear",
                        tint = Color(0xFF6A6B6B)
                    )
                }
            }
        },
        placeholder = placeholder,
        supportingText = {
            Column {
                if (supportingText != null) {
                    supportingText()
                }
                if (!errorMessage.isNullOrBlank()) {
                    Text(
                        text = errorMessage,
                        modifier = Modifier.padding(start = 10.dp, top = 1.dp),
                        color = errorColor
                    )
                }
            }
        },
        shape = shape,
        enabled = enabled,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            focusedBorderColor = primaryDark,
            focusedLabelColor = primaryDark,
            unfocusedContainerColor = Color.White,
            unfocusedBorderColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            disabledContainerColor = Color.White.copy(alpha = 0.5f),
            disabledBorderColor = Color.Black.copy(alpha = 0.5f),
            disabledTextColor = Color.Black.copy(alpha = 0.5f),
            disabledSupportingTextColor = Color.Black.copy(alpha = 0.5f),
            disabledLeadingIconColor = Color.Black.copy(alpha = 0.5f),
            disabledTrailingIconColor = Color.Black.copy(alpha = 0.5f),
            disabledLabelColor = Color.Black.copy(alpha = 0.5f),
            errorContainerColor = Color.White,
            errorBorderColor = errorColor,
            errorTextColor = Color.Black,
            errorLeadingIconColor = Color.Black,
            errorTrailingIconColor = Color.Black,
            errorLabelColor = errorColor
        ),
        visualTransformation = visualTransformation
    )
}

@Preview
@Composable
fun TextFieldPreview() {
    UITheme {
        var text by remember { mutableStateOf("") }
        TextInputField(value = text, onValueChange = { text = it }, onSubmit = { true })
    }
}

@Preview
@Composable
fun TextFieldPreview1() {
    UITheme {
        Surface {
            var text by remember { mutableStateOf("") }
            var errorMessage by remember { mutableStateOf<String?>("something went wrong") }

            TextInputField(
                value = text,
                onValueChange = { text = it },
                onSubmit = { true },
                leadingIcon = {
                    IconButton(
                        onClick = { },
                        enabled = true,
                        colors = IconButtonDefaults.iconButtonColors(disabledContentColor = neutral3)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "clear",
                            tint = Color(0xFF6A6B6B)
                        )
                    }
                },
                errorMessage = errorMessage,
                setErrorState = { m -> errorMessage = m }
            )
        }
    }
}

@Preview
@Composable
fun TextFieldPreview2() {
    UITheme {
        Surface {
            var text by remember { mutableStateOf("") }
            var errorMessage by remember { mutableStateOf<String?>(null) }

            TextInputField(
                value = text,
                onValueChange = { text = it },
                onSubmit = { true },
                leadingIcon = {
                    IconButton(
                        onClick = { },
                        enabled = true,
                        colors = IconButtonDefaults.iconButtonColors(disabledContentColor = neutral3)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "clear",
                            tint = Color(0xFF6A6B6B)
                        )
                    }
                },
                errorMessage = errorMessage,
                setErrorState = { m -> errorMessage = m }
            )
        }
    }
}
