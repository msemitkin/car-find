package com.github.msemitkin.cars.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.github.msemitkin.cars.catalog.dao.CarService
import com.github.msemitkin.cars.catalog.dao.Database
import com.github.msemitkin.cars.catalog.service.StatisticsService
import com.github.msemitkin.cars.catalog.ui.theme.CarscatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarscatalogTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val db = getDb()
                    val carService = CarService(db.carDao())
                    CarsCatalogNavigable(
                        saveCarService = carService,
                        getCarService = carService,
                        statisticsService = StatisticsService(carService)
                    )
                }
            }
        }
    }

    private fun getDb(): Database {
        return Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "cars-catalog"
        ).allowMainThreadQueries()
            .build()
    }
}
