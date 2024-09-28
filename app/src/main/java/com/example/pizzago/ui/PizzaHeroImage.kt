package com.example.pizzago.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pizzago.R
import com.example.pizzago.model.Pizza
import com.example.pizzago.model.Topping
import com.example.pizzago.model.ToppingPlacement.*

/**
 * Preview composable function for displaying the PizzaHeroImage
 * with a sample pizza configuration.
 */

@Preview
@Composable
fun PizzaHeroImagePreview () {
    PizzaHeroImage(pizza = Pizza(
        toppings = mapOf(
            Topping.Pineapple to All,
            Topping.Pepperoni to Left,
            Topping.Basil to Right
        )
    )
    )
}

/**
 * Composable function that displays a hero image of a pizza with its toppings.
 *
 * @param pizza The pizza object containing information about the toppings and their placement.
 * @param modifier Modifier used to adjust the layout or appearance of the image.
 */
@Composable
fun PizzaHeroImage (
    pizza: Pizza,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
    ) {
        Image(
            painter = painterResource(R.drawable.pizza_crust),
            contentDescription = stringResource(R.string.pizza_preview),
            modifier = modifier.fillMaxSize()
        )
        pizza.toppings.forEach { (topping, placement) ->
            Image(
                painter = painterResource(topping.pizzaOverlayImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = when (placement) {
                    Left -> Alignment.TopStart
                    Right -> Alignment.TopEnd
                    All -> Alignment.Center
                },
                modifier = modifier.focusable(false)
                    .aspectRatio(when (placement) {
                        Left, Right -> 0.5f
                        All -> 1.0f
                    })
                    .align(when (placement) {
                        Left -> Alignment.CenterStart
                        Right -> Alignment.CenterEnd
                        All -> Alignment.Center
                    })
                    )

        }
    }

}