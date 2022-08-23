package com.example.madwsr.ui.view

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.madwsr.R
import com.example.madwsr.controller.saveData
import com.example.madwsr.model.DataModel
import com.example.madwsr.model.feeling.Data
import com.example.madwsr.model.feeling.MainViewModel
import com.example.madwsr.model.quotes.Quotes
import com.example.madwsr.ui.theme.BackgroundColor
import com.example.madwsr.ui.theme.CardColor


data class Feel(val icon: ImageVector, val text: String)
data class Frase(val icon: ImageVector, val text: String, val title: String)

@Composable
fun HomeScreen(model: DataModel, mainVIewModel: MainViewModel) {
    mainVIewModel.getFeelings()
    val feelings: List<Data> = mainVIewModel.feelingsResponse
    mainVIewModel.getQuotes()
    val quotes: List<com.example.madwsr.model.quotes.Data> = mainVIewModel.quotesResponse

    Column(
        modifier = Modifier
            .background(BackgroundColor)
            .fillMaxSize()
            .padding(25.dp)
    ) {
        Text("С возвращением," + model.text, fontSize = 30.sp, color = Color.White)
        Text("Каким ты себя ощущаешь сегодня?", fontSize = 22.sp, color = Color.White)
        //  Text(feelings.toString())
        LazyRow() {
            items(feelings.size) { index ->
                Card(
                    modifier = Modifier.padding(19.dp).align(Alignment.CenterHorizontally),
                    backgroundColor = BackgroundColor
                ) {
                    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(Color.White).padding(15.dp)
                        ) {
                            Icon(
                                painter = rememberAsyncImagePainter(feelings[index].image),
                                contentDescription = null,
                                modifier = Modifier.size(36.dp)
                                    .background(Color.White),
                                tint = Color.Black
                            )
                        }

                        Text(text = feelings[index].title, color = Color.White, fontSize = 12.sp)
                    }
                }


            }
        }
        LazyColumn() {
            items(quotes.size) { index ->
                Card(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(13.dp), backgroundColor = CardColor, shape = RoundedCornerShape(20.dp)

                ) {
                    Image(
                        painter = rememberAsyncImagePainter(quotes[index].image.toString()),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.End).height(100.dp),
                        alignment = Alignment.CenterEnd,
                    )
                    Column(modifier = Modifier.padding(start = 30.dp, top = 22.dp, bottom = 23.dp)) {

                        Text(text = quotes[index].title, color = Color.Black, fontSize = 25.sp)
                        Text(text = quotes[index].description, color = Color.Black, fontSize = 15.sp)

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(BackgroundColor),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = "подбробнее",
                                fontSize = 15.sp,
                                color = Color.White,
                                modifier = Modifier.padding(start = 32.dp, top = 9.dp, end = 32.dp)
                            )
                        }

                    }

                }


            }
        }
    }
}



@Composable
fun PhotoScreen(model:DataModel, navController:NavHostController) {

    Box(modifier = Modifier.fillMaxSize().background(BackgroundColor), contentAlignment = Alignment.BottomCenter) {
        Image(painter = rememberAsyncImagePainter(model.img),contentDescription = null, modifier = Modifier.fillMaxWidth())
        Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.fillMaxWidth()){
            Button(onClick ={} , modifier = Modifier.align(Alignment.Bottom)){
                Text("Удалить")
            }
            Button(onClick = {navController.navigate(Screen.Main.route) }, modifier = Modifier.align(Alignment.Bottom)){
                Text("Закрыть")
            }
        }
    }
}

@Composable
fun ProfileScreen(model: DataModel,navControllerAll: NavHostController) {
    val list by remember {
        mutableStateOf(ArrayList<Uri?>())

    }
    val listCheck by remember{
        mutableStateOf(ArrayList<String>())
    }
    val context = LocalContext.current

    var imguri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imguri = uri
    }
    Column(
        modifier = Modifier
            .background(BackgroundColor)
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.ellipse),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally).size(150.dp)
        )
        Text(text = model.text, textAlign = TextAlign.Center, color = Color.White, fontSize = 35.sp)

//        LazyColumn {
//            items(list.size){ind->
//                Card(modifier=Modifier.height(150.dp).width(300.dp), shape = RoundedCornerShape(20.dp)){
//                    IconButton(onClick = {}){
//                        Image(painter = rememberAsyncImagePainter(model=list[ind]),contentDescription = null)
//                    }
//                  //  Text(list[ind])
//                }
//            }
//
//        }
//
        LazyColumn {
            items(listCheck.size){index->
                Card {
                    Text(listCheck[index])
                }
            }
        }
        Button(onClick = {
            launcher.launch("image/*")



        }, modifier = Modifier.size(50.dp)) {
            Icon(Icons.Default.Place, contentDescription = null)

        }
        Button(onClick = {
            model.onImgChange(imguri.toString())
            list.add(imguri)
            saveData(context, "img", model.img)
            listCheck.add(list.size.toString())
           // Log.d("SIZE_LIST",list.size.toString())
        }){
            Text("save")
        }



//        IconButton(onClick = {
//            navControllerAll.navigate(Screen.Photo.route)
//        }) {
//            Image(painter = rememberAsyncImagePainter(model = model.img), contentDescription = null)
//        }



    }

}

@Composable
fun SoundScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Тут будет \n" +
                    "прослушивание",
            fontSize = 30.sp,
            color = Color.White,

            )
    }
}