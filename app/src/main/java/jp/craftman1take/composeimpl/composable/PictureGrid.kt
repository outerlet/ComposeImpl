package jp.craftman1take.composeimpl.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import jp.craftman1take.composeimpl.data.Picture
import jp.craftman1take.composeimpl.data.thumbnailPictures

@Composable
fun PictureGrid(
    pictureList: List<Picture>,
    orientation: Orientation,
    countOfLine: Int,
) {
    val gridCells = GridCells.Fixed(count = countOfLine)
    val contentScale = ContentScale.Inside

    if (orientation == Orientation.Vertical) {
        LazyVerticalGrid(columns = gridCells) {
            items(pictureList) {
                Image(
                    painter = painterResource(id = it.resId),
                    contentDescription = null,
                    contentScale = contentScale,
                )
            }
        }
    } else {
        LazyHorizontalGrid(rows = gridCells) {
            items(pictureList) {
                Image(
                    painter = painterResource(id = it.resId),
                    contentDescription = null,
                    contentScale = contentScale,
                )
            }
        }
    }
}

@Preview(name = "vertical", showBackground = true)
@Composable
fun VerticalPictureGridPreview() {
    PictureGrid(
        pictureList = thumbnailPictures,
        orientation = Orientation.Vertical,
        countOfLine = 3,
    )
}

@Preview(name = "horizontal", showBackground = true)
@Composable
fun HorizontalPictureGridPreview() {
    PictureGrid(
        pictureList = thumbnailPictures,
        orientation = Orientation.Horizontal,
        countOfLine = 3,
    )
}
