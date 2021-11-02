package com.spamerl.pokedex_compose.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.spamerl.pokedex_compose.domain.model.PokemonModel

@Composable
fun DetailScreen(
    viewModel: DetailViewModel
) {
    val info = viewModel.pokemon.value
    Body(info)
}

@Composable
fun Body(info: PokemonModel) {
    Surface(Modifier.padding(horizontal = 12.dp, vertical = 0.dp)) {
        Column {
            Text(info.name,
                Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally))
            Image(painter = rememberImagePainter(info.image), contentDescription = "", modifier = Modifier.padding(8.dp).size(160.dp))
            Text("HP: " + info.hp)
            Text("Attack: " + info.attack)
            Text("Defense: " + info.defense)
            Text("Special attack: " + info.special_attack)
            Text("Special defense: " + info.special_defense)
            Text("Speed: " + info.speed)
        }
    }
}

/*
val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val special_attack: Int,
    val special_defense: Int
 */
@Preview
@Preview
@Composable
fun BodyPreview(){
    MaterialTheme(darkColors()) {
        Surface(Modifier.padding(horizontal = 12.dp, vertical = 0.dp)) {
            Column {
                Text("ivysaur",
                    Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally))
                Box(
                    Modifier
                        .size(150.dp)
                        .padding(8.dp)
                        .background(Color.DarkGray))
                Text("HP: 100")
                Text("Attack: 100")
                Text("Defense: 100")
                Text("Special attack: 100")
                Text("Special defense: 100")
                Text("Speed: 100")
            }
        }
    }
}