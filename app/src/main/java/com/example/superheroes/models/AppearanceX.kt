package com.example.superheroes.models


import com.google.gson.annotations.SerializedName

data class AppearanceX(
    @SerializedName("eyeColor")
    val eyeColor: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("hairColor")
    val hairColor: String,
    @SerializedName("height")
    val height: List<String>,
    @SerializedName("race")
    val race: Any,
    @SerializedName("weight")
    val weight: List<String>
)