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
import com.github.msemitkin.cars.catalog.dao.GetCarService
import com.github.msemitkin.cars.catalog.dao.SaveCarService
import com.github.msemitkin.cars.catalog.screens.AddNewCarScreen
import com.github.msemitkin.cars.catalog.screens.CarScreen
import com.github.msemitkin.cars.catalog.screens.Homepage

@Composable
fun CarsCatalogNavigable(
    navHostController: NavHostController = rememberNavController(),
    saveCarService: SaveCarService,
    getCarService: GetCarService
) {
    Scaffold(
        topBar = { ApplicationHeader() }
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
                    onSearchClick = { navHostController.navigate("search") }
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
            composable(
                route = "cars/{carId}",
                arguments = listOf(
                    navArgument("carId") { apply { type = NavType.LongType }.build() }
                )
            ) { backStackEntry ->
                val carId = requireNotNull(backStackEntry.arguments?.getLong("carId"))
                val car = getCarService.getById(carId)
                CarScreen(car = car)
            }
        }
    }
}
