package com.spamerl.pokedex_compose.ui.screens.details

import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.kryak.domain.model.PokemonDetail

@Composable
fun DetailScreen(
    viewModel: DetailsViewModel,
    modifier: Modifier = Modifier
) {
    val info = viewModel.state.collectAsState()
    Body(dataState = info.value, modifier = modifier)
}

@Composable
fun Body(
    dataState: PokemonDetail,
    modifier: Modifier
) {
    Column {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            modifier = modifier
                .fillMaxSize()
                .size(200.dp)
                .padding(bottom = 10.dp)
                .weight(
                    weight = 2f,
                    fill = false
                )
        ) {
            Column(
                modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = dataState.name, style = MaterialTheme.typography.h4)
                Box(
                    modifier.fillMaxSize()
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(dataState.sprites)
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = null,
                        modifier.fillMaxSize()
                    )
                }
            }
        }
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 10.dp,
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 10.dp)
                .weight(1f)
        ) {
            Column {
                Column(
                    modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Pokemon Stats:",
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier.padding(15.dp)
                ) {
                    Text(text = "hp: ${dataState.hp}", modifier.padding(bottom = 5.dp))
                    Text(text = "defence: ${dataState.defense}", modifier.padding(bottom = 5.dp))
                    Text(text = "attack: ${dataState.attack}", modifier.padding(bottom = 5.dp))
                    Text(text = "speed: ${dataState.speed}", modifier.padding(bottom = 5.dp))
                    Text(text = "special attack: ${dataState.special_attack}", modifier.padding(bottom = 5.dp))
                    Text(text = "special defence: ${dataState.special_defense}", modifier.padding(bottom = 5.dp))
                }
            }
        }
    }
}

// todo: change numeric stats representation to "radar chart"
