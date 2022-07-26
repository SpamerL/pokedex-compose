package com.kryak.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val color: String,
    val image: String
)
