package jp.craftman1take.composeimpl.ui.modalbottomsheet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import jp.craftman1take.composeimpl.data.pictureList
import jp.craftman1take.composeimpl.ui.theme.ComposeImplTheme

class ModalBottomSheetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeImplTheme {
                Surface {
                    ComposeContent(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun ComposeContent(modifier: Modifier = Modifier) {
        val openBottomSheet = remember { mutableStateOf(false) }
        val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        val currentIndex = remember { mutableIntStateOf(0) }

        ConstraintLayout(modifier = modifier.padding(16.dp)) {
            val (textRef, imageRef, buttonRef) = createRefs()
            val targetImage = pictureList[currentIndex.intValue]

            Text(
                text = targetImage.title,
                fontSize = 24.sp,
                modifier = Modifier.constrainAs(textRef) {
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                    start.linkTo(imageRef.start)
                    bottom.linkTo(imageRef.top, margin = 12.dp)
                }
            )

            Image(
                painter = painterResource(id = targetImage.resId),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.constrainAs(imageRef) {
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                    top.linkTo(parent.top)
                    bottom.linkTo(buttonRef.top)
                }
            )

            Button(
                modifier = Modifier.constrainAs(buttonRef) {
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                    bottom.linkTo(parent.bottom)
                },
                onClick = { openBottomSheet.value = true },
            ) {
                Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Open Bottom Sheet")
            }
        }

        if (openBottomSheet.value) {
            ModalBottomSheet(
                onDismissRequest = { openBottomSheet.value = !openBottomSheet.value },
                sheetState = bottomSheetState,
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp),
                ) {
                    itemsIndexed(pictureList, key = { _, item -> item.id }) { index, item ->
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { currentIndex.intValue = index },
                        ) {
                            Text(text = item.title)
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ComposeContentPreview() {
        ComposeContent(Modifier.fillMaxSize())
    }
}