package jp.craftman1take.composeimpl.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.craftman1take.composeimpl.R
import jp.craftman1take.composeimpl.composable.SimpleButton
import jp.craftman1take.composeimpl.ui.composebyfragment.ComposeByFragmentActivity
import jp.craftman1take.composeimpl.ui.constraintlayout.ImageAndButtonActivity
import jp.craftman1take.composeimpl.ui.constraintlayout.ListAndButtonActivity
import jp.craftman1take.composeimpl.ui.contentscale.ContentScaleActivity
import jp.craftman1take.composeimpl.ui.modalbottomsheet.ModalBottomSheetActivity
import jp.craftman1take.composeimpl.ui.pager.PagerActivity
import jp.craftman1take.composeimpl.ui.scaffold.ScaffoldActivity
import jp.craftman1take.composeimpl.ui.theme.ComposeImplTheme

class MainActivity : ComponentActivity() {
    private enum class MainButton(
        val imageVector: ImageVector,
        val labelResId: Int,
        val cls: Class<*>
    ) {
        LIST_AND_BUTTON(
            Icons.Filled.Edit, R.string.label_list_and_button, ListAndButtonActivity::class.java,
        ),
        IMAGE_AND_BUTTON(
            Icons.Filled.LocationOn, R.string.label_image_and_button, ImageAndButtonActivity::class.java,
        ),
        CONTENT_SCALE(
            Icons.Filled.Check, R.string.label_content_scale, ContentScaleActivity::class.java,
        ),
        FRAGMENT_COMPOSE(
            Icons.Filled.Create, R.string.label_fragment_compose, ComposeByFragmentActivity::class.java,
        ),
        MODAL_BOTTOM_SHEET(
            Icons.Filled.Info, R.string.label_modal_bottom_sheet, ModalBottomSheetActivity::class.java,
        ),
        HORIZONTAL_PAGER(
            Icons.Filled.Refresh, R.string.label_horizontal_pager, PagerActivity::class.java,
        ),
        TRY_SCAFFOLD(
            Icons.Filled.Build, R.string.label_try_scaffold, ScaffoldActivity::class.java,
        ),
    }

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
    fun ComposeContent() {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                textAlign = TextAlign.Start,
                text = stringResource(R.string.message_icon_convenience),
            )

            MainButton.entries.forEach {
                SimpleButton(
                    imageVector = it.imageVector,
                    label = stringResource(id = it.labelResId),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    startActivity(Intent(this@MainActivity, it.cls))
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ComposeContentPreview() {
        Surface {
            ComposeContent()
        }
    }

    @Preview(name = "Night-Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    fun ComposeContentPreviewNightMode() {
        Surface {
            ComposeContent()
        }
    }
}
