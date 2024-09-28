package com.example.pizzago.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.example.pizzago.R
import com.example.pizzago.model.Pizza
import com.example.pizzago.model.Size
import com.example.pizzago.model.Topping
import com.example.pizzago.model.ToppingPlacement
import java.text.NumberFormat

/**
 * Composable function that displays the main pizza builder screen.
 * This screen allows users to customize their pizza by selecting toppings and size.
 *
 * @param modifier Modifier used to adjust the layout or appearance of the screen.
 */
@Preview
@Composable
fun PizzaBuilderScreen (
    modifier: Modifier = Modifier
){
    var pizza by rememberSaveable { mutableStateOf(Pizza()) }


    Column (modifier = modifier)
    {
        ToppingsList(pizza = pizza,
            onEditPizza = {pizza = it},
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = true)
        )

        OrderButton(
            pizza = pizza,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }

 }

/**
 * Composable function that displays a list of toppings and allows the user to select them.
 *
 * @param pizza The current pizza configuration.
 * @param onEditPizza Callback function to be invoked when the pizza configuration is updated.
 * @param modifier Modifier used to adjust the layout or appearance of the list.
 */
@Composable
private fun ToppingsList (
    pizza: Pizza,
    onEditPizza: (Pizza) -> Unit,
    modifier: Modifier = Modifier
){
    var toppingBeingAdded by rememberSaveable { mutableStateOf <Topping?>(null) }
    var selectedSize by rememberSaveable { mutableStateOf(pizza.size) }

    toppingBeingAdded?.let { topping ->
        ToppingPlacementDialog(
            topping = topping,
            onDismissRequest = { toppingBeingAdded = null },
            // receives the topping placement option and updates the pizza object
            onSetToppingPlacement = { placement: ToppingPlacement? ->
                onEditPizza(pizza.withTopping(topping, placement))
            }

        )
    }

    LazyColumn (modifier = modifier) {

        items(Size.entries.chunked(3)) { row ->
            // Chunk the list into groups of 3
            Row(modifier = Modifier
                .fillMaxWidth() // Fill the available width
                .wrapContentWidth(Alignment.CenterHorizontally) // Center the content horizontally))
                .padding(16.dp)) // Add some padding between buttons
            {
                row.forEach { size ->
                    // Iterate over each size in the current row
                CircularButton(
                    size = size,
                    isSelected = selectedSize == size,
                    onSetPizzaSize = {
                        onEditPizza(pizza.withSize(it))
                        selectedSize = it
                    }
                        )
                    if (size != row.last()) { // Add Spacer except after the last button
                        Spacer(modifier = Modifier.width(16.dp)) // Add space between buttons
                    }
            }
        }
            }



        item {
            PizzaHeroImage(
            pizza = pizza,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ))
        }

        items(Topping.entries.toTypedArray()) { topping ->
            ToppingCell(
                topping = topping,
                // determine the placement of a topping based on the pizza property
                placement = pizza.toppings[topping],
                onClickTopping = {
                    toppingBeingAdded = topping
                })
    }
    }
}

/**
 * Composable function that displays a button for placing the pizza order.
 *
 * @param pizza The current pizza configuration,used to calculate the price
 * based on the selected toppings and size.
 * @param modifier Modifier used to adjust the layout or appearance of the button.
 */
@Composable
fun OrderButton (
    pizza: Pizza,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    Button(
        modifier = modifier,
        onClick =
        {
            Toast.makeText(context, R.string.order_placed_toast, Toast.LENGTH_LONG)
                .show()
        })
    {
        // convert the price property to a formatted string
        val currencyFormater = remember {NumberFormat.getCurrencyInstance()}
        val price = currencyFormater.format(pizza.price)

        Text(text = stringResource(R.string.place_order_button, price)
            .toUpperCase(Locale.current)
        )

    }
}


