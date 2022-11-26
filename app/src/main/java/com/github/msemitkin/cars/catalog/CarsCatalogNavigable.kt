package com.github.msemitkin.cars.catalog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.msemitkin.cars.catalog.components.ApplicationHeader
import com.github.msemitkin.cars.catalog.models.BodyType
import com.github.msemitkin.cars.catalog.models.Color
import com.github.msemitkin.cars.catalog.screens.AddNewCarScreen
import com.github.msemitkin.cars.catalog.screens.AuthorScreen
import com.github.msemitkin.cars.catalog.screens.CarScreen
import com.github.msemitkin.cars.catalog.screens.CarsScreen
import com.github.msemitkin.cars.catalog.screens.Homepage
import com.github.msemitkin.cars.catalog.screens.SearchScreen
import com.github.msemitkin.cars.catalog.screens.StatisticsScreen
import com.github.msemitkin.cars.catalog.service.GetCarService
import com.github.msemitkin.cars.catalog.service.GetCarsByColorService
import com.github.msemitkin.cars.catalog.service.SaveCarService
import com.github.msemitkin.cars.catalog.service.SearchCriteria
import com.github.msemitkin.cars.catalog.service.StatisticsService

@Composable
fun CarsCatalogNavigable(
    navHostController: NavHostController = rememberNavController(),
    saveCarService: SaveCarService,
    getCarService: GetCarService,
    getCarsByColorService: GetCarsByColorService,
    statisticsService: StatisticsService,
) {
    Scaffold(
        topBar = {
            ApplicationHeader(
                onAuthorClick = { navHostController.navigate("author") },
                onAppNameClick = { navHostController.navigate("homepage") }
            )
        }
    ) {
        NavHost(
            navController = navHostController,
            startDestination = "homepage",
            modifier = Modifier
                .fillMaxWidth()
        ) {
            composable("homepage") {
                Homepage(
                    onAddNewClick = { navHostController.navigate("add_new_car") },
                    onSearchClick = { navHostController.navigate("search") },
                    onStatisticsClick = { navHostController.navigate("statistics") }
                )
            }
            composable("add_new_car") {
                AddNewCarScreen(
                    onSave = {
                        val id = saveCarService.save(it)
                        navHostController.navigate("cars/$id")
                    }
                )
            }
            composable("search") {
                SearchScreen(
                    onSearchClick = { color, bodyType ->
                        navHostController.navigate("cars?color=${color.name}&bodyType=${bodyType.name}")
                    }
                )
            }
            composable(
                route = "cars?color={color}&bodyType={bodyType}",
                arguments = listOf(
                    navArgument("color") { type = NavType.StringType },
                    navArgument("bodyType") { type = NavType.StringType },
                )
            ) { backStackEntry ->
                val color = backStackEntry.arguments
                    ?.getString("color")!!
                    .let { Color.valueOf(it) }
                val bodyType = backStackEntry.arguments
                    ?.getString("bodyType")!!
                    .let { BodyType.valueOf(it) }
                val cars = getCarsByColorService.getByCriteria(SearchCriteria(color, bodyType))
                CarsScreen(cars)
            }
            composable(
                route = "cars/{carId}",
                arguments = listOf(
                    navArgument("carId") { type = NavType.LongType }
                )
            ) { backStackEntry ->
                val carId = requireNotNull(backStackEntry.arguments?.getLong("carId"))
                val car = getCarService.getById(carId)
                CarScreen(car = car)
            }
            composable("statistics") {
                val averageEngineCapacity = statisticsService.getAverageEngineCapacity()
                StatisticsScreen(averageEngineCapacity)
            }
            composable("author") {
                AuthorScreen()
            }
        }
    }
}
