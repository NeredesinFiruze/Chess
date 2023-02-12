package com.example.chess.chess_board

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.chess.ui.theme.Cell1
import com.example.chess.ui.theme.Cell2

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

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { viewModel.possibleMoves() }
        ) {
            Text(text = "Move")
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
    var click by remember { mutableStateOf(!isClicked) }
    Box(
        modifier = Modifier
            .background(if (click) background else Color.Blue)
            .size(DpSize(size, size))
            .clickable {
                click = !click
            },
        contentAlignment = Alignment.Center
    ) {
        val picture = viewModel.piecePicture(col, row)

        if (picture != null){
            Image(
                painter = painterResource(id = picture),
                contentDescription = null,
                modifier = Modifier
                    .scale(.8f)
            )
        }
    }
}