package com.example.login002v.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.login002v.ui.home.MuestraDatosScreen
import com.example.login002v.ui.login.LoginScreen
import com.example.login002v.view.DrawerMenu
import com.example.login002v.view.ProductoFormScreen
import com.example.login002v.view.ReinspeccionScreen
import com.example.login002v.view.MarcacionesDiariasScreen  // ✅ IMPORTAR MarcacionesDiariasScreen
import java.net.URLDecoder

@Composable
fun AppNav() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(navController = navController)
        }

        composable(
            route = "DrawerMenu/{username}",
            arguments = listOf(
                navArgument("username") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username").orEmpty()
            DrawerMenu(username = username, navController = navController)
        }

        composable(
            route = "muestraDatos/{username}",
            arguments = listOf(
                navArgument("username") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username").orEmpty()
            MuestraDatosScreen(username = username, navController = navController)
        }

        composable(
            route = "ProductoFormScreen/{nombre}/{precio}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("precio") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombreCodificado = backStackEntry.arguments?.getString("nombre").orEmpty()
            val nombre = URLDecoder.decode(nombreCodificado, "UTF-8")
            val precio = backStackEntry.arguments?.getString("precio").orEmpty()

            ProductoFormScreen(
                navController = navController,
                nombre = nombre,
                precio = precio
            )
        }

        composable("reinspeccion") {
            val viewModel: com.example.login002v.viewmodel.ReinspeccionViewModel = viewModel()

            ReinspeccionScreen(
                navController = navController,
                vm = viewModel
            )
        }

        // ✅ AGREGAR RUTA "marcacionesdiarias"
        composable("marcacionesdiarias") {
            MarcacionesDiariasScreen(navController = navController)
        }
    }
}