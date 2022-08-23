package com.example.madwsr.ui.view

import com.example.madwsr.R

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object OnBoard : Screen("onboard_screen")
    object Login : Screen("login_screen")
    object Main : Screen("main_screen")
    object Registr : Screen("registr_screen")
    object Menu : Screen("menu_screen")
    object Listen : Screen("listen_screen")
    object Photo : Screen("photo_screen")
}

sealed class BottomScreen(val route: String, val img: Int) {
    object Profile : BottomScreen("profile_screen", R.drawable.profile)
    object Home : BottomScreen("home_screen", R.drawable.logo)
    object Sound : BottomScreen("sound_screen", R.drawable.sound)
}

var bottomItem = listOf(
    BottomScreen.Home,
    BottomScreen.Sound,
    BottomScreen.Profile
)