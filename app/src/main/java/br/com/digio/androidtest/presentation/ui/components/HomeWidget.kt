package br.com.digio.androidtest.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.digio.androidtest.domain.model.DigioProducts
import br.com.digio.androidtest.presentation.ui.theme.AndroidTestTheme
import br.com.digio.androidtest.presentation.ui.theme.dp16

@Composable
internal fun HomeWidget(modifier: Modifier, data: DigioProducts) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            HeaderComponent()
        }
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(dp16)) {
                items(data.spotlight) { item ->
                    SpotlightBanner(modifier, item)
                }
            }
        }
        item {
            DigioCashBanner(modifier, data.cash)
        }
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(dp16)) {
                items(data.products) { product ->
                    ProductItem(modifier, product)
                }
            }
        }
    }
}

@Composable
@Preview
private fun HomeWidgetPreview() {
    AndroidTestTheme {

    }
}
