package com.example.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Surface(color = MaterialTheme.colorScheme.primary,
        // Padding for each column from outer composable
        modifier = Modifier.padding(horizontal = 2.dp, vertical = 4.dp)) {
        // Padding for inside of each Column element(for text)
        Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
            Text(text = "Hello,")
            Text(text = "$name!")
        }
    }
}

@Composable
private fun MyApp(modifier: Modifier = Modifier) {

    // Added Columns on function calling with a composable
    val troopNames = listOf<String>("Yeti","Wizard")

    // Padding for whole Column hence whole layout at this time
    Column(modifier.padding(horizontal = 4.dp, vertical = 4.dp)) {
        for (name in troopNames)
            Greeting(name = name)
    }
}

// widthDp shows preview with a fixed width
@Preview(showBackground = true, name = "Text preview", widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeBasicsTheme {
        MyApp()
    }
}