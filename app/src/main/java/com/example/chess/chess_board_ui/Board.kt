package com.example.chess.chess_board_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chess.chess_engine.EngineEvent
import com.example.chess.ui.theme.Cell1
import com.example.chess.ui.theme.Cell2
import com.example.chess.chess_engine.ChessModel

@Composable
fun Board(viewModel: ChessModel) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
        repeat(4){col->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =Arrangement.Center
            ) {
                repeat(4){row->
                    Cell(
                        background = Cell1,
                        col = 9 - ((col + 1) * 2 - 1),
                        row = (row + 1) * 2 - 1,
                        viewModel = viewModel
                    )
                    Cell(
                        background = Cell2,
                        col = 9 - ((col + 1) * 2 - 1),
                        row = (row + 1) * 2,
                        viewModel = viewModel
                    )
                }
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =Arrangement.Center
            ){
                repeat(4){row->
                    Cell(
                        background = Cell2,
                        col = 9 - ((col + 1) * 2),
                        row = (row + 1) * 2 - 1,
                        viewModel = viewModel
                    )
                    Cell(
                        background = Cell1,
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
fun Cell(
    background: Color,
    col: Int,
    row: Int,
    viewModel: ChessModel
) {
    val state by viewModel.boardState.collectAsState()

    Box(
        modifier = Modifier
            .size(DpSize(45.dp, 45.dp))
            .background(background)
            .drawBehind {
                if (state.canMove.contains("$col$row".toInt())){
                    drawCircle(color = Color(0xFF86E75C), radius = 15f)
                }
            }
            .clickable {
                viewModel.onEvent(EngineEvent.MoveTo(col, row))
            }
    ){
        Text(text = "$col$row", modifier = Modifier.align(Alignment.BottomStart), fontSize = 10.sp)
    }
}