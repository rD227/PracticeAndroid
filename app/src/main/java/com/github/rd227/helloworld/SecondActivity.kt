package com.github.rd227.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.rd227.helloworld.ui.theme.WelcomeApplicationTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WelcomeApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.background
//建议移除 import androidx.compose.material3.MaterialTheme.colorScheme，统一使用 MaterialTheme.colorScheme
                ) {
                    MainScreen()
                }
            }
        }
    }
}


/*
*
*
* Debug function
*
* */

@Preview(showBackground = true, name = "Left Menu Open")
@Composable
fun LeftMenuOpenPreview() {
    WelcomeApplicationTheme {
        MainScreen(initialLeftVisible = true)
    }
}

@Preview(showBackground = true)
@Composable
fun SecondActivityPreview() {
    WelcomeApplicationTheme {
        MainScreen()
    }
}

/*
@Preview(showBackground = true, name = "Right Menu Open")
@Composable
fun RightMenuOpenPreview() {
    WelcomeApplicationTheme {
        MainScreen(initialRightVisible = true)
    }
}


*/
