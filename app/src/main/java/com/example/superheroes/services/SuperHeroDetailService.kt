package com.example.superheroes.services

import com.example.superheroes.app.Services
import com.example.superheroes.models.SuperHeroDetail
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

class SuperHeroDetailService {
    val retrofitInstance: Retrofit = Services().getRetrofitInstance(false)

    interface SuperHeroDetailApi {
        @GET("{id}")
        fun getSuperHeroDetail(@Path("id") id: String): Call<SuperHeroDetail>
    }

    fun getSuperHeroDetailService():SuperHeroDetailService.SuperHeroDetailApi {
        return retrofitInstance.create(SuperHeroDetailApi::class.java)
    }

}