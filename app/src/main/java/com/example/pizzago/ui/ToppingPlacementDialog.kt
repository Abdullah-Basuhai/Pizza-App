package com.example.pizzago.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.pizzago.R
import com.example.pizzago.model.Topping
import com.example.pizzago.model.ToppingPlacement

/**
 * Composable function that displays a dialog for selecting the placement of a topping on the pizza.
 *
 * @param topping The topping for which the placement is being selected.
 * @param onDismissRequest Callback function to be invoked when the dialog should be dismissed.
 * @param onSetToppingPlacement Callback function to be invoked when a placement option is selected,
 * passing the chosen placement (or null for no placement) as an argument.
 */
@Composable
fun ToppingPlacementDialog(
    topping: Topping,
    onDismissRequest: () -> Unit,
    onSetToppingPlacement: (ToppingPlacement?) -> Unit
) {

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card {
            Column {
                Text(
                    text = stringResource(id = R.string.placement_prompt, topping.name),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )
                ToppingPlacement.entries.forEach { toppingPlacement ->
                    ToppingPlacementOption(
                        placementName  = toppingPlacement.label,
                        onClick = {
                            //Notify the creator of the dialog which choice was selected
                            onSetToppingPlacement(toppingPlacement)
                            //Dismiss the dialog
                            onDismissRequest()
                        }
                    )
                }

                ToppingPlacementOption(
                    placementName = R.string.placement_none,
                    onClick = {
                        onSetToppingPlacement(null)
                        onDismissRequest()
                    }
                )
            }
        }

    }
}

/**
 * Composable function that represents a single placement option within the ToppingPlacementDialog.
 *
 * @param placementName String resource ID for the name of the placement option.
 * @param onClick Callback function to be invoked when the option is clicked.
 * @param modifier Modifier used to adjust the layout or appearance of the option.
 */
@Composable
private fun ToppingPlacementOption (
    @StringRes placementName: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(placementName),
            modifier = Modifier.padding(8.dp)
        )
    }
}