package com.example.chess.chess_board_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chess.R
import com.example.chess.chess_engine.EngineEvent
import com.example.chess.chess_engine.ChessModel

@Composable
fun BoardWithPiece(viewModel: ChessModel) {

    val state by viewModel.boardState.collectAsState()

    Scaffold(
        drawerContent = {
            LazyColumn{
                item {
                    Text(text = "MOVES", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                }
                items(state.moveList){
                    val resourceID = when(it.take(1)){
                        "P"-> R.drawable.w_pawn
                        "p"-> R.drawable.b_pawn
                        "Q"-> R.drawable.w_queen
                        "q"-> R.drawable.b_queen
                        "N"-> R.drawable.w_knight
                        "n"-> R.drawable.b_knight
                        "B"-> R.drawable.w_bishop
                        "b"-> R.drawable.b_bishop
                        "R"-> R.drawable.w_rook
                        "r"-> R.drawable.b_rook
                        "K"-> R.drawable.w_king
                        "k"-> R.drawable.b_king
                        else-> R.drawable.ic_launcher_background
                    }
                    val column = when (it.last().digitToInt()) {
                        1 -> "a"
                        2 -> "b"
                        3 -> "c"
                        4 -> "d"
                        5 -> "e"
                        6 -> "f"
                        7 -> "g"
                        8 -> "h"
                        else -> ""
                    }
                    Row {
                        Image(
                            painter = painterResource(id = resourceID),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(text = column + it[3])
                    }
                }
            }
        }
    ) {paddingValues->
        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            Board(viewModel)
            state.board.forEach {
                Piece(
                    col = it.col,
                    row = it.row,
                    resourceID = viewModel.piecePicture(it.col, it.row)!!,
                    viewModel = viewModel
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${state.turn.name} turn",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = {
                        viewModel.onEvent(EngineEvent.NewGame)
                    }
                ) {
                    Text(text = "New Game")
                }
            }
        }
    }
}

@Composable
fun Piece(
    col: Int,
    row: Int,
    resourceID: Int,
    viewModel: ChessModel
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val boardSize = 45.dp * 8
        val piece = viewModel.pieceAt(col, row)

//        val positionX by animateDpAsState(
//            targetValue = ((screenWidth - boardSize) / 2) + 2.dp + (45.dp * (row - 1)),
//            animationSpec = tween(300)
//        )
//        val positionY by animateDpAsState(
//            targetValue = (((screenHeight - boardSize) / 2) + 2.dp) + (45.dp * ( 8 - col)),
//            animationSpec = tween(300)
//        )
        val positionX = ((screenWidth - boardSize) / 2) + 2.dp + (45.dp * (row - 1))
        val positionY = (((screenHeight - boardSize) / 2) + 2.dp) + (45.dp * ( 8 - col))

        Image(
            painter = painterResource(id = resourceID),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .offset(positionX, positionY)
                .clickable {
                    piece?.let {
                        if (viewModel.boardState.value.turn == piece.player) {
                            viewModel.onEvent(
                                EngineEvent.ChoosePiece(piece)
                            )
                        } else if (viewModel.boardState.value.state.piece != null) {
                            viewModel.onEvent(
                                EngineEvent.MoveTo(piece.col, piece.row)
                            )
                        }
                    }
                }
        )
    }
}