package com.github.rd227.helloworld

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.github.rd227.helloworld.ui.theme.WelcomeApplicationTheme
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

// 1. DataStore 必须定义在顶层，不能定义在函数内部
val Context.dataStore by preferencesDataStore(name = "settings")
val CLICK_COUNT_KEY = intPreferencesKey("click_count")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WelcomeApplicationTheme {
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

@SuppressLint("SuspiciousIndentation", "FlowOperatorInvokedInComposition")
@Composable
fun GreetingText(message: String, modifier: Modifier = Modifier, from: String) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope() // 获取协程作用域，用于在点击时写入数据

    // --- 内存中的状态 (进程结束即丢失) ---
    var clickCount by remember { mutableIntStateOf(0) }

    // --- 简单数据类中的状态 (每次重组都会重新创建对象，除非使用 remember) ---
    // 这里为了演示你的原代码逻辑，做了保留，但它在 UI 刷新或后台重启后会重置
    var clickCountObjectStable = remember { dataClass().clickCountStable }

    // --- Bundle 状态 (配置更改如旋转屏幕不丢，但彻底杀掉进程可能丢失) ---
    var stableClickCount by rememberSaveable { mutableIntStateOf(0) }

    // --- 2. DataStore 持久化状态 (保存到磁盘，彻底清空后台也不会丢失) ---
    // 通过 Flow 读取数据并转换为 Compose State
    val persistentClickCount by context.dataStore.data
        .map { preferences -> preferences[CLICK_COUNT_KEY] ?: 0 }
        .collectAsState(initial = 0)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = message,
            fontSize = 90.sp,
            modifier = Modifier.padding(8.dp, top = 50.dp),
            textAlign = TextAlign.Left,
            lineHeight = 100.sp,
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier.align(alignment = Alignment.End)
        )

        Button(
            onClick = {
                // 跳转逻辑
                val intent = Intent(context, SecondActivity::class.java)
                context.startActivity(intent)
                Toast.makeText(context, "test", Toast.LENGTH_SHORT).show()

                // 更新内存状态
                clickCount++
                stableClickCount++

                // 3. 在协程中更新 DataStore (持久化保存)
                scope.launch {
                    context.dataStore.edit { settings ->
                        val current = settings[CLICK_COUNT_KEY] ?: 0
                        settings[CLICK_COUNT_KEY] = current + 1
                    }
                }
            },
            modifier = Modifier.padding(10.dp, top = 100.dp)
        ) {
            Text(
                text = "DS Saved: $persistentClickCount | Mem: $clickCount | Saveable: $stableClickCount",
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty)
    Box(modifier = modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.78F,
            modifier = Modifier.fillMaxSize()
        )
        GreetingText(message = message, from = from)
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    WelcomeApplicationTheme {
        GreetingImage(
            message = stringResource(R.string.happy_birthday_sam),
            from = stringResource(R.string.from_emma)
        )
    }
}
