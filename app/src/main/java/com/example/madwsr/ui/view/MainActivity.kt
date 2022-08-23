package com.example.madwsr.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.madwsr.controller.SetUpOnGraph
import com.example.madwsr.controller.readData
import com.example.madwsr.model.DataModel
import com.example.madwsr.model.feeling.MainViewModel

class MainActivity : ComponentActivity() {
    val data:MainViewModel by viewModels()
    val dataModel:DataModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  val name = readData(this,"name")
        //  dataModel.onTextChange(name)

        val txt = readData(this,"name")
        val img = readData(this,"img")
        dataModel.onImgChange(img)
        dataModel.onTextChange(txt)
        setContent {
            //Prep1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var navController= rememberNavController()
                    SetUpOnGraph(navController = navController,dataModel,data)
                }

        }
    }
}
