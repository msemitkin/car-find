package com.github.msemitkin.cars.catalog.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CarDao {

    @Query("SELECT * FROM Car WHERE id = :id")
    fun getById(id: Long): CarEntity

    @Query("SELECT * FROM Car WHERE color = :color AND bodyType = :bodyType")
    fun getByCriteria(color: String, bodyType: String): List<CarEntity>

    @Query("SELECT * FROM Car")
    fun getAll(): List<CarEntity>

    @Insert
    fun insert(carEntity: CarEntity): Long
}