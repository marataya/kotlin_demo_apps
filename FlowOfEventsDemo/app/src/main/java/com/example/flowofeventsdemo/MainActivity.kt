package com.example.flowofeventsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flowofeventsdemo.ui.theme.FlowOfEventsDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowOfEventsDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FlowOfEventsDemo()
                }
            }
        }
    }
}
@Preview
@Composable
fun FlowOfEventsDemo() {
    val strCelsius = stringResource(id = R.string.celsius)
    val strFahrenheit = stringResource(id = R.string.fahrenheit)
    var temperature by remember {
        mutableStateOf("")
    }
    var scale by remember {
        mutableStateOf(R.string.celsius)
    }
    var convertedTemperature by remember {
        mutableStateOf(Float.NaN)
    }

    val calc = {
        val temp = temperature.toFloat()
        convertedTemperature = if (scale == R.string.celsius)
            (temp * 1.8F) + 32F
        else
            (temp - 32F) / 1.8F
    }
    val result = remember(convertedTemperature) {
        if (convertedTemperature.isNaN()) ""
        else
            "${String.format("%.2f", convertedTemperature)}${
                if (scale == R.string.celsius)
                    strFahrenheit
                else strCelsius
            }"
    }
    val enabled = temperature.isNotBlank()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TemperatureTextField(
            temperature = temperature,
            onValueChange = {temperature = it},
            callback = calc,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TemperatureScaleButtonGroup(
            selected = scale,
            radioButtonClicked = {scale = it}
        )
        Button(
            onClick = calc,
            enabled = enabled
        ) {
            Text(
                text = stringResource(id = R.string.convert),
            )
        }
        if (result.isNotBlank()) {
            Text(
                text = result,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview
@Composable
fun TemperatureTextField (
    modifier: Modifier = Modifier,
    temperature: String = "15.0",
    onValueChange: (String) -> Unit = {},
    callback: () -> Unit = {}
) {
    TextField(
        value = temperature,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = stringResource(id = R.string.placeholder))
        },
        modifier = modifier,
        keyboardActions = KeyboardActions(onAny = {callback()}),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        singleLine = true
    )
}

@Preview
@Composable
fun TemperatureScaleButtonGroup(
    modifier: Modifier = Modifier,
    selected: Int = 0,
    radioButtonClicked: (Int) -> Unit = {},
) {
    Row (modifier = modifier){
        TemperatureRadioButton(
            selected = selected == R.string.celsius,
            resId = R.string.celsius,
            onClick = {radioButtonClicked(R.string.celsius)}
        )
        TemperatureRadioButton(
            selected = selected == R.string.fahrenheit,
            resId = R.string.fahrenheit,
            onClick = {radioButtonClicked(R.string.fahrenheit)},
            modifier = Modifier.padding(start = 16.dp)
        )
    }

}

@Preview
@Composable
fun TemperatureRadioButton(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    resId: Int = 0,
    onClick: (Int) -> Unit = {}
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        RadioButton(selected = selected, onClick = { onClick(5) })
        Text(
            text = stringResource(id = resId),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
