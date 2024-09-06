package com.example.phaylfragment.model

import androidx.annotation.DrawableRes

data class OnboardingItem(
    val title: String,
    val description: String,
    val button: String,
    @DrawableRes val imageResId: Int // Resim kaynak ID'si ekleyin
)