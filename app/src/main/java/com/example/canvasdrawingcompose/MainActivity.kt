package com.example.canvasdrawingcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.canvasdrawingcompose.ui.TimerViewModel
import com.example.canvasdrawingcompose.ui.theme.CanvasDrawingComposeTheme
import com.example.canvasdrawingcompose.ui.theme.Purple200
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasDrawingComposeTheme {
                // A surface container using the 'background' color from the theme

                val viewModel: TimerViewModel by viewModels()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(5, timerViewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(rewardNumber: Int, timerViewModel: TimerViewModel?) {
    timerViewModel?.let {
        CustomIcon(rewardNumber = rewardNumber, it)
    }
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .size(300.dp)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Icon",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun CustomIcon(rewardNumber: Int, viewModel: TimerViewModel){
    val timeState = remember { mutableStateOf(viewModel.timeState.value) }

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Canvas(
            modifier = Modifier
                .size(300.dp)
                .padding(20.dp),
        ) {
            val times = 8
            var start = -90f
            val arcSize = 22.5f

            viewModel.startTime(rewardNumber)
            repeat(times) { i ->
                drawArc(
                    color = if(i < rewardNumber) Color.Red else Color.Red.copy(alpha = 0.1F),
                    startAngle = start,
                    sweepAngle = arcSize,
                    useCenter = false,
                    style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round),
                )
                start += arcSize + arcSize
            }

            drawCircle(
                color = Color.Red,
                radius = 100.dp.toPx(),
                alpha = 0.05F
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CanvasDrawingComposeTheme {
        Greeting(5, null)
    }
}


/**
 * Scale
 */

//Canvas(
//modifier = Modifier.fillMaxSize(),
//){
//    scale(scaleX = 10f, scaleY = 15f){
//        drawCircle(
//            Color.Cyan,
////                center = Offset(
////                    20.dp.toPx(),
////                    100.dp.toPx()
////                ),
//            radius = 20.dp.toPx()
//        )
//    }
//}

/**
 * Translate
 */

//Canvas(
//modifier = Modifier.fillMaxSize(),
//){
//    translate(left = 100f, top = -300f) {
//        drawCircle(Color.Cyan, radius = 200.dp.toPx())
//    }
//}

/**
 * Rotate
 */

//Canvas(
//modifier = Modifier.fillMaxSize(),
//){
//    rotate(degrees = 45F){
//        drawRect(
//            color = Color.Gray,
//            topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
//            size = size / 3F
//        )
//    }
//}

/**
 * Inset
 */

//Canvas(
//modifier = Modifier.fillMaxSize(),
//){
//    val quadrantSize = size / 2F
//    inset(horizontal = 50F, vertical = 50F){
//        drawRect(
//            color = Color.Gray,
//            size = quadrantSize
//        )
//    }
//}

/**
 * Multiple Transformation
 */

//Canvas(
//modifier = Modifier.fillMaxSize(),
//){
//    withTransform({
//        translate(left = size.width / 8F, top = size.height/2F)
//        rotate(degrees = 45F)
//    }) {
//        drawRect(
//            color = Color.Gray,
//            size = size / 2F
//        )
//    }
//}

/**
 * Drawing a graph // not complete by the way
 */

//Canvas(
//modifier = Modifier
//.padding(8.dp)
//.aspectRatio(3 / 2F)
//.fillMaxSize()
//.drawWithCache {
//
//    val path = generatePath(graphData, size)
//
//    onDrawBehind {
//        drawPath(
//            path = path,
//            color = Color.Green,
//            style = Stroke(2.dp.toPx())
//        )
//    }
//}
//) {
//    val barWidthPx = 1.dp.toPx()
//    drawRect(
//        color = Color.White,
//        style = Stroke(barWidthPx)
//    )
//
//    val verticalLines = 4
//    val verticalSize = size.width / (verticalLines + 1)
//    repeat(verticalLines) { i ->
//        val startX = verticalSize * (i + 1)
//        drawLine(
//            Color.White,
//            start = Offset(startX, 0f),
//            end = Offset(startX, size.height),
//            strokeWidth = barWidthPx
//        )
//    }
//
//    val horizontalLines = 3
//    val sectionSize = size.height / (horizontalLines + 1)
//    repeat(horizontalLines) { i ->
//        val startY = sectionSize * (i + 1)
//        drawLine(
//            Color.White,
//            start = Offset(0F, startY),
//            end = Offset(size.width, startY),
//            strokeWidth = barWidthPx
//        )
//    }
//}

//private fun generatePath(data: List<Balance>, size: Size) : Path {
//    val path = Path()
//
//    data.forEachIndexed { i, balance ->
//        val x =
//        val y =
//
//        path.lineTo(x,y)
//    }
//
//    return path
//}

/**
 * Dashed Circle
 */

//drawCircle(
//color = Color(0xff0f9d58),
//center = Offset(
//20.dp.toPx(),
//100.dp.toPx()
//),
//radius = size.minDimension/2,
//style = Stroke(width = 8f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f))
//)