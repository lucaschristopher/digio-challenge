package br.com.digio.androidtest.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.digio.androidtest.R
import br.com.digio.androidtest.domain.model.Cash
import br.com.digio.androidtest.presentation.ui.theme.AndroidTestTheme
import br.com.digio.androidtest.presentation.ui.theme.dp4
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
internal fun DigioCashBanner(
    modifier: Modifier = Modifier,
    cash: Cash
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = dp4
        ),
        modifier = modifier.fillMaxSize()
    ) {
        SubcomposeAsyncImage(
            model = cash.bannerURL,
            contentDescription = stringResource(R.string.image_description_cash_item)
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