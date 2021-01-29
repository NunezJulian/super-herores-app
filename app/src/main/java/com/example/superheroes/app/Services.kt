package com.example.superheroes.app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Services {

    //Base URL
    val BASE_URL: String = "https://superheroapi.com/api/10156112965520834/"
    val ALL_HEROES_BASE_URL = "https://akabab.github.io/superhero-api/api/"

    fun getRetrofitInstance(showAllHeroes: Boolean): Retrofit {
        val gson: Gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

        return Retrofit.Builder()
            .baseUrl(if (showAllHeroes) ALL_HEROES_BASE_URL else BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}