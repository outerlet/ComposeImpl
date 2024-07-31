package jp.craftman1take.composeimpl.ui.composebyfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import jp.craftman1take.composeimpl.R

enum class Selection(
    @DrawableRes val resId: Int,
    val segmentTitle: String,
) {
    PICTURE_1(R.drawable.picture_01, "Leaves"),
    PICTURE_2(R.drawable.picture_02, "Rabbit"),
    PICTURE_3(R.drawable.picture_03, "Blossoms"),
}

class ComposeByFragmentFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        content {
            CustomLayout()
        }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun CustomLayout() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Compose on Fragment") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Blue,
                        titleContentColor = Color.White,
                    )
                )
            },
        ) { paddingValues ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding() + 16.dp,
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 16.dp,
                        end = paddingValues.calculateStartPadding(LayoutDirection.Rtl) + 16.dp,
                    )
            ) {
                val selectedIndex = remember { mutableIntStateOf(0) }
                val (imageRef, segmentRef) = createRefs()

                Image(
                    painter = painterResource(id = Selection.entries[selectedIndex.intValue].resId),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize().constrainAs(imageRef) {
                        width = Dimension.matchParent
                        height = Dimension.wrapContent
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                        bottom.linkTo(segmentRef.top)
                    },
                )

                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier.constrainAs(segmentRef) {
                        width = Dimension.matchParent
                        height = Dimension.wrapContent
                        bottom.linkTo(parent.bottom)
                    }
                ) {
                    Selection.entries.forEachIndexed { index, selection ->
                        SegmentedButton(
                            selected = index == selectedIndex.intValue,
                            shape = SegmentedButtonDefaults.itemShape(
                                index = index,
                                count = Selection.entries.size,
                            ),
                            onClick = { selectedIndex.intValue = index },
                        ) {
                            Text(text = selection.segmentTitle)
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CustomLayoutPreview() {
        CustomLayout()
    }
}