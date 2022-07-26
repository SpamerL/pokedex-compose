package com.kryak.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val id: Int,
    val prevKey: Int?,
    val nextKey: Int?
)