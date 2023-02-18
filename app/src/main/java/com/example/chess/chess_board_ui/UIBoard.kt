package com.example.chess.chess_board_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.chess.chess_engine.EngineEvent
import com.example.chess.ui.theme.Cell1
import com.example.chess.ui.theme.Cell2
import com.example.chess.viewmodel.ChessModel

@Composable
fun UIBoard(viewModel: ChessModel) {
    Column {
        repeat(4) { col ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(4) { row ->
                    Cell(
                        col = 9 - ((col + 1) * 2 - 1),
                        background = Cell1,
                        row = (row + 1) * 2 - 1,
                        viewModel = viewModel
                    )
                    Cell(
                        col = 9 - ((col + 1) * 2 - 1),
                        background = Cell2,
                        row = (row + 1) * 2,
                        viewModel = viewModel
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(4) { row ->
                    Cell(
                        col = 9 - ((col + 1) * 2),
                        background = Cell2,
                        row = (row + 1) * 2 - 1,
                        viewModel = viewModel
                    )
                    Cell(
                        col = 9 - ((col + 1) * 2),
                        background = Cell1,
                        row = (row + 1) * 2,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Cell(
    size: Dp = 45.dp,
    isClicked: Boolean = false,
    background: Color,
    col: Int,
    row: Int,
    viewModel: ChessModel
) {
    val click by remember { mutableStateOf(!isClicked) }
    Box(
        modifier = Modifier
            .background(if (click) background else Color.Blue)
            .size(DpSize(size, size))
            .clickable {
                viewModel.onEvent(EngineEvent.ToPosition(col, row))
                println(viewModel.state.value)
            }
    )
}