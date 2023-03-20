package com.example.chess.chess_board_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.chess.chess_engine.EngineEvent
import com.example.chess.chess_engine.ChessModel

@Composable
fun BoardWithPiece(viewModel: ChessModel) {
    Box {
        val state by viewModel.boardState.collectAsState()

        Board(viewModel)
        state.board.forEach {
            Piece(
                col = it.col,
                row = it.row,
                resourceID = viewModel.piecePicture(it.col, it.row)!!,
                viewModel = viewModel
            )
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

//        val positionX = animateDpAsState(
//            targetValue = ((screenWidth - boardSize) / 2) + 2.dp + (45.dp * (row - 1)),
//            animationSpec = tween(300)
//        )
//        val positionY = animateDpAsState(
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
                        viewModel.onEvent(
                            EngineEvent.ChoosePiece(piece)
                        )
                    }
                }
        )
    }
}