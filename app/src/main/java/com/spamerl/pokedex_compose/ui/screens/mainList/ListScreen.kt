package com.spamerl.pokedex_compose.ui.screens.mainList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.spamerl.pokedex_compose.data.remote.model.CombinedListItem

@ExperimentalFoundationApi
@Composable
fun ListScreen(
    viewModel: ListScreenViewModel,
    select: (CombinedListItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val data = viewModel.list.collectAsLazyPagingItems()
    Body(data = data, select = select, modifier = modifier)
}

@ExperimentalFoundationApi
@Composable
fun Body(
    data: LazyPagingItems<CombinedListItem>,
    select: (CombinedListItem) -> Unit,
    modifier: Modifier
) {

    val rows = (data.itemCount + 1) / 2
    
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        content = {
            items(data.itemCount) { index ->
                GridItem(data = data[index], modifier = modifier, select = select)
            }
        })
}

@Composable
fun GridItem(
    data: CombinedListItem?,
    select: (CombinedListItem) -> Unit,
    modifier: Modifier
) {
    Card(modifier.fillMaxWidth().padding(5.dp).clickable { select.invoke(data!!) }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(data!!.name)

            Image(
                painter = rememberImagePainter(data = data.image),
                contentDescription = "pokemon Image",
                modifier.size(128.dp)
            )
        }
    }
}
