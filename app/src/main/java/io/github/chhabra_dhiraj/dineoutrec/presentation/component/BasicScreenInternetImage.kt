package io.github.chhabra_dhiraj.dineoutrec.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import io.github.chhabra_dhiraj.dineoutrec.R
import io.github.chhabra_dhiraj.dineoutrec.presentation.ui.theme.DineoutRecTheme

@Composable
fun BasicScreenInternetImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentScale: ContentScale = ContentScale.Fit
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentDescription = null,
        loading = {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.scale(0.3f)
            )
        },
        error = {
            Image(
                imageVector = ImageVector.vectorResource(
                    id = R.drawable.baseline_fastfood_24
                ),
                contentDescription = null
            )
        },
        contentScale = contentScale
    )
}

@Preview
@Composable
private fun BasicScreenInternetImage_Preview() {
    DineoutRecTheme {
        BasicScreenInternetImage(
            imageUrl = "https://imageproxy.wolt.com/assets/673208c8479a971266a698b3"
        )
    }
}