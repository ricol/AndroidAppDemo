package com.example.androidappdemo.ui.compose

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class ComposeDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            MyUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyUI()
        }
    }
}

@Preview
@Composable
fun MyUI() {
    val color = remember {
        mutableStateOf(Color.Red)
    }
    val textSize = remember { 
        mutableStateOf(20)
    }
    Box(modifier = Modifier.background(Color.Black)) {
        Column(modifier = Modifier.padding(20.dp).fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            println("Column...")
            Text("Click me", color = color.value, fontSize = textSize.value.sp, modifier = Modifier.clickable {
                println("Hi there!")
                color.value = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat())
            })
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Green, fontSize = 30.sp)) {
                    append("J")
                }
                append("etpack")
                withStyle(style = SpanStyle(color = Color.Green, fontSize = 30.sp)) {
                    append("C")
                }
                append("ompose")
            }, color = Color.White, fontSize = textSize.value.sp, fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline)
            Row {
                Text("Hello", color = Color.Blue, fontSize = textSize.value.sp)
                Text("Android world!", color = Color.Cyan, fontSize = textSize.value.sp)
            }
            Text("Hi There", color = Color.White, fontSize = textSize.value.sp)
            Text(color = Color.Red, fontSize = textSize.value.sp, text = "hello Ricol!")
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text("text size: ${textSize.value}", color = Color.White)
                Spacer(modifier = Modifier.width(10.dp))
                Text("Increase", color = Color.White, modifier = Modifier.clickable {
                    textSize.value += 1
                })
                Spacer(modifier = Modifier.width(10.dp))
                Text("Decrease", color = Color.White, modifier = Modifier.clickable {
                    textSize.value -= 1
                })
            }
        }
    }
}