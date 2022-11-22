package com.github.msemitkin.cars.catalog.dao

import com.github.msemitkin.cars.catalog.models.BodyType
import com.github.msemitkin.cars.catalog.models.Car
import com.github.msemitkin.cars.catalog.models.CarBrand
import com.github.msemitkin.cars.catalog.models.Color

interface GetCarService {
    fun getById(id: Long): Car
}

interface SaveCarService {
    fun save(car: Car): Long
}

class CarService(private val carDao: CarDao) : GetCarService, SaveCarService {

    override fun save(car: Car): Long {
        val carEntity = CarEntity(
            null,
            car.brand.name,
            car.bodyType.name,
            car.color.name,
            car.price,
            car.engineCapacityLiters
        )
        return carDao.insert(carEntity)
    }

    override fun getById(id: Long): Car {
        val carEntity = carDao.getById(id)
        return Car(
            CarBrand.valueOf(carEntity.brand),
            BodyType.valueOf(carEntity.bodyType),
            Color.valueOf(carEntity.color),
            carEntity.price,
            carEntity.engineCapacityLiters
        )
    }
}