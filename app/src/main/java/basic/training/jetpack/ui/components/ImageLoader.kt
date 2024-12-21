package basic.training.jetpack.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import basic.training.jetpack.R
import coil.compose.SubcomposeAsyncImage

@Composable
fun ImageLoader(modifier: Modifier = Modifier, imageUrl : String) {

    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = "Valkyrie Image From  URL",
        modifier = modifier,
        contentScale = ContentScale.Crop,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        error = {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Error Loading Image",
                modifier = Modifier.fillMaxSize()
            )
        }
    )

/**
 * simple loader image
 */
//    AsyncImage(
//        model = ImageRequest.Builder(LocalContext.current)
//            .data(imageUrl)
//            .crossfade(true)
//            .build(),
//        contentDescription = "Valkyrie Image From  URL",
//        modifier = Modifier.size(300.dp),
//        contentScale = ContentScale.Crop,
//        placeholder = painterResource(R.drawable.ic_launcher_foreground),
//        error = painterResource(R.drawable.ic_launcher_foreground)
//    )
}