package br.com.digio.androidtest.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import br.com.digio.androidtest.R
import br.com.digio.androidtest.domain.model.Product
import br.com.digio.androidtest.presentation.ui.theme.DigioFont
import br.com.digio.androidtest.presentation.ui.theme.dp20
import br.com.digio.androidtest.presentation.ui.theme.dp4

@Composable
internal fun ProductsCarousel(modifier: Modifier = Modifier, data: List<Product>) {
    Column {
        Text(
            modifier = modifier.padding(bottom = dp20, start = dp4, end = dp4),
            text = stringResource(id = R.string.txt_main_text_products),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.dark_blue_grey_three),
            fontFamily = DigioFont
        )
        LazyRow {
            items(data) { product ->
                ProductItem(product = product)
            }
        }
    }
}
