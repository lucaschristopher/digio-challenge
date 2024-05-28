package br.com.digio.androidtest.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.com.digio.androidtest.R
import br.com.digio.androidtest.presentation.ui.theme.AndroidTestTheme
import br.com.digio.androidtest.presentation.ui.theme.dp10
import br.com.digio.androidtest.presentation.ui.theme.dp40
import br.com.digio.androidtest.presentation.ui.theme.dp8

@Composable
internal fun HeaderComponent(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(dp10)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_icon_round),
            contentDescription = stringResource(R.string.image_description_contact_profile_picture),
            modifier = modifier
                .size(dp40)
                .clip(CircleShape)
        )
        Text(
            modifier = modifier.padding(start = dp8),
            text = stringResource(id = R.string.hello_maria),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
private fun HeaderComponentPreview() {
    AndroidTestTheme {
        HeaderComponent()
    }
}
