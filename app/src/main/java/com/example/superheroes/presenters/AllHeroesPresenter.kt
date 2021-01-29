package com.example.superheroes.presenters

import android.content.Context
import android.util.Log
import com.example.superheroes.interfaces.IAllHeroes
import com.example.superheroes.interfaces.ISuperHeroDetail
import com.example.superheroes.models.AllHeroesData
import com.example.superheroes.models.SuperHeroDetail
import com.example.superheroes.services.AllHeroesService
import com.example.superheroes.services.SuperHeroDetailService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse
import java.lang.Exception

class AllHeroesPresenter(private val mView: IAllHeroes) {
    private val api: AllHeroesService.AllHeroesApi = AllHeroesService().getAllHeroesService()

    fun getAllHeroes() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<AllHeroesData> = api.getSuperHeroDetail().awaitResponse()
                if (response.isSuccessful) {
                    mView.allHeroesSuccess(response.body())
                }
                Log.d("RESPONSE", response.body().toString())
            } catch (error: Exception) {
                Log.d("RESPONSE", error.toString())
                mView.allHeroesError(error.toString())
            }

        }
    }
}