package jp.craftman1take.composeimpl.ui.modalbottomsheet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import jp.craftman1take.composeimpl.R
import jp.craftman1take.composeimpl.data.Picture
import jp.craftman1take.composeimpl.ui.theme.ComposeImplTheme

class ModalBottomSheetActivity : AppCompatActivity() {
    private val imageList = listOf(
        "紅葉" to R.drawable.picture_01,
        "因幡の白兎" to R.drawable.picture_02,
        "桜" to R.drawable.picture_03,
        "秋の寺社" to R.drawable.picture_04,
        "夏の吊り橋" to R.drawable.picture_05,
        "春のツツジ" to R.drawable.picture_06,
        "山の案内熊" to R.drawable.picture_07,
        "山のトトロ" to R.drawable.picture_08,
        "秘境駅" to R.drawable.picture_09,
        "秋のススキ" to R.drawable.picture_10,
    )

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
            val targetImage = imageList[currentIndex.intValue]

            Text(
                text = targetImage.first,
                fontSize = 24.sp,
                modifier = Modifier.constrainAs(textRef) {
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                    start.linkTo(imageRef.start)
                    bottom.linkTo(imageRef.top, margin = 12.dp)
                }
            )

            Image(
                painter = painterResource(id = targetImage.second),
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
                    itemsIndexed(imageList, key = { _, item -> item }) { index, (name, _) ->
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { currentIndex.intValue = index },
                        ) {
                            Text(text = name)
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