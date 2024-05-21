package com.example.viewmodeldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewmodeldemo.ui.theme.ViewModelDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelDemo()
        }
    }
}


class MyViewModel : ViewModel() {

    private val _text: MutableLiveData<String> =
        MutableLiveData<String>("Hello #3")

    val text: LiveData<String>
        get() = _text

    fun setText(value: String) {
        _text.value = value
    }
}


@Composable
@Preview
fun ViewModelDemo() {
    val viewModel: MyViewModel =
    val state1 = remember {
        mutableStateOf("Hello #1")
    }
    val state2 = rememberSaveable {
        mutableStateOf("Hello #2")
    }
    state3.value?.let {
        Column(modifier = Modifier.fillMaxWidth()) {
            MyTextField(state1.value) { state1.value = it }
            MyTextField(state2.value) { state2.value = it }
            MyTextField(state3.value) {
                viewModel.setText(it)
            }
        }
    }
}

@Composable
fun MyTextField(
    value: String? = "",
    onValueChange: (String) -> Unit = {}
) {
    value?.let {
        TextField(
            value = it,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}