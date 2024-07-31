package jp.craftman1take.composeimpl.composable

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ScaffoldTopAppBar(
    title: String,
    onNavigationClick: () -> Unit = {},
    actions: List<Pair<ImageVector, () -> Unit>> = emptyList(),
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White,
        ),
        actions = {
            actions.forEach { (imageVector, onClick) ->
                IconButton(onClick = onClick) {
                    Icon(imageVector = imageVector, contentDescription = null)
                }
            }
        }
    )
}

@Composable
fun ScaffoldBottomAppBar(
    vararg actions: Pair<ImageVector, () -> Unit>
) {
    BottomAppBar(
        containerColor = Color.LightGray,
        contentColor = Color.Black,
    ) {
        actions.forEach { (imageVector, onClick) ->
            IconButton(onClick = onClick) {
                Icon(imageVector = imageVector, contentDescription = null)
            }
        }
    }
}

@Composable
fun ScaffoldFloatingActionButton(onClick: () -> Unit = {}) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(0xFF009a00),
        contentColor = Color.White,
    ) {
        Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null)
    }
}

@Preview(name = "top app bar", showBackground = true)
@Composable
fun ScaffoldTopAppBarPreview() {
    val context = LocalContext.current

    ScaffoldTopAppBar(
        "This is preview.",
        {},
        listOf(
            Icons.Filled.Add to {
                Toast.makeText(context, "TOP: Add", Toast.LENGTH_SHORT).show()
            },
            Icons.Filled.Share to {
                Toast.makeText(context, "TOP: Share", Toast.LENGTH_SHORT).show()
            },
        )
    )
}

@Preview(name = "bottom app bar", showBackground = true)
@Composable
fun ScaffoldBottomAppBarPreview() {
    val context = LocalContext.current
    ScaffoldBottomAppBar(
        Icons.Filled.Add to {
            Toast.makeText(context, "CLICKED: Add", Toast.LENGTH_SHORT).show()
        },
        Icons.Filled.Edit to {
            Toast.makeText(context, "CLICKED: Edit", Toast.LENGTH_SHORT).show()
        },
        Icons.Filled.Refresh to {
            Toast.makeText(context, "CLICKED: Refresh", Toast.LENGTH_SHORT).show()
        },
    )
}

@Preview(name = "FAB", showBackground = true)
@Composable
fun ScaffoldFloatingActionButtonPreview() {
    ScaffoldFloatingActionButton()
}
