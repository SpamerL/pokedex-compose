package com.spamerl.pokedex_compose.data.remote.model


import com.google.gson.annotations.SerializedName
import com.spamerl.pokedex_compose.domain.model.PokemonModel

data class PokemonResponse(
    val abilities: List<Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val height: Int,
    val id: Int,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
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
        @SerializedName("front_default")
        val frontDefault: String,
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
}

fun PokemonResponse.toPokemonUIModel() = PokemonModel(
    name = name,
    id = id,
    image = sprites.frontDefault,
    hp = stats[0].baseStat,
    attack = stats[1].baseStat,
    defense = stats[2].baseStat,
    special_attack = stats[3].baseStat,
    special_defense = stats[4].baseStat,
    speed = stats[5].baseStat
)