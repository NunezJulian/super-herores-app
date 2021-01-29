package com.example.superheroes.models


import com.google.gson.annotations.SerializedName

data class WorkX(
    @SerializedName("base")
    val base: String,
    @SerializedName("occupation")
    val occupation: String
)