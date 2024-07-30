package jp.craftman1take.composeimpl.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.craftman1take.composeimpl.data.Picture
import jp.craftman1take.composeimpl.data.thumbnailPictures

enum class PictureListOrder {
    FORWARD,
    REVERSE;

    fun another() = if (this == FORWARD) REVERSE else FORWARD
}

@Composable
fun PictureList(
    pictureList: List<Picture>,
    orientation: Orientation,
    listState: LazyListState = rememberLazyListState(),
    listModifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    orderState: State<PictureListOrder> = mutableStateOf(PictureListOrder.FORWARD),
) {
    val contents: LazyListScope.() -> Unit = {
        items(
            items = if (orderState.value == PictureListOrder.FORWARD) pictureList else pictureList.reversed(),
            key = { it.id },
        ) {
            Image(
                painter = painterResource(id = it.resId),
                contentDescription = null,
                contentScale = ContentScale.Inside,
            )
        }
    }

    if (orientation == Orientation.Vertical) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            content = contents,
            state = listState,
            modifier = listModifier,
        )
    } else {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            content = contents,
            state = listState,
            modifier = listModifier,
        )
    }
}

@Preview(name = "vertical", showBackground = true)
@Composable
fun PictureColumnPreview() {
    PictureList(
        pictureList = thumbnailPictures,
        orientation = Orientation.Vertical,
    )
}

@Preview(name = "horizontal", showBackground = true)
@Composable
fun PictureRowPreview() {
    PictureList(
        pictureList = thumbnailPictures,
        orientation = Orientation.Horizontal,
    )
}
