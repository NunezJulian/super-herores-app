package com.example.superheroes.models


import com.google.gson.annotations.SerializedName

data class PowerstatsX(
    @SerializedName("combat")
    val combat: Int,
    @SerializedName("durability")
    val durability: Int,
    @SerializedName("intelligence")
    val intelligence: Int,
    @SerializedName("power")
    val power: Int,
    @SerializedName("speed")
    val speed: Int,
    @SerializedName("strength")
    val strength: Int
)