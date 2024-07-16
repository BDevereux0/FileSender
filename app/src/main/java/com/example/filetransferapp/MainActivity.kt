package com.example.filetransferapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.filetransferapp.ui.theme.FileTransferAppTheme
import com.example.filetransferapp.mainScreen.DrawScreen
import com.example.filetransferapp.mainScreen.ShowCamera

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FileTransferAppTheme {
                Surface(color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxSize()) {
                    ShowCamera()
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
    FileTransferAppTheme {
        Surface(color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxSize()) {
            Greeting("Android")
        }

    }
}