package com.example.madwsr.controller

import android.content.Context
import android.content.SharedPreferences


fun readData(context:Context,key:String):String{
    val sharedPreferences:SharedPreferences = context.getSharedPreferences(
        "shared",Context.MODE_PRIVATE
    )
    return sharedPreferences.getString(key,"ERROR")!!
}

fun saveData(context:Context,key:String,string:String){
    val sharedPreferences:SharedPreferences = context.getSharedPreferences(
        "shared",Context.MODE_PRIVATE
    )

    val editor = sharedPreferences.edit()
    editor.putString(key,string)
    editor.apply()
}