package com.github.rd227.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.rd227.helloworld.ui.theme.WelcomeApplicationTheme


class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WelcomeApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    //Text(text = "Second Activity")
                    SecondActivityPreview()
                }
            }
        }
    }
}

@Composable
fun SurroundButton(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = "Second Activity")
        Button(onClick = {

        },
            modifier = Modifier
                .padding(start = 280.dp ,
                    top = 10.dp,

                    )
                .align(Alignment.End)

        ){
            Text(text = "Button")
        }
    }
}


@Composable
@Preview(showBackground = true)
fun SecondActivityPreview() {
    WelcomeApplicationTheme {
        Column(modifier = Modifier.fillMaxSize()) { }
        SurroundButton()
    }
}