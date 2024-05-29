package br.com.digio.androidtest.presentation.ui.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import br.com.digio.androidtest.domain.model.Spotlight

@Composable
internal fun SpotlightCarousel(data: List<Spotlight>) {
    LazyRow {
        items(data) { item ->
            SpotlightBanner(spotlight = item)
        }
    }
}
