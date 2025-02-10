package io.github.chhabra_dhiraj.dineoutrec.presentation.component

import androidx.annotation.DrawableRes
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

// TODO: Rename to a better name
@Composable
fun BasicScreenInternetImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    @DrawableRes errorDrawableResId: Int = R.drawable.baseline_broken_image_24,
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
                    id = errorDrawableResId
                ),
                contentDescription = null // TODO: add this
            )
        },
        contentScale = contentScale
    )
}

@Preview(showBackground = true)
@Composable
private fun BasicScreenInternetImage_Preview() {
    DineoutRecTheme {
        BasicScreenInternetImage(
            imageUrl = "https://imageproxy.wolt.com/assets/673208c8479a971266a698b3"
        )
    }
}