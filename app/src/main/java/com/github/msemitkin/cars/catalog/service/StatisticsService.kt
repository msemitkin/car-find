package com.github.msemitkin.cars.catalog.service

import com.github.msemitkin.cars.catalog.dao.GetCarsService

class StatisticsService(private val getCarsService: GetCarsService) {

    fun getAverageEngineCapacity() = getCarsService.getCars()
        .map { it.engineCapacityLiters }
        .average()
}