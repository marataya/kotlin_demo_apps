package com.example.colorpickerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize(),
//                    .wrapContentWidth(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.width(min(400.dp, maxWidth)).padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val color = remember { mutableStateOf(Color.Magenta) }

                    ColorPicker(color)

                    Text(
                        text = "#${color.value.toArgb().toUInt().toString(16)}",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge.merge(
                            TextStyle(
                                color = color.value
                            )
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(color.value)
                    )
                }
            }
        }
    }
}


@Composable
fun ColorPicker(color: MutableState<Color>) {
    val red: Float = color.value.red
    val green: Float = color.value.green
    val blue: Float = color.value.blue
    Column(modifier = Modifier) {
        Slider(
            value = red,
            onValueChange = { color.value = Color(it, green, blue) }
        )
        Slider(
            value = green,
            onValueChange = { color.value = Color(red, it, blue) }
        )
        Slider(
            value = blue,
            onValueChange = { color.value = Color(red, green, it) }
        )
    }
}
