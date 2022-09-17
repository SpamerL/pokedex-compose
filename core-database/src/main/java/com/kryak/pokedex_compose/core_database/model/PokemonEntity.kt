package com.kryak.pokedex_compose.core_database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kryak.pokedex_compose.core_model.PokemonModel

@Entity
data class PokemonEntity(
    val name: String = "",
    @PrimaryKey
    val id: Int = 0,
    val image: String,
    val hp: Int = 0,
    val attack: Int = 0,
    val defense: Int = 0,
    val speed: Int = 0,
    val special_attack: Int = 0,
    val special_defense: Int = 0,
    val weight: Int,
    val height: Int,
    val favorite: Boolean = false
)

fun PokemonEntity.asExternalModel() = PokemonModel(
    name = name,
    id = id,
    image = image,
    hp = hp,
    attack = attack,
    defense = defense,
    speed = speed,
    special_attack = special_attack,
    special_defense = special_defense,
    weight = weight,
    height = height,
    favorite = favorite
)
