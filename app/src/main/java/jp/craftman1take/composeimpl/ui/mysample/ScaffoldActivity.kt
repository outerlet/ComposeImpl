package jp.craftman1take.composeimpl.ui.mysample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.craftman1take.composeimpl.composable.ScaffoldBottomAppBar
import jp.craftman1take.composeimpl.composable.ScaffoldFloatingActionButton
import jp.craftman1take.composeimpl.composable.ScaffoldTopAppBar
import jp.craftman1take.composeimpl.data.pictures
import jp.craftman1take.composeimpl.ui.theme.ComposeImplTheme
import kotlinx.coroutines.launch

class ScaffoldActivity : ComponentActivity() {
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
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                ScaffoldTopAppBar(
                    "Top App Bar",
                    {
                        Toast.makeText(context, "Navigation", Toast.LENGTH_SHORT).show()
                    },
                    listOf(
                        Icons.Filled.Add to {
                            Toast.makeText(context, "TOP: Add", Toast.LENGTH_SHORT).show()
                        },
                        Icons.Filled.Share to {
                            Toast.makeText(context, "TOP: Share", Toast.LENGTH_SHORT).show()
                        },
                    ),
                )
            },
            bottomBar = {
                ScaffoldBottomAppBar(
                    Icons.Filled.Add to {
                        Toast.makeText(context, "BOTTOM: Add", Toast.LENGTH_SHORT).show()
                    },
                    Icons.Filled.Edit to {
                        Toast.makeText(context, "BOTTOM: Edit", Toast.LENGTH_SHORT).show()
                    },
                    Icons.Filled.Refresh to {
                        Toast.makeText(context, "BOTTOM: Refresh", Toast.LENGTH_SHORT).show()
                    },

                    )
            },
            floatingActionButton = {
                ScaffoldFloatingActionButton(
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(message = "This is simple snackbar.")
                        }
                    }
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(8.dp)) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                ) {
                    items(
                        items = pictures,
                        key = { p -> p.id },
                    ) { picture ->
                        Image(
                            painter = painterResource(id = picture.resId),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ComposeContentPreview() {
        ComposeContent()
    }
}