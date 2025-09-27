package com.daiatech.app.ui.components.dialogs

import android.app.Activity
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.daiatech.app.ui.components.appbars.saveViewAsBitmap
import com.daiatech.app.ui.components.buttons.ButtonVariation
import com.daiatech.app.ui.components.buttons.KButton
import com.daiatech.app.ui.components.buttons.KTextButton
import com.daiatech.app.ui.components.modals.dialogs.KDialogLayout
import com.daiatech.app.ui.components.textfields.DashedNumbersTransformation
import com.daiatech.app.ui.components.textfields.NumericInputField
import com.daiatech.app.ui.theme.UITheme
import com.daiatech.app.ui.theme.primaryDark
import com.daiatech.ui.R
import kotlinx.coroutines.launch

@Composable
fun NumericInputDialog(
    modifier: Modifier = Modifier,
    input: String,
    onValueChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    visualTransformation: VisualTransformation = DashedNumbersTransformation(),
    properties: DialogProperties = DialogProperties(),
    title: @Composable () -> Unit,
    subtitle: (@Composable () -> Unit)? = null,
    description: (@Composable () -> Unit)? = null,
    negativeText: String = "Back",
    positiveText: String = "Done",
    onNegative: () -> Unit,
    onPositive: () -> Boolean,
    onHelp: ((Uri?) -> Unit)? = null,
    enablePositive: Boolean = true,
    enableNegative: Boolean = true,
    errorMessage: String? = null,
    setErrorState: (message: String?) -> Unit = { },
    label: @Composable () -> Unit = {},
    invalidNumberMessage: String = "Only numeric inputs are allowed",
    blankInputErrorMessage: String = "Blank inputs are not allowed!"
) {
    NumericInputDialog(
        modifier = modifier,
        input = input,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        onDismissRequest = onDismissRequest,
        properties = properties,
        title = title,
        subtitle = subtitle,
        description = description,
        actions = {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                KTextButton(
                    onClick = onNegative,
                    modifier = Modifier.weight(1f),
                    enabled = enableNegative,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = primaryDark
                    ),
                    border = BorderStroke(2.dp, primaryDark),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp
                    )
                ) {
                    Text(text = negativeText)
                }
                KButton(
                    onClick = { onPositive() },
                    modifier = Modifier.weight(1f),
                    isEnabled = enablePositive,
                    buttonVariation = ButtonVariation.PrimaryButtonRegular,
                    content = positiveText,
                    leftIcon = null,
                    rightIcon = null
                )
            }
        },
        onImeDone = onPositive,
        errorMessage = errorMessage,
        setErrorState = setErrorState,
        label = label,
        invalidNumberMessage = invalidNumberMessage,
        blankInputErrorMessage = blankInputErrorMessage,
        onHelp = onHelp
    )
}

@Composable
fun NumericInputDialog(
    modifier: Modifier = Modifier,
    input: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = DashedNumbersTransformation(),
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    title: @Composable () -> Unit,
    subtitle: (@Composable () -> Unit)? = null,
    description: (@Composable () -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
    errorMessage: String? = null,
    setErrorState: (message: String?) -> Unit = { },
    label: @Composable () -> Unit = {},
    invalidNumberMessage: String = "Only numeric inputs are allowed",
    blankInputErrorMessage: String = "Blank inputs are not allowed!",
    onImeDone: () -> Boolean,
    onHelp: ((Uri?) -> Unit)? = null
) {
    val context = LocalContext.current
    val view = LocalView.current
    val window = (view.context as Activity).window

    KDialogLayout(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        properties = properties,
        actions = { modifier, _ ->
            if (actions != null) {
                Row(modifier = modifier.fillMaxWidth()) {
                    actions()
                }
            }
        },
        content = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)) {
                subtitle?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    subtitle()
                }
                description?.let {
                    ProvideTextStyle(value = MaterialTheme.typography.bodyMedium) {
                        Spacer(modifier = Modifier.height(8.dp))
                        description()
                    }
                }
                NumericInputField(
                    modifier = Modifier.fillMaxWidth(),
                    value = input,
                    onValueChange = onValueChange,
                    onSubmit = { onImeDone() },
                    errorMessage = errorMessage,
                    setErrorState = setErrorState,
                    label = label,
                    invalidNumberMessage = invalidNumberMessage,
                    blankInputErrorMessage = blankInputErrorMessage,
                    visualTransformation = visualTransformation
                )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                title()
                val coroutineScope = rememberCoroutineScope()

                // TODO: fix the screenshot of dialog window not taken
                val takeScreenshot = {
                    coroutineScope.launch {
                        val fileContainer = (context.filesDir.path.plus("/screenshots-container"))
                        val filesUri = saveViewAsBitmap(fileContainer, view, window)
                        if (onHelp != null) {
                            onHelp(filesUri)
                        }
                    }
                }
                if (onHelp != null) {
                    IconButton(
                        modifier = Modifier
                            .padding(8.dp),
                        onClick = {
                            takeScreenshot()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_help_avatar),
                            contentDescription = stringResource(id = R.string.help),
                            tint = Color.Unspecified,
                            modifier = Modifier.size(45.dp)
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun NumericInputDialogPrev() {
    UITheme {
        Column(Modifier.padding(16.dp)) {
            NumericInputDialog(
                input = "123456",
                onValueChange = { },
                properties = DialogProperties(usePlatformDefaultWidth = false),
                onDismissRequest = { /*TODO*/ },
                title = { Text(text = "Project Code") },
                description = { Text(text = "Please enter the project code") },
                onNegative = { /*TODO*/ },
                onPositive = { true },
                onHelp = {}
            )
        }
    }
}
