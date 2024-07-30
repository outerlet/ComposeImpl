package jp.craftman1take.composeimpl.ui.composebyfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import jp.craftman1take.composeimpl.R
import kotlinx.coroutines.launch

enum class Selection(val imageResId: Int) {
    Maid(R.drawable.picture_01),
    Dancer(R.drawable.picture_02),
    Miku(R.drawable.picture_03),
}

class ComposeByFragmentFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        content {
            CustomLayout()
        }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun CustomLayout() {
        val coroutineScope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        val selection = remember { mutableStateOf(Selection.Miku) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "My Scaffold")
                    },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Share, contentDescription = null)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Blue,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White,
                        navigationIconContentColor = Color.White,
                    )
                )
            },
            bottomBar = {
                BottomAppBar {
                    IconButton(onClick = { selection.value = Selection.Maid }) {
                        Icon(imageVector = Icons.Filled.Star, contentDescription = null)
                    }

                    IconButton(onClick = { selection.value = Selection.Dancer }) {
                        Icon(imageVector = Icons.Filled.Check, contentDescription = null)
                    }

                    IconButton(onClick = { selection.value = Selection.Miku }) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
                    }
                }
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            floatingActionButton = {
                FloatingActionButton(
                    shape = FloatingActionButtonDefaults.shape,
                    containerColor = FloatingActionButtonDefaults.containerColor,
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("This is simple Snackbar")
                        }
                    },
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }
        ) { padding ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                key(selection.value) {
                    Image(
                        painter = painterResource(id = selection.value.imageResId),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
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