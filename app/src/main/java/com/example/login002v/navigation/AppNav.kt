package com.example.login002v.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.login002v.ui.home.MuestraDatosScreen
import com.example.login002v.ui.login.LoginScreen

@Composable


fun AppNav(){

    //reamos controlador
    val navController = rememberNavController()

    NavHost( navController= navController, startDestination = "login")
    {
        composable("login"){
            LoginScreen(navController= navController)
        }//fin composable

        composable(
            route="muestraDatos/{username}",
            arguments = listOf(
                navArgument("username"){
                    type= NavType.StringType
                }
            )//fin List Of
        )//fin composable

        { // inicio
            backStackEntry ->
            val username = backStackEntry.arguments?.getString("username").orEmpty()
            MuestraDatosScreen(username=username,navController= navController )

        }




    }// Fin NavHost

}//fin AppNav