package com.example.madwsr.model.feeling

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madwsr.controller.RetrofitInterface
import com.example.madwsr.model.user.UserG
import com.example.madwsr.model.user.UserP
import kotlinx.coroutines.launch
import kotlin.Exception


//class MainViewModel2:ViewModel(){
//    var feelingsResponse: List<Data> by mutableStateOf(listOf())
//    var errorMessage by mutableStateOf("")
//
//    fun getFeelings(){
//        viewModelScope.launch{
//            val apiService = RetrofitInterface.getInstance()
//            try{
//                feelingsResponse = apiService.getFeelings().data
//            }catch (e:Exception){
//                errorMessage = e.message.toString()
//            }
//        }
//    }
//
//}

class MainViewModel :ViewModel(){
    var errorMessage by mutableStateOf("")
    var feelingsResponse:List<Data> by mutableStateOf(listOf())
    var quotesResponse:List<com.example.madwsr.model.quotes.Data> by mutableStateOf(listOf())


    var messageEmailResponse:UserG by mutableStateOf(UserG("","","","qwe",""))

    fun getQuotes(){
        viewModelScope.launch{
            val apiService = RetrofitInterface.getInstance()
            try{
                quotesResponse = apiService.getQuotes().data
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }

    fun getFeelings(){
        viewModelScope.launch{
            val apiService = RetrofitInterface.getInstance()
            try{
                feelingsResponse = apiService.getFeelings().data
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }

    fun postEmails(email:String,password:String){
        viewModelScope.launch {
            try{
                val body = UserP(email,password)
                val result = RetrofitInterface.getInstance().postData(body)
                messageEmailResponse = result
            }catch(e:Exception){
                Log.d("ERROR_RETROFITE",e.message.toString())
            }
        }
    }

}