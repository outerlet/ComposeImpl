package jp.craftman1take.composeimpl.ui.contentscale

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import jp.craftman1take.composeimpl.data.Picture
import jp.craftman1take.composeimpl.ui.theme.ComposeImplTheme

class ContentScaleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                ComposeImplTheme {
                    ComposeContent()
                }
            }
        }
    }

    @Composable
    fun ComposeContent(modifier: Modifier = Modifier) {
        val constraintSet = ConstraintSet {
            val originalLabelRef = createRefFor("originalLabel")
            val originalImageRef = createRefFor("originalImage")
            val confirmLabelRef = createRefFor("confirmLabel")
            val confirmImageRef = createRefFor("confirmImage")

            val guideline = createGuidelineFromTop(0.5f)

            constrain(originalLabelRef) {
                width = Dimension.matchParent
                height = Dimension.wrapContent
                top.linkTo(parent.top, margin = 12.dp)
            }

            constrain(originalImageRef) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(originalLabelRef.bottom, margin = 8.dp)
                bottom.linkTo(guideline)
                start.linkTo(originalLabelRef.start)
                end.linkTo(originalLabelRef.end)
            }

            constrain(confirmLabelRef) {
                width = Dimension.fillToConstraints
                height = Dimension.wrapContent
                top.linkTo(guideline, margin = 12.dp)
                start.linkTo(originalLabelRef.start)
                end.linkTo(originalLabelRef.end)
            }

            constrain(confirmImageRef) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(confirmLabelRef.bottom, margin = 8.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(originalLabelRef.start)
                end.linkTo(originalLabelRef.end)
            }
        }

        ConstraintLayout(
            constraintSet = constraintSet,
            modifier = modifier
                .padding(start = 12.dp, end = 12.dp)
                .fillMaxSize(),
        ) {
            val contentScale: State<ContentScale> = remember { mutableStateOf(ContentScale.Fit) }

            PictureLabelComposable(
                title = "Original (Fit)",
                modifier = Modifier.layoutId("originalLabel"),
            )

            PictureComposable(
                Picture(0, null, R.drawable.picture_01),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .layoutId("originalImage")
                    .background(color = Color.Gray),
            )

            PictureLabelComposable(
                title = "Confirm",
                modifier = Modifier.layoutId("confirmLabel"),
            )

            PictureComposable(
                Picture(0, null, R.drawable.picture_01),
                contentScale = contentScale.value,
                modifier = Modifier
                    .layoutId("confirmImage")
                    .background(color = Color.Gray),
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
fun PictureLabelComposable(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 24.sp,
        textAlign = TextAlign.Start,
        modifier = modifier,
    )
}

@Composable
fun PictureComposable(picture: Picture, contentScale: ContentScale = ContentScale.Fit, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = picture.resId),
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier,
    )
}
