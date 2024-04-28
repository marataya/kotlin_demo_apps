package com.example.modifierdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.modifierdemo.ui.theme.ModifierDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModifierDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OrderDemo()
                }
            }
        }
    }
}

@Composable
fun OrderDemo() {
    var color by remember {
        mutableStateOf(Color.Blue)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                color = if (color == Color.Blue)
                    Color.Red
                else
                    Color.Blue
            }
            .padding(32.dp)
            .border(BorderStroke(width = 2.dp, color = color))
            .background(color = Color.LightGray)
            .drawYellowCross()

    ) {

    }
}

@Preview
@Composable
fun runOrderDemo() {
    OrderDemo()
}

fun Modifier.drawYellowCross() = then(
    
    object: DrawModifier {
        override fun ContentDrawScope.draw() {
            drawLine(
                color = Color.Yellow,
                start = Offset(0f, 0f),
                end = Offset(size.width - 1, size.height - 1),
                strokeWidth = 10f
            )
            drawLine(
                color = Color.Yellow,
                start = Offset(0f, size.height - 1),
                end = Offset(size.width - 1, 0f),
                strokeWidth = 10f
            )
            drawContent()
        }
    }
)