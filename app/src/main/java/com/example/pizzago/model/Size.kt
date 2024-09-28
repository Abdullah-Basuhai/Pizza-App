package com.example.pizzago.model

import androidx.annotation.StringRes
import com.example.pizzago.R

enum class Size(@StringRes val size: Int) {
    Small(R.string.small),
    Medium(R.string.medium),
    Large(R.string.large),
}