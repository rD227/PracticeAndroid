package com.github.rd227.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text

class SecondActivity : ComponentActivity(){
   override fun oncreate(savedInstanceState: Bundle?){
        super.oncreate(savedInstanceState)
        setContent{
            Text(text = "Second Activity")
        }
    }
}

