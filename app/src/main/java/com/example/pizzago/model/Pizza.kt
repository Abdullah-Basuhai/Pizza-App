package com.example.pizzago.model
import android.os.Parcelable
import com.example.pizzago.model.ToppingPlacement.*
import kotlinx.parcelize.Parcelize
@Parcelize
 data class Pizza(
     val toppings: Map<Topping,ToppingPlacement> = emptyMap(),
     val size: Size = Size.Small
 ): Parcelable

{
     val price:Double
         get() = 9.99 + toppings.asSequence()// returns a sequence of pairs
                 .sumOf { (_,toppingPlacement) ->
                     when(toppingPlacement) {
                         Left,Right -> 0.5
                         All -> 1.0
                     }
                     } + when(size) {
                         Size.Small -> 0.0
                         Size.Medium -> 3.0
                         Size.Large -> 6.0
                     }



     fun withTopping(topping: Topping, placement: ToppingPlacement?): Pizza {

         return if (placement == null) {
             // copy function is used to create a modified version of a Pizza object without changing the original.
             copy(
                 // Returns a map containing all entries of the original map except the entry with the given key.
                 toppings = toppings - topping
             )
         } else {
             copy(
                 // creates a new read-only map by replacing or adding an entry to this map from a given key-value pair.
                 toppings = toppings + (topping to placement)
             )

         }
         }

    fun withSize(size: Size): Pizza {
        return copy (size = size)
    }

}

