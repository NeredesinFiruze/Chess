package com.example.chess.chess_board

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.chess.R

@Composable
fun UIBoard(viewModel: ChessModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        repeat(4) { col ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                repeat(4) { row ->
                    CellGray(
                        col = 9 - ((col + 1) * 2 - 1),
                        row = (row + 1) * 2 - 1,
                        viewModel = viewModel
                    )
                    CellBlack(
                        col = 9 - ((col + 1) * 2 - 1),
                        row = (row + 1) * 2,
                        viewModel = viewModel
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                repeat(4) { row ->
                    CellBlack(
                        col = 9 - ((col + 1) * 2),
                        row = (row + 1) * 2 - 1,
                        viewModel = viewModel
                    )
                    CellGray(
                        col = 9 - ((col + 1) * 2),
                        row = (row + 1) * 2,
                        viewModel = viewModel
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
    viewModel: ChessModel
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
        contentAlignment = Alignment.Center
    ){
        val piece = viewModel.pieceAt(col, row)
        if (piece != null){
            when(piece.player == Player.WHITE){
                true -> {
                    when(piece.rank){
                        Rank.ROOK -> {
                            Image(
                                painter = painterResource(id = R.drawable.w_rook_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.BISHOP -> {
                            Image(
                                painter = painterResource(id = R.drawable.w_bishop_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.PAWN -> {
                            Image(
                                painter = painterResource(id = R.drawable.w_pawn_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.KING -> {
                            Image(
                                painter = painterResource(id = R.drawable.w_king_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.QUEEN -> {
                            Image(
                                painter = painterResource(id = R.drawable.w_queen_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.KNIGHT -> {
                            Image(
                                painter = painterResource(id = R.drawable.w_knight_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                    }
                }
                false -> {
                    when(piece.rank){
                        Rank.ROOK -> {
                            Image(
                                painter = painterResource(id = R.drawable.b_rook_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.BISHOP -> {
                            Image(
                                painter = painterResource(id = R.drawable.b_bishop_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.PAWN -> {
                            Image(
                                painter = painterResource(id = R.drawable.b_pawn_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.KING -> {
                            Image(
                                painter = painterResource(id = R.drawable.b_king_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.QUEEN -> {
                            Image(
                                painter = painterResource(id = R.drawable.b_queen_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.KNIGHT -> {
                            Image(
                                painter = painterResource(id = R.drawable.b_knight_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CellBlack(
    size: Dp = 45.dp,
    isClicked: Boolean = false,
    col: Int,
    row: Int,
    viewModel: ChessModel
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
    ){
        val piece = viewModel.pieceAt(col, row)
        if (piece != null){
            when(piece.player == Player.WHITE){
                true -> {
                    when(piece.rank){
                        Rank.ROOK -> {
                            Image(
                                painter = painterResource(id = R.drawable.w_rook_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.BISHOP -> {
                            Image(
                                painter = painterResource(id = R.drawable.w_bishop_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.PAWN -> {
                            Image(
                                painter = painterResource(id = R.drawable.w_pawn_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.KING -> {
                            Image(
                                painter = painterResource(id = R.drawable.w_king_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.QUEEN -> {
                            Image(
                                painter = painterResource(id = R.drawable.w_queen_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.KNIGHT -> {
                            Image(
                                painter = painterResource(id = R.drawable.w_knight_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                    }
                }
                false -> {
                    when(piece.rank){
                        Rank.ROOK -> {
                            Image(
                                painter = painterResource(id = R.drawable.b_rook_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.BISHOP -> {
                            Image(
                                painter = painterResource(id = R.drawable.b_bishop_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.PAWN -> {
                            Image(
                                painter = painterResource(id = R.drawable.b_pawn_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.KING -> {
                            Image(
                                painter = painterResource(id = R.drawable.b_king_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.QUEEN -> {
                            Image(
                                painter = painterResource(id = R.drawable.b_queen_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                        Rank.KNIGHT -> {
                            Image(
                                painter = painterResource(id = R.drawable.b_knight_2x_ns),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(.8f)
                            )
                        }
                    }
                }
            }
        }
    }
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
    //UIBoard()
}