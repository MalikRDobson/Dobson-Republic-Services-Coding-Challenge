package com.dobson.apps.rs_coding_challenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dobson.apps.rs_coding_challenge.ui.detail.DriverRouteDetailScreen
import com.dobson.apps.rs_coding_challenge.ui.main.DriverRouteMainScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            DriverRouteMainScreen(navController = navController)
        }
        composable("${Screen.Detail.route}/{id}",
            arguments = listOf(navArgument("id"){type= NavType.IntType})
        ) {
            DriverRouteDetailScreen(navController=navController)
        }
    }

}