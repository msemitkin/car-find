package com.github.msemitkin.cars.catalog.dao

import androidx.room.RoomDatabase

@androidx.room.Database(entities = [CarEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun carDao(): CarDao
}
