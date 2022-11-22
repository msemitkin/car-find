package com.github.msemitkin.cars.catalog.service

class StatisticsService(private val getCarsService: GetCarsService) {

    fun getAverageEngineCapacity() = getCarsService.getCars()
        .map { it.engineCapacityLiters }
        .average()
}