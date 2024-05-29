package br.com.digio.androidtest.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.digio.androidtest.R
import br.com.digio.androidtest.domain.model.Spotlight
import br.com.digio.androidtest.presentation.ui.theme.AndroidTestTheme
import br.com.digio.androidtest.presentation.ui.theme.dp20
import br.com.digio.androidtest.presentation.ui.theme.dp24
import br.com.digio.androidtest.presentation.ui.theme.dp4
import br.com.digio.androidtest.presentation.ui.theme.dp400
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
internal fun SpotlightBanner(
    modifier: Modifier = Modifier,
    spotlight: Spotlight
) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(start = dp4, end = dp4, top = dp24),
        shape = RoundedCornerShape(dp20),
        elevation = CardDefaults.cardElevation(defaultElevation = dp4)
    ) {
        SubcomposeAsyncImage(
            contentScale = ContentScale.Crop,
            modifier = modifier
                .width(dp400)
                .wrapContentHeight(),
            model = spotlight.bannerURL,
            contentDescription = stringResource(R.string.image_description_spotlight_item)
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

@Composable
@Preview
private fun SpotlightItemPreview() {
    AndroidTestTheme {
        val spotlight = Spotlight(
            name = "Recarga",
            bannerURL = "https://s3-sa-east-1.amazonaws.com/digio-exame/recharge_banner.png",
            description = "Agora ficou mais fácil colocar créditos no seu celular! A digio Store traz a facilidade de fazer recargas..."
        )

        SpotlightBanner(spotlight = spotlight)
    }
}