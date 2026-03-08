package com.github.rd227.helloworld


//import android.media.Image
import android.os.Bundle
//import android.os.Message
//import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                    BirthdayCardPreview()
                }
            }
        }
    }
}


@Composable
fun GreetingText(message: String,modifier: Modifier = Modifier,from: String) {
    //Row {
        //Surface(color = Color.Cyan) {
            Column (
                verticalArrangement = Arrangement.Center,
                //horizontalAlignment = Alignment.Center as Alignment.Horizontal,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                Text(
                    text = message,
                    fontSize = 100.sp,
                    modifier = Modifier.padding(
                        8.dp,
                        top = 50.dp,
                    ),
                    textAlign = TextAlign.Left,
                    lineHeight = 100.sp,
                )
                Text(
                    text = from,
                    fontSize = 36.sp,
                    modifier = Modifier
                        .align(alignment = Alignment.End)
                )
                Button(onClick = {} ,
                    modifier = Modifier.padding(
                        20.dp,
                        top = 100.dp,
                        )
                ) {
                    Text(text = stringResource(R.string.click_me))
                }
            }
        //}
    //}
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty)
    Box(Modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.78F,
            modifier = Modifier
                .fillMaxSize()
        )
        GreetingText(message = message , from = from )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    WelcomeApplicationTheme() {
        GreetingImage(message = stringResource(R.string.happy_birthday_sam), from = stringResource(R.string.from_emma))
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