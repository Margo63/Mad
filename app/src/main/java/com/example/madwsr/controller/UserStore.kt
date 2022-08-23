package com.example.madwsr.controller

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStore(private val context:Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("email")
        val USER_KEY = stringPreferencesKey("EMAIL_KEY")
    }

    val getEmail: Flow<String?> =context.dataStore.data.map { pref->
        pref[USER_KEY] ?: ""
    }
    suspend fun saveEmail(email:String){
        context.dataStore.edit { pref->

            pref[USER_KEY] = email
        }
    }

}