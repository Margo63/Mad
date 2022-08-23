package com.example.madwsr.ui.view

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.madwsr.R
import com.example.madwsr.controller.BottomSetUpOnGraph
import com.example.madwsr.controller.RetrofitInterface
import com.example.madwsr.controller.saveData
import com.example.madwsr.model.DataModel
import com.example.madwsr.model.feeling.MainViewModel
import com.example.madwsr.model.user.UserG
import com.example.madwsr.model.user.UserP
import com.example.madwsr.ui.theme.BackgroundColor
import com.example.madwsr.ui.theme.ButtonColor
import com.example.madwsr.ui.theme.TextColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun SplashScreen(navController: NavHostController, model: DataModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .width(202.dp)
                .height(213.dp),
            tint = Color.White
        )
    }
    LaunchedEffect(key1 = true) {
        delay(1000)
        if (model.text == "ERROR") {

            navController.popBackStack()
            navController.navigate(Screen.OnBoard.route)
        } else {
            navController.navigate(Screen.Main.route)
        }

    }
}

@Composable
fun OnBoarding(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(50.dp))
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(199.dp)
            )
            Spacer(Modifier.height(32.dp))
            Text("ПРИВЕТ", fontSize = 34.sp, color = Color.White)
            Text(
                "Наслаждайся отборочными.\nБудь внимателен.\n" +
                        "Делай хорошо.",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(95.dp))
            Button(
                onClick = { navController.navigate(Screen.Login.route) },
                colors = ButtonDefaults.buttonColors(ButtonColor),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    "Войти в аккаунт",
                    color = Color.White,
                    fontSize = 23.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "Еще нет аккаунта? Зарегистрируйтесь",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.Registr.route)
                }
            )


        }
    }

}

@Composable
fun LoginScreen(navController: NavHostController, model: DataModel,mainModel:MainViewModel) {
    var email by remember {
        mutableStateOf("")
    }


    var password by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Box(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Image(
            painter = painterResource(id = R.drawable.green),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(BottomCenter),
            alignment = Alignment.BottomCenter,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(31.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 27.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .height(49.dp)
                    .width(43.41.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(31.dp))
            Text("Sign in", color = Color.White, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(111.dp))
            TextField(
                label = { Text("Email") },
                value = email,
                onValueChange = { email = it },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = TextColor,
                    textColor = TextColor,
                    focusedLabelColor = TextColor,
                    cursorColor = TextColor,
                    unfocusedIndicatorColor = TextColor,
                    focusedIndicatorColor = TextColor,
                    backgroundColor = BackgroundColor
                )
            )
            Spacer(modifier = Modifier.size(5.dp))
            TextField(
                label = { Text("Password") },
                value = password,
                onValueChange = { password = it },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedLabelColor = TextColor,
                    textColor = TextColor,
                    focusedLabelColor = TextColor,
                    cursorColor = TextColor,
                    unfocusedIndicatorColor = TextColor,
                    focusedIndicatorColor = TextColor,
                    backgroundColor = BackgroundColor

                )
            )
            Spacer(modifier = Modifier.height(55.dp))
            var checkBut by remember{
                mutableStateOf(false)
            }
            if(email.isNotBlank() && (email.matches(Regex(".*@.*")))){
                checkBut=true
                }else{
                    checkBut=false
            }
            Button(
                onClick = {
                    mainModel.postEmails(email,password)
                    val user = mainModel.messageEmailResponse
                    //Log.d("NICKNAME",user.nickName)
                    model.onTextChange(user.nickName)
                    saveData(context = context,"name",model.text)


                    navController.navigate(Screen.Main.route){
                        popUpTo(Screen.Main.route)
                    }



                },
                colors = ButtonDefaults.buttonColors(ButtonColor),
                modifier = Modifier
                    .fillMaxWidth(),
                enabled =checkBut
            ) {

                Text("Sign in", textAlign = TextAlign.Center, color = Color.White, fontSize = 25.sp)
            }

            Text(
                "Registr",
                modifier = Modifier
                    .padding(vertical = 23.dp)
                    .clickable {
                        navController.navigate(Screen.Registr.route)
                    },
                color = Color.White,
                fontSize = 20.sp
            )
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(ButtonColor),
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text("Профиль", textAlign = TextAlign.Center, color = Color.White, fontSize = 25.sp)
            }

        }

    }


}

@Composable
fun Hamburger(navController: NavHostController) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { navController.navigate(Screen.Menu.route) }) {
        Icon(
            painter = painterResource(id = R.drawable.hamberger),
            contentDescription = null,
            modifier = Modifier.size(23.dp),
            tint = Color.White
        )
    }

}

@Composable
fun Menu(navController: NavHostController,screen_n:String) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { if(screen_n=="profile_screen"){
        navController.navigate(Screen.Login.route)
    }
    }) {

        if (screen_n == "profile_screen") {
            Text("exit",color=Color.White)
        } else {
            Image(
                painter = painterResource(id = R.drawable.ellipse),
                contentDescription = null,
                modifier = Modifier.size(35.dp),

                )
        }
    }
//
}

@Composable
fun MainScreen(model: DataModel, navController2: NavHostController,data: MainViewModel) {
    val navController = rememberNavController()
    var screen_n by remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                 Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(49.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center

                )
                 }

            },
             navigationIcon = { Hamburger(navController2) },
            actions = { Menu(navController2,screen_n) },
            backgroundColor = BackgroundColor,

            )
    },
        bottomBar = {
            BottomNavigation(backgroundColor = BackgroundColor, contentColor = Color.White) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomItem.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.img),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                        },
                        //label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            screen_n=screen.route
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)

                    )
                }
            }
        }
    ) {
        BottomSetUpOnGraph(navController = navController, model,data,navControllerAll=navController2)
    }
}

@Composable
fun RegistrScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundColor), contentAlignment = Center) {
        Text(
            "Тут будет\n" +
                    "регистрация",
            fontSize = 30.sp,
            color = Color.White,
        )
    }
}


@Composable
fun MenuScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundColor), contentAlignment = Center) {
        Text(
            "Тут будет меню",
            fontSize = 30.sp,
            color = Color.White,
        )
    }
}


@Composable
fun ListenScreen() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundColor), contentAlignment = Center,) {
        Text(
            "Тут будет \n" +
                    "прослушивание",
            fontSize = 30.sp,
            color = Color.White,

        )


    }
}

