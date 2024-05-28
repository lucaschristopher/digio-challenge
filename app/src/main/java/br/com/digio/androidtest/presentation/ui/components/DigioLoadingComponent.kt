package br.com.digio.androidtest.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.digio.androidtest.R

@Composable
internal fun DigioLoadingComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = colorResource(id = R.color.blue_darker))
    }
}

@Composable
@Preview
private fun DigioLoadingComponentPreview() = DigioLoadingComponent()
