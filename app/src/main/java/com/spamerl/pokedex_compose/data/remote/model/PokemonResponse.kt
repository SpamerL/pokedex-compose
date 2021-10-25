package com.spamerl.pokedex_compose.data.remote.model


import com.google.gson.annotations.SerializedName
import com.spamerl.pokedex_compose.domain.model.PokemonModel

data class PokemonResponse(
    val abilities: List<Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val forms: List<Form>,
    val height: Int,
    @SerializedName("held_items")
    val heldItems: List<Any>,
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    @SerializedName("past_types")
    val pastTypes: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) {
    data class Ability(
        val ability: Ability,
        @SerializedName("is_hidden")
        val isHidden: Boolean,
        val slot: Int
    ) {
        data class Ability(
            val name: String,
            val url: String
        )
    }

    data class Form(
        val name: String,
        val url: String
    )

    data class Move(
        val move: Move,
        @SerializedName("version_group_details")
        val versionGroupDetails: List<VersionGroupDetail>
    ) {
        data class Move(
            val name: String,
            val url: String
        )

        data class VersionGroupDetail(
            @SerializedName("level_learned_at")
            val levelLearnedAt: Int,
            @SerializedName("move_learn_method")
            val moveLearnMethod: MoveLearnMethod,
            @SerializedName("version_group")
            val versionGroup: VersionGroup
        ) {
            data class MoveLearnMethod(
                val name: String,
                val url: String
            )

            data class VersionGroup(
                val name: String,
                val url: String
            )
        }
    }

    data class Species(
        val name: String,
        val url: String
    )

    data class Sprites(
        @SerializedName("back_default")
        val backDefault: String,
        @SerializedName("front_default")
        val frontDefault: String
    )

    data class Stat(
        @SerializedName("base_stat")
        val baseStat: Int,
        val effort: Int,
        val stat: Stat
    ) {
        data class Stat(
            val name: String,
            val url: String
        )
    }

    data class Type(
        val slot: Int,
        val type: Type
    ) {
        data class Type(
            val name: String,
            val url: String
        )
    }
}

fun PokemonResponse.toPokemonModel() = PokemonModel (
    id = id,
    name = name,
    baseExperience = baseExperience,
    species = species,
    sprite = sprites.frontDefault,
    height = height,
    weight = weight,
    locationAreaEncountersURL = locationAreaEncounters,
    hp = stats[0].baseStat,
    attack = stats[1].baseStat,
    defense = stats[2].baseStat,
    special_attack = stats[3].baseStat,
    special_defense = stats[4].baseStat,
    speed = stats[5].baseStat
)