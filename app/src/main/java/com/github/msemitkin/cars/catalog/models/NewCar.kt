package com.github.msemitkin.cars.catalog.models

data class NewCar(
    val brand: String,
    val bodyType: BodyType,
    val color: String,
    val price: Int,
    val engineCapacityLiters: Int
)