package com.github.rd227.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    // 状态：菜单是否打开
    var isMenuVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // 主内容
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Speed Of Linking")
            
            // 使用位图（Image）作为“按钮”
            // 假设你已经把回形针图片放到了 res/drawable/paperclip.png
            // 这里我先用 androidparty 作为演示，你可以换成你的图片名
            Image(
                painter = painterResource(id = R.drawable.androidparty), 
                contentDescription = "打开菜单",
                modifier = Modifier
                    .padding(16.dp)
                    .size(64.dp) // 控制位图大小
                    .align(Alignment.End) // 放在右边
                    .clickable { isMenuVisible = !isMenuVisible } // 让位图具备点击功能
            )
        }

        // 侧滑菜单（从右边滑出）
        AnimatedVisibility(
            visible = isMenuVisible,
            enter = slideInHorizontally(initialOffsetX = { it }), // 从右往左进
            exit = slideOutHorizontally(targetOffsetX = { it }),  // 从左往右出
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(250.dp)
                    .background(colorScheme.surfaceVariant)
                    .padding(16.dp)
            ) {
                Column {
                    Text(text = "侧边菜单", style = MaterialTheme.typography.headlineSmall)
                    Button(
                        onClick = { isMenuVisible = false },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("关闭")
                    }
                    Text(text = "选项 1", modifier = Modifier.padding(top = 16.dp))
                    Text(text = "选项 2", modifier = Modifier.padding(top = 16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondActivityPreview() {
    WelcomeApplicationTheme {
        MainScreen()
    }
}
