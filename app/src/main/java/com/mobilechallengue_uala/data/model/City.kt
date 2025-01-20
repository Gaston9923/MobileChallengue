package com.mobilechallengue_uala.data.model

data class City(
    val _id: Int,
    val name: String,
    val country: String,
    val isFavorite: Boolean = false,
    val coord: Coordinates
)