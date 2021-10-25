package com.spamerl.pokedex_compose.data.remote.model

data class PagedResponse<T>(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<T> = listOf()
)
