package com.example.nextstep.di

import android.content.Context
import com.example.nextstep.data.repository.AppRepository
import com.example.nextstep.data.repository.AuthRepository
import com.example.nextstep.data.retrofit.ApiConfig
import com.example.nextstep.data.retrofit.ApiService
import com.example.nextstep.preference.AppPreference
import com.example.nextstep.preference.TokenPreference
import com.example.nextstep.preference.datastore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {

    fun authRepository(context: Context): AuthRepository{
        val apiService = ApiConfig.getApiService()
        val pref = AppPreference.getInstance(context.datastore)
        return AuthRepository.getInstance(apiService, pref)
    }

    fun appRepository(context: Context): AppRepository{
        val pref = AppPreference.getInstance(context.datastore)
        val apiService = ApiConfig.getApiService()
        return AppRepository.getInstance(apiService, pref)
    }

    /*fun appRepository(context: Context): AppRepository{
        val pref = AppPreference.getInstance(context.datastore)
        val token = runBlocking { pref.getUserToken().first() }
        val apiService = ApiConfig.getApiServiceWithToken(token!!)
        return AppRepository.getInstance(apiService, pref)
    }*/

}