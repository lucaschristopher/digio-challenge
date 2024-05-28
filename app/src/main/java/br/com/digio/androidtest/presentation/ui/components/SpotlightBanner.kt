package br.com.digio.androidtest.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.digio.androidtest.R
import br.com.digio.androidtest.domain.model.Spotlight
import br.com.digio.androidtest.presentation.ui.theme.AndroidTestTheme
import br.com.digio.androidtest.presentation.ui.theme.dp4
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
internal fun SpotlightBanner(
    modifier: Modifier = Modifier,
    spotlight: Spotlight
) {
    ElevatedCard(
        modifier = modifier.fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = dp4)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(spotlight.bannerURL)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_alert_circle),
            contentDescription = stringResource(R.string.image_description_spotlight_item),
        )
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