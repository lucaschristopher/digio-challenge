package br.com.digio.androidtest.presentation.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.digio.androidtest.presentation.viewmodel.DigioViewModel

@Composable
internal fun DigioHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: DigioViewModel = hiltViewModel()
) {
    val state = viewModel.products.collectAsStateWithLifecycle().value

    Scaffold(modifier = modifier.fillMaxSize()) {
        DigioHomeContent(modifier = modifier.padding(it), state = state)
    }
}