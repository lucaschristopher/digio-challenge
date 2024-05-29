package br.com.digio.androidtest.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.digio.androidtest.domain.model.DigioProducts
import br.com.digio.androidtest.presentation.ui.theme.AndroidTestTheme
import br.com.digio.androidtest.presentation.ui.theme.dp14
import br.com.digio.androidtest.presentation.ui.theme.dp24

@Composable
internal fun HomeWidget(
    modifier: Modifier = Modifier,
    data: DigioProducts
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dp14, vertical = dp24)
    ) {
        item { HeaderComponent() }
        item { SpotlightCarousel(data.spotlight) }
        item { CashBannerComponent(cash = data.cash) }
        item { ProductsCarousel(modifier, data.products) }
    }
}

@Composable
@Preview
private fun HomeWidgetPreview() {
    AndroidTestTheme {

    }
}
