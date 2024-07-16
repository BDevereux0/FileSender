package com.example.filetransferapp.mainScreen
import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.filetransferapp.mainScreen.DisplayCamera
@Composable
fun ShowCamera(){
    var showCamera by remember { mutableStateOf(false) }

    if (showCamera) {
        DisplayCamera()
    } else {
        DrawScreen(onShowCamera = { showCamera = true })
    }
}


@Composable
fun TakePicture(){
//courtesy of chatGPT

    //Provides access to the current context within a composable function
    val context = LocalContext.current

    //grabs the current permissions. These are required to use camera
    val requiredPermissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    //A launcher that can handle the result of requesting multiple permissions.
    //looks like this is set up to handle a permissions request and should it get that, executes
    //the code?
    val activityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        var permissionGranted = true
        permissions.entries.forEach {
            if (it.key in requiredPermissions && !it.value) {
                permissionGranted = false
            }
        }
        if (!permissionGranted) {
            Toast.makeText(context, "Permission request denied", Toast.LENGTH_SHORT).show()
        } else {
            //startCamera()
        }
    }

    activityResultLauncher.launch(requiredPermissions)
}

@Composable
fun DrawScreen(onShowCamera: () -> Unit, modifier: Modifier = Modifier){

    Row (modifier = Modifier.padding(top = 32.dp, end = 100.dp),
        horizontalArrangement = Arrangement.Center){
        Button(
            onClick = { TakePicture() },
            shape = CutCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.Red),
            modifier = Modifier.padding(all = 5.dp),
        ) {
            Text(text = "Picture")
        }

        Button(onClick = { /*TODO*/ },
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.Red),
            modifier = Modifier.padding(all = 5.dp)
        ) {
            Text(text = "Select")
        }

        Button(onClick = { /*TODO*/ },
            shape = CutCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.Red),
            modifier = Modifier.padding(all = 5.dp),
        ) {
            Text(text = "Send")
        }

    }

    Row(modifier = Modifier.padding(start = 100.dp, end = 10.dp, top = 100.dp, bottom = 100.dp)) {
        Button(onClick = { /*TODO*/ },
            //this draws a triangle.
            //moveTo() sets the "pointer" to the middle of the shape.
            //lineTo() method draws a line from where the pointer is to
            //the (x,y) coord position using parameters.
            //close() draws a line from the current position to starting
            //point.
            //
            //First lineTo() call draws a line from top center position
            //to bottom right corner. Second call to lineTo() draws a line
            //from bot right position to the bot left position.
            //
            //
            //close() the draws a line from bot left to starting point.
            shape = GenericShape { size, _ ->
                moveTo(size.width / 2, 0f)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            },
            border = BorderStroke(1.dp, Color.Red),
            modifier = Modifier.padding(all = 5.dp)
        ) {
            Text(text = "Settings")
        }

        Button(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(0.dp, 10.dp, 10.dp),
            border = BorderStroke(1.dp, Color.Red),
            modifier = Modifier.padding(all = 5.dp)
        ) {
            Text(text = "Close")
        }
    }
}
