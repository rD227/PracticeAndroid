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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Speed Of Linking", modifier = Modifier.padding(top = 20.dp))
            
            // 【修改部分】：让位图按钮拥有和普通按钮一样的底色和形状
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.End)
                    .size(56.dp) // 这里的 56dp 是 Material 3 悬浮按钮的标准尺寸
                    .clip(CircleShape) // 圆形背景
                    .background(MaterialTheme.colorScheme.primary) // 使用主题的主色调作为背景
                    .clickable { isMenuVisible = !isMenuVisible }, // 点击功能移到 Box 上
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.link),
                    contentDescription = "打开菜单",
                    modifier = Modifier.size(28.dp), // 位图图标在按钮内的大小
                    // 如果你想让位图颜色也随主题变化（比如变成白色以适应蓝色背景）：
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                )
            }
        }

        // 侧滑菜单（从右边滑出）
        AnimatedVisibility(
            visible = isMenuVisible,
            enter = slideInHorizontally(initialOffsetX = { it }),
            exit = slideOutHorizontally(targetOffsetX = { it }),
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
