package com.github.rd227.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
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
fun MainScreen(
    initialLeftVisible: Boolean = false,
    initialRightVisible: Boolean = false
) {
    // 状态：菜单是否打开
    var RightisMenuVisible by remember { mutableStateOf(initialRightVisible) }
    var LeftMenuVisible by remember { mutableStateOf(initialLeftVisible) }

    Box(modifier = Modifier.fillMaxSize()) {
        // 主内容
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 【左侧按钮】
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = 16.dp)
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable { LeftMenuVisible = !LeftMenuVisible },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gear),
                    contentDescription = "打开菜单",
                    modifier = Modifier.size(28.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                )
            }

            Text(
                text = "连接速度",
                modifier = Modifier.padding(top = 32.dp)
            )

            // 【右侧按钮】
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = 16.dp)
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable { RightisMenuVisible = !RightisMenuVisible },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.link),
                    contentDescription = "打开菜单",
                    modifier = Modifier.size(28.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                )
            }
        }


        // 2. 中层：背景遮罩 (Scrim)
        // 当菜单可见时，显示一个半透明层，点击它会关闭菜单
        AnimatedVisibility(
            visible = RightisMenuVisible || LeftMenuVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.32f)) // 半透明黑
                    .clickable(
                        // 使用无感点击，防止出现水波纹
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        RightisMenuVisible = false
                        LeftMenuVisible = false
                    }
            )
        }

        // 侧滑菜单（从右边滑出）
        AnimatedVisibility(
            visible = RightisMenuVisible,
            enter = slideInHorizontally(initialOffsetX = { it }) + fadeIn(),
            exit = slideOutHorizontally(targetOffsetX = { it }) + fadeOut(),
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(250.dp)
                    .background(colorScheme.surfaceVariant)
            ) {
                Column {
                    Text(text = "侧边菜单"/*, style = MaterialTheme.typography.headlineSmall*/,
                        modifier = Modifier.padding(top = 10.dp)
                            .padding(start = 10.dp)
                    )
                    Button(
                        onClick = { RightisMenuVisible = false },
                        modifier = Modifier.padding(top = 16.dp)
                        .padding(start = 10.dp)
                    ) {
                        Text("关闭")
                    }
                    RowClick(icon = ImageVector.vectorResource(id = R.drawable.connectsetting),"Network connect",onClick = {})
                    Text(text = "选项 2", modifier = Modifier.padding(top = 16.dp))
                }
            }
        }
        AnimatedVisibility(//left
            visible = LeftMenuVisible,
            enter = slideInHorizontally(initialOffsetX = { -it }) + fadeIn(),
            exit = slideOutHorizontally(targetOffsetX = { -it }) + fadeOut(),
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(250.dp)
                    .background(colorScheme.surfaceVariant)
                    .padding(16.dp)
            ) {
                Column {
                    Text(text = "侧边菜单"/*, style = MaterialTheme.typography.headlineSmall*/,
                        modifier = Modifier.padding(top = 10.dp))
                    Button(
                        onClick = { LeftMenuVisible = false },
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
/*
*
*
* Module function
*
* */

@Composable
fun RowClick(icon: ImageVector, text:String,onClick: () -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp)
        ){
        Box(                modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(9.dp)
            )
        }


        Spacer(modifier = Modifier.width(16.dp))

        // 右侧文字
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
/*
*
*
* Debug function
*
* */



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

@Preview(showBackground = true, name = "Left Menu Open")
@Composable
fun LeftMenuOpenPreview() {
    WelcomeApplicationTheme {
        MainScreen(initialLeftVisible = true)
    }
}
*/
