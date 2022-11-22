package com.github.msemitkin.cars.catalog.models

data class Car(
    val brand: CarBrand,
    val bodyType: BodyType,
    val color: Color,
    val price: Int,
    val engineCapacityLiters: Double
)