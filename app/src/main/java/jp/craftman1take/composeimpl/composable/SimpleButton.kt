package jp.craftman1take.composeimpl.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SimpleButton(
    imageVector: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.Blue,
            contentColor = Color.White,
            disabledContentColor = Color.LightGray,
        ),
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize),
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = label)
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleButtonPreview() {
    Box(modifier = Modifier.fillMaxWidth()) {
        SimpleButton(
            imageVector = Icons.Filled.Refresh,
            label = "Sample of SimpleButton",
            modifier = Modifier.fillMaxWidth(),
        )
    }
}