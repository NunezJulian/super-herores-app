package com.example.superheroes.services

import com.example.superheroes.app.Services
import com.example.superheroes.models.AllHeroesData
import com.example.superheroes.models.SuperHeroDetail
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

class AllHeroesService {

    val retrofitInstance: Retrofit = Services().getRetrofitInstance(true)

    interface AllHeroesApi {
        @GET("all.json")
        fun getSuperHeroDetail(): Call<AllHeroesData>
    }

    fun getAllHeroesService():AllHeroesService.AllHeroesApi {
        return retrofitInstance.create(AllHeroesApi::class.java)
    }
}