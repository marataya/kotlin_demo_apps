package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val nameStrId: Int,
    val visitorCount: Int,
    @DrawableRes val coursePicId: Int
)