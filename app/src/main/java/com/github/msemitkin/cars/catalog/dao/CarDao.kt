package com.github.msemitkin.cars.catalog.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarDao {

    @Query("SELECT * FROM Car WHERE id = :id")
    fun getById(id: Long): CarEntity

    @Insert
    fun insert(carEntity: CarEntity): Long
}