package com.github.msemitkin.cars.catalog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.msemitkin.cars.catalog.components.ApplicationHeader
import com.github.msemitkin.cars.catalog.screens.AddNewCarScreen
import com.github.msemitkin.cars.catalog.screens.Homepage

@Composable
fun CarsCatalogNavigable(
    navHostController: NavHostController = rememberNavController()
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
                AddNewCarScreen()
            }
        }
    }
}
