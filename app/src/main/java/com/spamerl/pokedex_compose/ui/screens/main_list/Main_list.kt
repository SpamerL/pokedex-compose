@file:OptIn(ExperimentalFoundationApi::class)

package com.spamerl.pokedex_compose.ui.screens.main_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.kryak.domain.model.PokemonListModel

@Composable
fun MainList(
    viewModel: MainListViewModel,
    select: (PokemonListModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val info = viewModel.state.collectAsLazyPagingItems()
    Body(
        list = info,
        select = select,
        modifier = modifier
    )
}

@ExperimentalFoundationApi
@Composable
fun Body(
    list: LazyPagingItems<PokemonListModel>,
    select: (PokemonListModel) -> Unit,
    modifier: Modifier
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        content = {
            items(list.itemCount) { index: Int ->
                ItemCard(
                    pokemon = list[index],
                    select = select,
                    modifier = modifier
                )
            }
        }
    )
}

@Composable
fun ItemCard(
    pokemon: PokemonListModel?,
    select: (PokemonListModel) -> Unit,
    modifier: Modifier
) {
    val color = when (pokemon!!.color) {
        "black" -> Color.Black
        "blue" -> Color.Blue
        "brown" -> Color(165, 42, 42)
        "gray" -> Color.Gray
        "green" -> Color.Green
        "pink" -> Color(255, 192, 203)
        "purple" -> Color(128, 0, 128)
        "red" -> Color.Red
        "yellow" -> Color.Yellow
        else -> { Color.White }
    }
    Card(
        shape = CutCornerShape(
            topStart = 40.dp,
            topEnd = 0.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ),
        elevation = 10.dp,
        modifier = modifier
            .padding(10.dp)
            .clickable { select.invoke(pokemon) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = modifier
                        .size(20.dp)
                        .clip(
                            shape = CutCornerShape(
                                topStart = 0.dp,
                                topEnd = 4.dp,
                                bottomStart = 4.dp,
                                bottomEnd = 0.dp
                            )
                        )
                        .background(color)
                )
                Text(
                    text = pokemon.id.toString(),
                    modifier
                        .padding(10.dp)
                )
            }
            Box(
                modifier
                    .size(200.dp)
                    .padding(20.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(pokemon.image)
                        .crossfade(true)
                        .size(200, 200)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = null,
                    modifier.size(200.dp)
                )
            }

            Text(
                text = pokemon.name,
                modifier.padding(10.dp)
            )
        }
    }
}
