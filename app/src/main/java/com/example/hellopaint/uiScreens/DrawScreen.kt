package com.example.hellopaint.uiScreens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellopaint.ads.AdMobBanner
import com.example.hellopaint.models.Line

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawScreen() {
    var lines = remember {
        mutableStateListOf<Line>()
    }
    var currentColor by remember {
        mutableStateOf(Color.Black)
    }

        Box(modifier=Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primary),
                title = {
                    Text(
                        text = "Color Picker",
                        color = Color.White,
                        fontSize = 18.sp,

                        )
                },
                actions = {
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.Black),
                        onClick = { currentColor = Color.Black },
                        modifier = Modifier
                            .padding(3.dp)
                            .width(40.dp)
                    )
                    {
                        //black
                    }

                    Button(
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        onClick = { currentColor = Color.Red },
                        modifier = Modifier
                            .padding(3.dp)
                            .width(40.dp)
                    )
                    {
                        //red
                    }

                    Button(
                        colors = ButtonDefaults.buttonColors(Color.Green),
                        onClick = { currentColor = Color.Green },
                        modifier = Modifier
                            .padding(3.dp)
                            .width(40.dp)
                    )
                    {
                        //green
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.Blue),
                        onClick = { currentColor = Color.Blue },
                        modifier = Modifier
                            .padding(3.dp)
                            .width(40.dp)
                    )
                    {
                        //blue
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(Color.DarkGray),
                        onClick = { lines.removeLast() },
                        modifier = Modifier
                            .padding(3.dp)

                    )
                    {
                        //clear
                        Text(text = "Clear")
                    }

                }
            )
            Canvas(modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()

                        val line = Line(
                            start = change.position - dragAmount,
                            end = change.position,
                            color = currentColor,
                        )
                        lines.add(line)
                    }
                }
            )
            {
                lines.forEach { line ->
                    drawLine(
                        color = line.color,
                        start = line.start,
                        end = line.end,
                        strokeWidth = line.strokeWidth,
                        cap = StrokeCap.Round
                    )
                }
            }

        }

            Box(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 5.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter)
            ) {
                AdMobBanner(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                )
            }
    }
}


@Preview(showBackground = true)
@Composable
fun DrawScreenPreview() {
    DrawScreen()
}

/*
To save the draw you need two functions:
One to convert lines to bitmap

fun linesToBitmap(lines: List<Line>, width: Int, height: Int): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = android.graphics.Canvas(bitmap)
    val paint = Paint()

    Log.d("Lines", lines.toString())

    // Set the background color (optional)
    canvas.drawColor(0xFFFFFFFF.toInt())

    // Set the line color and style
    paint.strokeWidth = 5f
    paint.color = Color(0x00FFFFFF).toArgb()
    paint.style = Paint.Style.STROKE

    val path = Path()

    // Draw each line on the canvas
    for (line in lines) {
        val paint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = line.strokeWidth
            color = line.color.toArgb()
        }

        path.reset() // Reset the path for each line
        path.moveTo(line.start.x, line.start.y)
        path.lineTo(line.end.x, line.end.y)

        // Draw the path on the canvas using the specified paint
        canvas.drawPath(path, paint)
    }

    return bitmap
}

and one to save the generated bitmap
 suspend fun saveDrawing(bitmap: Bitmap) {
    withContext(Dispatchers.IO) {
        val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Drawings")
        if (!directory.exists()) {
            directory.mkdirs()
        }
        val fileName = "drawing_${System.currentTimeMillis()}.png"
        val file = File(directory, fileName)

        try {
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
*/