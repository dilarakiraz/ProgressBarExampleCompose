package com.dilara.progressbarexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dilara.progressbarexample.ui.theme.ProgressBarExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProgressBarExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {

                        MultiColorProgressBar(
                            segments = listOf(
                                ProgressSegment(0.3f, listOf(Color.Green)),
                                ProgressSegment(0.4f, listOf(Color.Gray)),
                                ProgressSegment(0.3f, listOf(Color.Red))
                            ),
                            modifier = Modifier.padding(innerPadding),
                            height = 24.dp,
                            cornerRadius = 12.dp
                        )

                        GradientMultiColorProgressBar(
                            segments = listOf(
                                GradientProgressSegment(0.3f, listOf(Color.Green, Color.Blue)),
                                GradientProgressSegment(0.4f, listOf(Color.Gray, Color.Yellow)),
                                GradientProgressSegment(0.3f, listOf(Color.Red, Color.Magenta))
                            ),
                            modifier = Modifier.padding(innerPadding),
                            height = 24.dp,
                            cornerRadius = 12.dp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProgressBarExampleTheme {
        Greeting("Android")
    }
}