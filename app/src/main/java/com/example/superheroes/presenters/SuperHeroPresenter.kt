package com.example.superheroes.presenters

import android.util.Log
import com.example.superheroes.interfaces.ISuperHeroDetail
import com.example.superheroes.models.SuperHeroDetail
import com.example.superheroes.services.SuperHeroDetailService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse
import java.lang.Exception

class SuperHeroPresenter (private val mView: ISuperHeroDetail) {
    private val api: SuperHeroDetailService.SuperHeroDetailApi = SuperHeroDetailService().getSuperHeroDetailService()

    fun getSuperHeroDetail(id: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: Response<SuperHeroDetail> = api.getSuperHeroDetail(id).awaitResponse()
                if (response.isSuccessful) {
                    mView.superHeroDetailSuccess(response.body())
                }
                Log.d("RESPONSE", response.body().toString())
            } catch (error: Exception) {
                Log.d("RESPONSE", error.toString())
                mView.superHeroDetailError(error.toString())
            }

        }
    }
}