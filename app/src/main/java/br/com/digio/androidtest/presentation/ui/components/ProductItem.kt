package br.com.digio.androidtest.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.digio.androidtest.R
import br.com.digio.androidtest.domain.model.Product
import br.com.digio.androidtest.presentation.ui.theme.AndroidTestTheme
import br.com.digio.androidtest.presentation.ui.theme.dp150
import br.com.digio.androidtest.presentation.ui.theme.dp20
import br.com.digio.androidtest.presentation.ui.theme.dp4
import br.com.digio.androidtest.presentation.ui.theme.dp6
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
internal fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product
) {
    Card(
        modifier = modifier
            .size(dp150)
            .padding(horizontal = dp4),
        shape = RoundedCornerShape(dp20),
        elevation = CardDefaults.cardElevation(defaultElevation = dp6)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.Center,
        ) {
            SubcomposeAsyncImage(
                contentScale = ContentScale.Fit,
                model = product.imageURL,
                contentDescription = stringResource(R.string.image_description_product_item)
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        DigioLoadingComponent()
                    }

                    is AsyncImagePainter.State.Error -> {
                        DigioLogo()
                    }

                    else -> {
                        SubcomposeAsyncImageContent()
                    }
                }
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