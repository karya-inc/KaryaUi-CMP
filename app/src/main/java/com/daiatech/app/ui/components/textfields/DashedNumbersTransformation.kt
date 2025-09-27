package com.daiatech.app.ui.components.textfields

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DashedNumbersTransformation(private val interval: Int = 4) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = buildAnnotatedString {
            text.forEachIndexed { index, char ->
                if (index > 0 && index % interval == 0) {
                    append("-")
                }
                append(char)
            }
        }

        val offsetMapping = object : OffsetMapping {

            // Required transformations
            // original     = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
            // transformed  = [0, 1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14, 16, 17, 18, 19, 21, 22, 23, 24]

            override fun originalToTransformed(offset: Int): Int {
                return offset.let { it + it / 4 - (if (it != 0 && it % 4 == 0) 1 else 0) }
            }

            override fun transformedToOriginal(offset: Int): Int {
                var originalOffset = 0
                var transformedOffset = 0
                while (transformedOffset < offset) {
                    if (formattedText[transformedOffset] != '-') {
                        originalOffset++
                    }
                    transformedOffset++
                }
                return originalOffset
            }
        }
        return TransformedText(formattedText, offsetMapping)
    }
}
