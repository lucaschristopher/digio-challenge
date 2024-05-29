package br.com.digio.androidtest.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import br.com.digio.androidtest.R
import br.com.digio.androidtest.domain.model.Cash
import br.com.digio.androidtest.presentation.ui.theme.DigioFont
import br.com.digio.androidtest.presentation.ui.theme.dp10
import br.com.digio.androidtest.presentation.ui.theme.dp20
import br.com.digio.androidtest.presentation.ui.theme.dp4

@Composable
internal fun CashBannerComponent(
    modifier: Modifier = Modifier,
    cash: Cash
) {
    Column {
        MultiStyleText(
            modifier = modifier.padding(top = dp20, bottom = dp10, start = dp4, end = dp4),
            first = stringResource(id = R.string.digio),
            firstColor = colorResource(id = R.color.dark_blue_grey_three),
            second = stringResource(id = R.string.cache),
            secondColor = colorResource(id = R.color.font_color_digio_cash),
        )
        DigioCashBanner(cash = cash)
    }
}

@Composable
private fun MultiStyleText(
    modifier: Modifier,
    first: String,
    firstColor: Color,
    second: String,
    secondColor: Color
) {
    Text(
        modifier = modifier,
        fontWeight = FontWeight.Bold,
        color = colorResource(id = R.color.dark_blue_grey_three),
        fontFamily = DigioFont,
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = firstColor)) {
                append(first)
            }
            append(EMPTY_CHAR)
            withStyle(style = SpanStyle(color = secondColor)) {
                append(second)
            }
        }
    )
}

private const val EMPTY_CHAR = " "