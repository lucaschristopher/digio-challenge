package br.com.digio.androidtest.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.digio.androidtest.R
import br.com.digio.androidtest.domain.model.Product
import br.com.digio.androidtest.presentation.ui.theme.AndroidTestTheme
import br.com.digio.androidtest.presentation.ui.theme.dp6
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
internal fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product
) {
    ElevatedCard(
        modifier = modifier.fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = dp6)
    ) {
        SubcomposeAsyncImage(
            modifier = modifier.fillMaxSize(),
            model = product.imageURL,
            alignment = Alignment.TopStart,
            contentDescription = stringResource(R.string.image_description_product_item)
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }
    }
}

@Composable
@Preview
private fun ProductBannerPreview() {
    AndroidTestTheme {
        val product = Product(
            name = "XBOX",
            imageURL = "https://s3-sa-east-1.amazonaws.com/digio-exame/xbox_icon.png",
            description = "Com o e-Gift Card Xbox você adquire créditos para comprar games, música, filmes, programas de TV e muito mais!"

        )
        ProductItem(product = product)
    }
}