package jp.craftman1take.composeimpl.ui.pager

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import jp.craftman1take.composeimpl.data.pictureList
import jp.craftman1take.composeimpl.ui.theme.ComposeImplTheme

class PagerActivity : AppCompatActivity() {
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
    @OptIn(ExperimentalFoundationApi::class)
    fun ComposeContent(modifier: Modifier = Modifier) {
        // pageCount だけでもよい（理解のため initialPage をデフォルト値で設定している）
        val pagerState = rememberPagerState(initialPage = 0, pageCount = { pictureList.size })

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) { page ->
            ConstraintLayout(modifier = modifier.fillMaxSize().padding(12.dp)) {
                val (imageRef, textRef) = createRefs()

                Image(
                    painter = painterResource(id = pictureList[page].resId),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.constrainAs(imageRef) {
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                        centerTo(parent)
                    },
                )

                Text(
                    text = pictureList[page].title,
                    fontSize = 24.sp,
                    modifier = Modifier.constrainAs(textRef) {
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                        top.linkTo(imageRef.bottom)
                        bottom.linkTo(parent.bottom)
                        centerHorizontallyTo(parent)
                    }
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ComposeContentPreview() {
        ComposeContent(modifier = Modifier.fillMaxSize())
    }
}