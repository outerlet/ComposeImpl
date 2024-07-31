package jp.craftman1take.composeimpl.ui.contentscale

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import jp.craftman1take.composeimpl.R
import jp.craftman1take.composeimpl.composable.SimpleButton
import jp.craftman1take.composeimpl.composable.TextButton
import jp.craftman1take.composeimpl.data.Picture
import jp.craftman1take.composeimpl.ui.theme.ComposeImplTheme

class ContentScaleActivity : AppCompatActivity() {
    // 確認用なので Pair で実装
    private val contentScaleList = listOf(
        "Fit" to ContentScale.Fit,
        "Inside" to ContentScale.Inside,
        "Crop" to ContentScale.Crop,
        "FillWidth" to ContentScale.FillWidth,
        "FillHeight" to ContentScale.FillHeight,
        "FillBounds" to ContentScale.FillBounds,
        "None" to ContentScale.None,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeImplTheme {
                Surface {
                    ComposeContent()
                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun ComposeContent() {
        val contentScale = remember {
            mutableStateOf("Fit" to ContentScale.Fit)
        }
        val scaffoldState = rememberBottomSheetScaffoldState()

        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = 96.dp,
            sheetContainerColor = Color(0xFFFAE6D7),
            sheetTonalElevation = 8.dp,
            sheetContent = {
                contentScaleList.forEach { (title, scale) ->
                    SideSpacedTextButton(
                        label = title,
                    ) { contentScale.value = title to scale }
                }
            },
        ) {
            ComparePictureContent(
                contentScaleName = contentScale.value.first,
                contentScale = contentScale.value.second,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxSize()
            )
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun ComposeContentPreview() {
        ComposeContent()
    }
}

@Composable
fun SideSpacedTextButton(label: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier.padding(
            top = 8.dp,
            bottom = 8.dp,
            start = 12.dp,
            end = 12.dp,
        )
    ) {
        TextButton(
            label = label,
            modifier = Modifier.fillMaxWidth(),
            onClick = onClick,
        )
    }
}

@Composable
fun ComparePictureContent(
    contentScaleName: String,
    contentScale: ContentScale,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(36.dp),
        modifier = modifier,
    ) {
        PictureComposable(
            title = "Confirm: $contentScaleName",
            picture = Picture(0, null, R.drawable.picture_01_tmb),
            modifier = Modifier.layoutId("confirmation"),
            contentScale = contentScale,
        )

        PictureComposable(
            title = "Original (Fit)",
            picture = Picture(0, null, R.drawable.picture_01_tmb),
            modifier = Modifier.layoutId("original"),
            contentScale = ContentScale.Fit,
        )
    }
}


@Composable
fun PictureComposable(
    title: String,
    picture: Picture,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Start,
            modifier = modifier,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = picture.resId),
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(color = Color.LightGray),
        )
    }
}
