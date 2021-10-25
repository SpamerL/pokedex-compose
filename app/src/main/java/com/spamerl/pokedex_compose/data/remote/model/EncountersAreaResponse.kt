package com.spamerl.pokedex_compose.data.remote.model


import com.google.gson.annotations.SerializedName

class Encounters_area : ArrayList<Encounters_area.Encounters_areaItem>(){
    data class Encounters_areaItem(
        @SerializedName("location_area")
        val locationArea: LocationArea,
    ) {
        data class LocationArea(
            val name: String,
            val url: String
        )
    }
}