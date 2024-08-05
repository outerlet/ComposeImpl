package jp.craftman1take.composeimpl.ui.constraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import jp.craftman1take.composeimpl.data.pictureList
import jp.craftman1take.composeimpl.ui.theme.ComposeImplTheme

data class ImageItem(val id: Int, @DrawableRes val imageResId: Int)

class ListAndButtonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                ComposeImplTheme {
                    MainContent(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.padding(16.dp)) {
        val (listRef, buttonRef) = createRefs()
        val itemList = remember {
            mutableStateOf(
                pictureList.subList(0, 5).mapIndexed { index, picture ->
                    ImageItem(index, picture.resId)
                }
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(listRef) {
                    width = Dimension.matchParent
                    height = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    bottom.linkTo(buttonRef.top, margin = 16.dp)
                }
        ) {
            items(itemList.value) {
                SingleImage(item = it)
            }
        }

        Button(
            modifier = Modifier.constrainAs(buttonRef) {
                width = Dimension.matchParent
                height = Dimension.fillToConstraints
                bottom.linkTo(parent.bottom)
            },
            onClick = {
                itemList.value = itemList.value.reversed()
            }
        ) {
            Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "画像の並びを逆転")
        }
    }
}

@Composable
fun SingleImage(item: ImageItem) {
    Image(
        painter = painterResource(id = item.imageResId),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    MainContent(modifier = Modifier.fillMaxSize())
}