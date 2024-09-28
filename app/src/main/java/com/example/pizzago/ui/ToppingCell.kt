package com.example.pizzago.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pizzago.model.Topping
import com.example.pizzago.model.ToppingPlacement
/**
 * Preview composable function for displaying a ToppingCell when the topping
 * is not on the pizza.
 */
@Preview
@Composable
private fun ToppingCellPreviewNotOnPizza() {
    ToppingCell(
        topping = Topping.Pepperoni,
        placement = null,
        onClickTopping = {}
    )

}
/**
 * Preview composable function for displaying a ToppingCell when the topping
 * is on the left half of the pizza.
 */
@Preview
@Composable
private fun ToppingCellPreviewOnLeftHalf() {
    ToppingCell(
        topping = Topping.Pepperoni,
        placement = ToppingPlacement.Left,
        onClickTopping = {}
    )

}

/**
 * Composable function that represents a cell for selecting a pizza topping and its placement.
 *
 * @param topping The topping associated with this cell.
 * @param placement The current placement of the topping on the pizza (null if not on the pizza).
 * @param modifier Modifier used to adjust the layout or appearance of the cell.
 * @param onClickTopping Callback function to be invoked when the cell is clicked.
 */
@Composable
fun ToppingCell(
    topping: Topping,
    placement: ToppingPlacement?,
    modifier:Modifier = Modifier,
    onClickTopping: () -> Unit
) {
    Log.d("ToppingCell","Called ToppingCell for $topping")
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onClickTopping() }
            .padding(16.dp,4.dp)
    ) {
        Checkbox(checked = placement != null, onCheckedChange = {onClickTopping() })
        Column (
            modifier = Modifier.weight(1f, fill = true)
                .padding(4.dp)
        ) {
            Text(
                text = stringResource(id = topping.toppingName),
                style=MaterialTheme.typography.bodyLarge
                )
            if(placement != null){
                Text(
                    text = stringResource(id = placement.label),
                    style=MaterialTheme.typography.bodySmall
                    )
            }
        }
    }
}