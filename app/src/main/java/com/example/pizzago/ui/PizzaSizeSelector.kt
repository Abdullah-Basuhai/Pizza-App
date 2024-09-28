package com.example.pizzago.ui

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzago.model.Size


/**
 * Composable function that represents a circular button for selecting pizza size.
 *
 * @param size The pizza size associated with this button.
 * @param isSelected Indicates whether this size is currently selected.
 * @param modifier Modifier used to adjust the layout or appearance of the button.
 * @param onSetPizzaSize Callback function to be invoked when the button is clicked,
 * passing the selected size as an argument.
 */

@Composable
fun CircularButton (
    size: Size,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onSetPizzaSize: (Size) -> Unit
) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        Color.White // Or a slightly whiter shade than your background
    }
    val textColor = if (isSelected) {
        Color.White // Or your desired selected text color
    } else {
        Color.Black // Or your desired unselected text color
    }

    Button(
        onClick = { onSetPizzaSize(size) },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        ), // elevation is like adding a small shadow beneath
        // the button to make it look like it's raised slightly above the screen.
        modifier = modifier
            .size(50.dp)

    ) {
        Text(
            text = stringResource(id = size.size),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
        )
    }
}
