package com.example.superheroes.interfaces

import com.example.superheroes.models.SuperHeroDetail

interface ISuperHeroDetail {
    suspend fun superHeroDetailSuccess(superheroDetailResponse: SuperHeroDetail?)
    fun superHeroDetailError(errorMessage: String)
}