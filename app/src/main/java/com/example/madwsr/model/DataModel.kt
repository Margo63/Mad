package com.example.madwsr.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

//class DataModel():ViewModel() {
//    var text by mutableStateOf("")
//
//    fun onTextChange(newText:String){
//        text=newText
//    }
//}

class DataModel():ViewModel(){
    var text by mutableStateOf("")
    var img by mutableStateOf("")
    fun onTextChange(newText:String){
        text=newText
    }
    fun onImgChange(newIng:String){
        img=newIng
    }
}