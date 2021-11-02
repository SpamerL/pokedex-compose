package com.spamerl.pokedex_compose.data.remote.model

import androidx.compose.ui.graphics.Color

data class CombinedList(
    var count: Int,
    var next: String?,
    var previous: String?,
    var combinedResults: MutableList<CombinedListItem>
)

data class CombinedListItem(
    var id: Int,
    val name: String,
    val image: String,
    var color: Color
)
