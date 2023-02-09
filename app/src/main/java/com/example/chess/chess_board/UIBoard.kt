package com.example.chess.chess_board

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Composable
fun UIBoard() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        repeat(4) { col ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                repeat(4) { row ->
                    CellGray(
                        col = 9 - ((col + 1) * 2 - 1),
                        row = (row + 1) * 2 - 1
                    )
                    CellBlack(
                        col = 9 -((col + 1) * 2 - 1),
                        row = (row + 1) * 2
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                repeat(4) { row ->
                    CellBlack(
                        col = 9 -((col + 1) * 2),
                        row = (row + 1) * 2 - 1
                    )
                    CellGray(
                        col =9 - ((col + 1) * 2),
                        row = (row + 1) * 2
                    )
                }
            }
        }
    }
}
@Composable
fun CellGray(
    size: Dp = 45.dp,
    isClicked: Boolean = false,
    col: Int,
    row: Int,
    content: @Composable BoxScope.() -> Unit = {}
) {
    var click by remember { mutableStateOf(!isClicked) }

    Box(
        modifier = Modifier
            .background(if (click) Color.Gray else Color.Blue)
            .size(DpSize(size, size))
            .clickable {
                click = !click
                println("col: $col, row: $row")
            },
        content = content
    )
}
@Composable
fun CellBlack(
    size: Dp = 45.dp,
    isClicked: Boolean = false,
    col: Int,
    row: Int,
    content: @Composable BoxScope.() -> Unit = {}
) {
    var click by remember { mutableStateOf(!isClicked) }

    Box(
        modifier = Modifier
            .background(if (click) Color.Black else Color.Blue)
            .size(DpSize(size, size))
            .clickable {
                click = !click
                println("col: $col, row: $row")
            },
        content = content
    )
}
//@Composable
//fun UIBoard() {
//    Canvas(
//        modifier =
//        Modifier
//            .fillMaxSize()
//            .padding(10.dp)
//            .pointerInput(true) {
//                detectTapGestures(
//                    onTap = {
//                        println("fail")
//                    }
//                )
//            }
//    ) {
//
//        val width = size.width / 8
//        val topPoint = (size.height - 8 * width) / 2f
//
//        for (height in 0..3) {
//            for (i in 0..3) {
//                drawRect(
//                    color = Color.Gray,
//                    topLeft = Offset(2 * width * i, topPoint + height * (2 * width)),
//                    size = Size(width, width)
//                )
//                drawRect(
//                    color = Color.Black,
//                    topLeft = Offset(2 * width * i + width, topPoint + height * (2 * width)),
//                    size = Size(width, width)
//                )
//                drawRect(
//                    color = Color.Black,
//                    topLeft = Offset(2 * width * i, topPoint + width + height * (2 * width)),
//                    size = Size(width, width)
//                )
//                drawRect(
//                    color = Color.Gray,
//                    topLeft = Offset(
//                        2 * width * i + width,
//                        topPoint + width + height * (2 * width)
//                    ),
//                    size = Size(width, width)
//                )
//            }
//
//        }
//    }
//}
@Preview
@Composable
fun Sd() {
    UIBoard()
}