package com.example.superheroes.models


import com.google.gson.annotations.SerializedName

data class ConnectionsX(
    @SerializedName("groupAffiliation")
    val groupAffiliation: String,
    @SerializedName("relatives")
    val relatives: String
)