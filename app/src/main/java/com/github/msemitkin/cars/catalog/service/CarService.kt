package com.github.msemitkin.cars.catalog.service

import com.github.msemitkin.cars.catalog.dao.CarDao
import com.github.msemitkin.cars.catalog.dao.CarEntity
import com.github.msemitkin.cars.catalog.models.BodyType
import com.github.msemitkin.cars.catalog.models.Car
import com.github.msemitkin.cars.catalog.models.CarBrand
import com.github.msemitkin.cars.catalog.models.Color

interface GetCarService {
    fun getById(id: Long): Car
}

interface GetCarsByColorService {
    fun getByColor(color: Color): List<Car>
}

interface SaveCarService {
    fun save(car: Car): Long
}

interface GetCarsService {
    fun getCars(): List<Car>
}

class CarService(private val carDao: CarDao) :
    GetCarService,
    SaveCarService,
    GetCarsService,
    GetCarsByColorService {

    override fun save(car: Car): Long {
        val carEntity = toCarEntity(car)
        return carDao.insert(carEntity)
    }

    override fun getCars(): List<Car> {
        val entities = carDao.getAll()
        return entities.map { toCar(it) }
    }

    override fun getById(id: Long): Car {
        val carEntity = carDao.getById(id)
        return toCar(carEntity)
    }

    override fun getByColor(color: Color): List<Car> {
        val entities = carDao.getByColor(color.name)
        return entities.map { toCar(it) }
    }

    private fun toCarEntity(car: Car) = CarEntity(
        null,
        car.brand.name,
        car.bodyType.name,
        car.color.name,
        car.price,
        car.engineCapacityLiters
    )

    private fun toCar(carEntity: CarEntity) = Car(
        CarBrand.valueOf(carEntity.brand),
        BodyType.valueOf(carEntity.bodyType),
        Color.valueOf(carEntity.color),
        carEntity.price,
        carEntity.engineCapacityLiters
    )

}