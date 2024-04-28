package com.example.customlayoutdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.customlayoutdemo.ui.theme.CustomLayoutDemoTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomLayoutDemo()
        }
    }
}


@Preview
@Composable
fun ColumnWithTexts() {
    Column {
        Text(
            text = "Android UI development with Jetpack Compose",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Hello Compose",
            style = MaterialTheme.typography.headlineSmall.merge(TextStyle(color = Color.Red))
        )
    }
}

@Preview
@Composable
fun CustomLayoutDemo(){
    SimpleFlexBox {
        for(i in 0..42) {
            ColoredBox()
        }
    }
}

@Composable
fun SimpleFlexBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = simpleFlexboxMeasurePolicy()
    )
}

fun simpleFlexboxMeasurePolicy(): MeasurePolicy =

    MeasurePolicy { measurables, constraints ->
        val placeables = measurables.map { measurable: Measurable ->
            measurable.measure(constraints)
        }
        layout(
            constraints.maxWidth,
            constraints.maxHeight
        ) {
            var yPos = 0
            var xPos = 0
            var maxY = 0
            placeables.forEach { placeable ->
                if (xPos + placeable.width > constraints.maxWidth) {
                    xPos = 0
                    yPos += maxY
                    maxY = 0
                }
                placeable.placeRelative(x = xPos, y = yPos)
                xPos += placeable.width
                if (maxY < placeable.height) {
                    maxY = placeable.height
                }
            }
        }
    }





@Preview
@Composable
fun ColoredBox() {
    Box(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = Color.Black
            )
            .background(randomColor())
            .width((40 * randomInt123()).dp)
            .height((10 * randomInt123()).dp)
    )
}

private fun randomInt123() = Random.nextInt(1, 4)


private fun randomColor() = when (randomInt123()) {
    1 -> Color.Red
    2 -> Color.Green
    else -> Color.Blue
}