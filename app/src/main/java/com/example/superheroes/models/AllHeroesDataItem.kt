package com.example.superheroes.models


import com.google.gson.annotations.SerializedName

data class AllHeroesDataItem(
    @SerializedName("appearance")
    val appearance: AppearanceX,
    @SerializedName("biography")
    val biography: BiographyX,
    @SerializedName("connections")
    val connections: ConnectionsX,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: Images,
    @SerializedName("name")
    val name: String,
    @SerializedName("powerstats")
    val powerstats: PowerstatsX,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("work")
    val work: WorkX
)