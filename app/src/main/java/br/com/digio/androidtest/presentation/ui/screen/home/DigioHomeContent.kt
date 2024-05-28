package br.com.digio.androidtest.presentation.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.digio.androidtest.domain.model.DigioProducts
import br.com.digio.androidtest.presentation.ui.components.DigioLoadingComponent
import br.com.digio.androidtest.presentation.ui.components.HomeWidget
import br.com.digio.androidtest.presentation.ui.theme.AndroidTestTheme
import br.com.digio.androidtest.presentation.utils.Result

@Composable
internal fun DigioHomeContent(
    modifier: Modifier = Modifier,
    state: Result<DigioProducts>
) {
    when (state) {
        is Result.Loading -> DigioLoadingComponent(modifier)
        is Result.Failure -> {

        }

        is Result.Success -> HomeWidget(modifier, state.data)

        else -> Unit
    }
}

@Composable
@Preview
private fun DigioHomeContentPreview() {
    AndroidTestTheme {
        DigioHomeContent(
            modifier = Modifier,
            state = Result.Loading
        )
    }
}