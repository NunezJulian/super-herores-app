package com.example.superheroes.interfaces

import com.example.superheroes.models.AllHeroesData

interface IAllHeroes {
    suspend fun allHeroesSuccess(allHeroesResponse: AllHeroesData?)
    fun allHeroesError(errorMessage: String)
}