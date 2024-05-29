package br.com.digio.androidtest.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.digio.androidtest.R
import br.com.digio.androidtest.domain.model.Cash
import br.com.digio.androidtest.presentation.ui.theme.AndroidTestTheme
import br.com.digio.androidtest.presentation.ui.theme.dp120
import br.com.digio.androidtest.presentation.ui.theme.dp20
import br.com.digio.androidtest.presentation.ui.theme.dp24
import br.com.digio.androidtest.presentation.ui.theme.dp4
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
internal fun DigioCashBanner(
    modifier: Modifier = Modifier,
    cash: Cash
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(start = dp4, end = dp4, bottom = dp24)
    ) {
        SubcomposeAsyncImage(
            modifier = modifier
                .fillMaxSize()
                .height(dp120)
                .clip(RoundedCornerShape(dp20)),
            model = cash.bannerURL,
            contentScale = ContentScale.FillHeight,
            contentDescription = stringResource(R.string.image_description_cash_item)
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
private fun DigioCashBannerPreview() {
    AndroidTestTheme {
        val cash = Cash(
            title = "digio Cash",
            bannerURL = "https://s3-sa-east-1.amazonaws.com/digio-exame/cash_banner.png",
            description = "Dinheiro na conta sem complicação. Transfira parte do limite do seu cartão para sua conta."
        )
        DigioCashBanner(cash = cash)
    }
}