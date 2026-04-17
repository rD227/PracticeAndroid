package com.github.rd227.helloworld

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment

class OldStyFragmentUIContainer : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.preferences, container, false)
    }
}

@Preview
@Composable
fun OldStyFragmentUIPreview() {
    val context = LocalContext.current
    //It seems it will automatically use it if you didn't declare it
    AndroidView(
        factory = {// context ->
            val parent = android.widget.FrameLayout(context)
            LayoutInflater.from(context).inflate(
                R.layout.preferences,
                parent,
                false
            )
        },
        modifier = Modifier.fillMaxSize()
    )
}



