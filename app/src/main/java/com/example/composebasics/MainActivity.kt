package com.example.composebasics

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

// https://developer.android.com/codelabs/jetpack-compose-basics#7
@Composable
fun OnboardScreen(modifier: Modifier = Modifier) {
    // TODO: This state should be hoisted
    var shouldShowOnboard by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Jetpack Compose!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = { shouldShowOnboard = false }
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardPreview() {
    ComposeBasicsTheme {
        OnboardScreen()
    }
}

@Composable
fun Greeting(name: String) {
    // Setting a variable to change state
    // Remember is used to remember the previous state
    // and do not assign state as soon as recomposition happens
    // and change it only if changed by user or by external factors

    // each call of comosable has its own state

    val expanded = remember {
        mutableStateOf(false)
    }

    val extraPadding = if(expanded.value) 48.dp else 0.dp

    Surface(color = MaterialTheme.colorScheme.primary,
        // Padding for each column from outer composable
        modifier = Modifier.padding(horizontal = 2.dp, vertical = 4.dp)) {

        // Padding for inside of each Row element(for text and button)
        Row(modifier = Modifier
            .padding(24.dp)
            .padding(bottom = extraPadding)) {

            // Weight modifier makes it flexible and allows to take all available space
            // Hence, fillMaxWidth is not needed
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hello,")
                Text(text = "$name!")
            }

            // Using elevated button at end of row
            ElevatedButton(onClick = { expanded.value = !expanded.value }) {
                // Add any other composable inside button using trailing lambdas
                Text(if(expanded.value) "Show less" else "Show more")
            }
        }

    }
}

@Composable
private fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboard by remember {
        mutableStateOf(true)
    }
    Surface(modifier) {
        if (shouldShowOnboard) {
            OnboardScreen(/* TODO */)
        } else {
            greetingS()
        }
    }
}

@Preview
@Composable
fun MyAppPreview() {
    ComposeBasicsTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

@Composable
private fun greetingS(modifier: Modifier = Modifier) {

    // Added Columns on function calling with a composable
    val troopNames = listOf("Yeti","Wizard")

    // Padding for whole Column hence whole layout at this time
    Column(modifier.padding(horizontal = 4.dp, vertical = 4.dp)) {
        for (name in troopNames)
            Greeting(name = name)
    }
}

@Composable
fun greetingSPreview() {
    ComposeBasicsTheme {
        greetingS()
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