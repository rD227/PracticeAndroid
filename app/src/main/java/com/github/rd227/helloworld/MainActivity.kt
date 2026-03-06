package com.github.rd227.helloworld

import android.R.id.message
import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.rd227.helloworld.ui.theme.WelcomeApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WelcomeApplicationTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                }
            }
        }
    }
}


@Composable
fun GreetingText(message: String,modifier: Modifier = Modifier,from: String) {
    //Row {
        Surface(color = Color.Cyan) {
            Column {
                Text(
                    text = message,
                    fontSize = 100.sp,
                    modifier = modifier.padding(8.dp),
                    textAlign = TextAlign.Center,
                    lineHeight = 120.sp,
                )
                Text(
                    text = from,
                    fontSize = 36.sp,
                    modifier = modifier
                        .align(alignment = Alignment.End)
                )
            }
        }
    //}
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    WelcomeApplicationTheme() {
        GreetingText(message = "Happy Birthday Sam!", from = "From Emma")
    }
}


/*
*
*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WelcomeApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
//@Preview(showBackground = true)
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = Color.Gray) {
        Text(
            text = "Hello First $name!",
            modifier = modifier.padding(80.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WelcomeApplicationTheme {
        Greeting("Android")
    }
}
*
*
*/