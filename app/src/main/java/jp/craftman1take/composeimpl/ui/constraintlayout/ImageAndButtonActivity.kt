package jp.craftman1take.composeimpl.ui.constraintlayout

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import jp.craftman1take.composeimpl.R
import jp.craftman1take.composeimpl.ui.theme.ComposeImplTheme

class ImageAndButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeImplTheme {
                Surface {
                    MainContent(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

    @Composable
    fun MainContent(modifier: Modifier = Modifier) {
        val constraintSet = ConstraintSet {
            val imageRef = createRefFor("image")
            val buttonRef = createRefFor("button")

            constrain(imageRef) {
                width = Dimension.matchParent
                height = Dimension.fillToConstraints
                top.linkTo(parent.top)
                bottom.linkTo(buttonRef.top, margin = 8.dp)
            }

            constrain(buttonRef) {
                width = Dimension.fillToConstraints
                height = Dimension.wrapContent
                start.linkTo(imageRef.start)
                end.linkTo(imageRef.end)
                bottom.linkTo(parent.bottom)
            }
        }

        ConstraintLayout(
            modifier = modifier.padding(16.dp),
            constraintSet = constraintSet,
        ) {
            Image(
                modifier = Modifier.layoutId("image"),
                painter = painterResource(id = R.drawable.picture_11),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Button(
                modifier = Modifier.layoutId("button"),
                onClick = {},
            ) {
                Text(text = "ボタン")
            }
        }
    }

    @Preview
    @Composable
    fun MainContentPreview() {
        Surface {
            MainContent(modifier = Modifier.fillMaxSize())
        }
    }
}

