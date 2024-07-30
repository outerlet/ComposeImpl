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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.craftman1take.composeimpl.composable.SimpleButton
import jp.craftman1take.composeimpl.ui.composebyfragment.ComposeByFragmentActivity
import jp.craftman1take.composeimpl.ui.constraintlayout.ConstraintLayoutActivity
import jp.craftman1take.composeimpl.ui.contentscale.ContentScaleActivity
import jp.craftman1take.composeimpl.ui.scaffold.ScaffoldActivity
import jp.craftman1take.composeimpl.ui.theme.ComposeImplTheme

class MainActivity : ComponentActivity() {
    private enum class MainButton(
        val imageVector: ImageVector,
        val label: String,
        val cls: Class<*>
    ) {
        SIMPLE_CONSTRAINTLAYOUT(Icons.Filled.Edit, "シンプルな ConstraintLayout", ConstraintLayoutActivity::class.java),
        CONTENT_SCALE_VARIATION(Icons.Filled.Check, "ContentScale のバリエーションと効果を確認", ContentScaleActivity::class.java),
        COMPOSE_BY_FRAGMENT(Icons.Filled.Create, "Fragment で Compose を使ってみた", ComposeByFragmentActivity::class.java),
        USE_SCAFFOLD(Icons.Filled.Refresh, "Scaffold で色々やってみた", ScaffoldActivity::class.java)
    }

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
    fun ComposeContent() {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            MainButton.entries.forEach {
                SimpleButton(
                    imageVector = it.imageVector,
                    label = it.label,
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
        ComposeContent()
    }

    @Preview(name = "Night-Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    fun ComposeContentPreviewNightMode() {
        ComposeContent()
    }
}
