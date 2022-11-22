package com.github.msemitkin.cars.catalog.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Car")
data class CarEntity(
    @PrimaryKey val id: Long?,
    val brand: String,
    val bodyType: String,
    val color: String,
    val price: Int,
    val engineCapacityLiters: Double
)