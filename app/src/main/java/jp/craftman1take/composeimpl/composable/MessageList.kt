package jp.craftman1take.composeimpl.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

data class Message(val id: Int, val body: String)

@Composable
fun MessageList() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val listRef = createRef()
        val messages = remember {
            (1..20).map { Message(id = it, body = "Message No. $it") }
        }

        Column(
            modifier = Modifier
                .background(color = Color.LightGray)
                .constrainAs(listRef) {
                    width = Dimension.fillToConstraints
                }
                .verticalScroll(state = rememberScrollState())
        ) {
            messages.forEach {
                key(it.id) {
                    Text(
                        text = it.body,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .padding(top = 24.dp, bottom = 24.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = Color.Black)
                    )
                }
            }
        }
    }
}

