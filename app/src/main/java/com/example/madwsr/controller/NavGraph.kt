package com.example.madwsr.controller

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.madwsr.model.DataModel
import com.example.madwsr.model.feeling.MainViewModel
import com.example.madwsr.ui.view.*

@Composable
fun SetUpOnGraph(navController:NavHostController,model:DataModel,data:MainViewModel){
    NavHost(navController = navController, startDestination = Screen.Splash.route){
        composable(Screen.Splash.route){
            SplashScreen(navController,model)
        }
        composable(Screen.Main.route){
            MainScreen(model,navController,data)
        }
        composable(Screen.Login.route){
            LoginScreen(navController = navController,model,data)
        }
        composable(Screen.OnBoard.route){
            OnBoarding(navController = navController)
        }

        composable(Screen.Registr.route){
            RegistrScreen()
        }

        composable(Screen.Menu.route){
            MenuScreen()
        }

        composable(Screen.Listen.route){
            ListenScreen()
        }

        composable(Screen.Photo.route){
            PhotoScreen(model,navController)
        }

    }
}


@Composable
fun BottomSetUpOnGraph(navController:NavHostController,model: DataModel,data: MainViewModel,navControllerAll:NavHostController){
    NavHost(navController = navController, startDestination = BottomScreen.Home.route){
        composable(BottomScreen.Home.route){
            HomeScreen(model,data)
        }
        composable(BottomScreen.Profile.route){
            ProfileScreen(model,navControllerAll)
        }
        composable(BottomScreen.Sound.route){
            SoundScreen()
        }

    }
}