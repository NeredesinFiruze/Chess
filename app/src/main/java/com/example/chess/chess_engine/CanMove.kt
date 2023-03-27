package com.example.chess.chess_engine

class CanMove {

    fun canMove(
        pieces: Pieces,
        viewModel: ChessModel,
    ): ArrayList<Int> {

        val col = pieces.col
        val row = pieces.row
        val isWhite = pieces.player == Player.WHITE

        val allPosition = arrayListOf<Int>()

        when (pieces.rank) {
            Rank.ROOK -> {
                for (i in 1 until 8) {
                    if (viewModel.pieceAt(col + i, row) == null) {
                        if (col + i in 1..8) {
                            allPosition.add("${col + i}$row".toInt())
                        }
                    } else if (viewModel.pieceAt(col + i, row)?.player == pieces.player) {
                        break
                    } else {
                        if (col + i in 1..8) {
                            allPosition.add("${col + i}$row".toInt())
                        }
                        break
                    }
                }
                for (i in 1 until 8) {
                    if (viewModel.pieceAt(col - i, row) == null) {
                        if (col - i in 1..8)
                            allPosition.add("${col - i}$row".toInt())
                    } else if (viewModel.pieceAt(col - i, row)?.player == pieces.player) {
                        break
                    } else {
                        if (col - i in 1..8)
                            allPosition.add("${col - i}$row".toInt())
                        break
                    }
                }
                for (i in 1..8) {
                    if (viewModel.pieceAt(col, row + i) == null) {
                        if (row + i in 1..8) {
                            allPosition.add("$col${row + i}".toInt())
                        }
                    } else if (viewModel.pieceAt(col, row + i)?.player == pieces.player) {
                        break
                    } else {
                        if (row + i in 1..8)
                            allPosition.add("$col${row + i}".toInt())
                        break
                    }
                }
                for (i in 1..8) {
                    if (viewModel.pieceAt(col, row - i) == null) {
                        if (row - i in 1..8) {
                            allPosition.add("$col${row - i}".toInt())
                        }
                    } else if (viewModel.pieceAt(col, row - i)?.player == pieces.player) {
                        break
                    } else {
                        if (row - i in 1..8)
                            allPosition.add("$col${row - i}".toInt())
                        break
                    }
                }
            }
            Rank.BISHOP -> {
                for (i in 1 until pieces.row) {
                    if (viewModel.pieceAt(col + i, row - i) == null) {
                        if (row - i in 1..8 && col + i in 1..8) {
                            allPosition.add("${col + i}${row - i}".toInt())
                        }
                    } else if (viewModel.pieceAt(col + i, row - i)?.player == pieces.player) {
                        break
                    } else {
                        if (row - i in 1..8 && col + i in 1..8) {
                            allPosition.add("${col + i}${row - i}".toInt())
                        }
                        break
                    }
                }
                for (i in 1 until pieces.row) {
                    if (viewModel.pieceAt(col - i, row - i) == null) {
                        if (row - i in 1..8 && col - i in 1..8)
                            allPosition.add("${col - i}${row - i}".toInt())
                    } else if (viewModel.pieceAt(col - i, row - i)?.player == pieces.player) {
                        break
                    } else {
                        if (row - i in 1..8 && col - i in 1..8)
                            allPosition.add("${col - i}${row - i}".toInt())
                        break
                    }
                }
                for (i in 1..8 - pieces.row) {
                    if (viewModel.pieceAt(col + i, row + i) == null) {
                        if (row + i in 1..8 && col + i in 1..8) {
                            allPosition.add("${col + i}${row + i}".toInt())
                        }
                    } else if (viewModel.pieceAt(col + i, row + i)?.player == pieces.player) {
                        break
                    } else {
                        if (row + i in 1..8 && col + i in 1..8)
                            allPosition.add("${col + i}${row + i}".toInt())
                        break
                    }
                }
                for (i in 1..8 - pieces.row) {
                    if (viewModel.pieceAt(col - i, row + i) == null) {
                        if (row + i in 1..8 && col - i in 1..8) {
                            allPosition.add("${col - i}${row + i}".toInt())
                        }
                    } else if (viewModel.pieceAt(col - i, row + i)?.player == pieces.player) {
                        break
                    } else {
                        if (row + i in 1..8 && col - i in 1..8)
                            allPosition.add("${col - i}${row + i}".toInt())
                        break
                    }
                }
            }
            Rank.PAWN -> {
                if (isWhite) {
                    val enemy = viewModel.pieceAt(col + 1, row + 1)
                    val enemy2 = viewModel.pieceAt(col + 1, row - 1)
                    val enemy3 = viewModel.pieceAt(col + 1, row)

                    if (enemy3 == null) allPosition.add("${col + 1}$row".toInt())
                    if (col == 2 &&
                        viewModel.pieceAt((col + 2), row) == null &&
                        viewModel.pieceAt((col + 1), row) == null
                    ) allPosition.add("${col + 2}$row".toInt())

                    if (enemy?.player == Player.BLACK) allPosition.add("${enemy.col}${enemy.row}".toInt())
                    if (enemy2?.player == Player.BLACK) allPosition.add("${enemy2.col}${enemy2.row}".toInt())

                    if (viewModel.boardState.value.moveList.isNotEmpty()){

                        val lastMove = viewModel.boardState.value.moveList.last()
                        val isPawn = lastMove.take(1).contains("p")
                        val isRightPosition = lastMove[1] - lastMove[3] == 2
                        val goTo = lastMove.takeLast(2).toInt()
                        val piece = (pieces.col.toString() + pieces.row.toString()).toInt()

                        if (viewModel.pieceAt(col,row - 1) != null &&
                            isRightPosition &&
                            isPawn &&
                            piece - 1 == goTo
                        ){
                            allPosition.add(goTo + 10)
                        }
                        else if (viewModel.pieceAt(col,row + 1) != null &&
                            isRightPosition &&
                            isPawn &&
                            piece + 1 == goTo
                        ){
                            allPosition.add(goTo + 10)
                        }
                    }

                } else {
                    val enemy = viewModel.pieceAt(col - 1, row + 1)
                    val enemy2 = viewModel.pieceAt(col - 1, row - 1)
                    val enemy3 = viewModel.pieceAt(col - 1, row)

                    if (enemy3 == null) allPosition.add("${col - 1}$row".toInt())
                    if (col == 7 &&
                        viewModel.pieceAt(col - 2, row) == null &&
                        viewModel.pieceAt(col - 1, row) == null
                    ) allPosition.add("${col - 2}$row".toInt())
                    if (enemy?.player == Player.WHITE) allPosition.add("${enemy.col}${enemy.row}".toInt())
                    if (enemy2?.player == Player.WHITE) allPosition.add("${enemy2.col}${enemy2.row}".toInt())

                    val lastMove = viewModel.boardState.value.moveList.last()
                    val isPawn = lastMove.take(1).contains("P")
                    val isRightPosition = lastMove[3] - lastMove[1] == 2
                    val goTo = lastMove.takeLast(2).toInt()
                    val piece = (pieces.col.toString() + pieces.row.toString()).toInt()

                    if (viewModel.pieceAt(col,row - 1) != null &&
                        isRightPosition &&
                        isPawn &&
                        piece - 1 == goTo
                    ){
                        allPosition.add(goTo - 10)
                    }
                    else if (viewModel.pieceAt(col,row + 1) != null &&
                        isRightPosition &&
                        isPawn &&
                        piece + 1 == goTo
                    ){
                        allPosition.add(goTo - 10)
                    }
                }
            }
            Rank.KING -> {
                if (viewModel.pieceAt(col + 1, row + 1)?.player != pieces.player)
                    allPosition.add("${col + 1}${row + 1}".toInt())

                if (viewModel.pieceAt(col - 1, row + 1)?.player != pieces.player)
                    allPosition.add("${col - 1}${row + 1}".toInt())

                if (viewModel.pieceAt(col + 1, row - 1)?.player != pieces.player)
                    allPosition.add("${col + 1}${row - 1}".toInt())

                if (viewModel.pieceAt(col - 1, row - 1)?.player != pieces.player)
                    allPosition.add("${col - 1}${row - 1}".toInt())

                if (viewModel.pieceAt(col, row + 1)?.player != pieces.player)
                    allPosition.add("$col${row + 1}".toInt())

                if (viewModel.pieceAt(col, row - 1)?.player != pieces.player)
                    allPosition.add("$col${row - 1}".toInt())

                if (viewModel.pieceAt(col + 1, row)?.player != pieces.player)
                    allPosition.add("${col + 1}$row".toInt())

                if (viewModel.pieceAt(col - 1, row)?.player != pieces.player)
                    allPosition.add("${col - 1}$row".toInt())

                val kingMoves = viewModel.boardState.value.moveList.map{ it.take(1)}

                if (viewModel.pieceAt(1,6) == null &&
                    viewModel.pieceAt(1,7) == null
                ){
                    if (isWhite && !kingMoves.contains("K")){

                        val rookMovesRight = viewModel.boardState.value.moveList.map{ it.take(3)}.contains("R18")
                        if (!rookMovesRight){
                            allPosition.add(17)
                        }
                    }
                }
                if (viewModel.pieceAt(1,2) == null &&
                    viewModel.pieceAt(1,3) == null &&
                    viewModel.pieceAt(1,4) == null
                ){
                    if (isWhite && !kingMoves.contains("K")){

                        val rookMovesLeft = viewModel.boardState.value.moveList.map{ it.take(3)}.contains("R11")
                        if (!rookMovesLeft){
                            allPosition.add(13)
                        }
                    }
                }
                if (viewModel.pieceAt(8,6) == null &&
                    viewModel.pieceAt(8,7) == null
                ){
                    if (!isWhite && !kingMoves.contains("k")){

                        val rookMovesRight = viewModel.boardState.value.moveList.map{ it.take(3)}.contains("r88")
                        if (!rookMovesRight){
                            allPosition.add(87)
                        }
                    }
                }
                if (viewModel.pieceAt(8,2) == null &&
                    viewModel.pieceAt(8,3) == null &&
                    viewModel.pieceAt(8,4) == null
                ){
                    if (!isWhite && !kingMoves.contains("k")){

                        val rookMovesLeft = viewModel.boardState.value.moveList.map{ it.take(3)}.contains("r81")
                        if (!rookMovesLeft){
                            allPosition.add(83)
                        }
                    }
                }
            }
            Rank.QUEEN -> {
                for (i in 1 until pieces.row) {
                    if (viewModel.pieceAt(col + i, row - i) == null) {
                        if (row - i in 1..8 && col + i in 1..8) {
                            allPosition.add("${col + i}${row - i}".toInt())
                        }
                    } else if (viewModel.pieceAt(col + i, row - i)?.player == pieces.player) {
                        break
                    } else {
                        if (row - i in 1..8 && col + i in 1..8) {
                            allPosition.add("${col + i}${row - i}".toInt())
                        }
                        break
                    }
                }
                for (i in 1 until pieces.row) {
                    if (viewModel.pieceAt(col - i, row - i) == null) {
                        if (row - i in 1..8 && col - i in 1..8)
                            allPosition.add("${col - i}${row - i}".toInt())
                    } else if (viewModel.pieceAt(col - i, row - i)?.player == pieces.player) {
                        break
                    } else {
                        if (row - i in 1..8 && col - i in 1..8)
                            allPosition.add("${col - i}${row - i}".toInt())
                        break
                    }
                }
                for (i in 1..8 - pieces.row) {
                    if (viewModel.pieceAt(col + i, row + i) == null) {
                        if (row + i in 1..8 && col + i in 1..8) {
                            allPosition.add("${col + i}${row + i}".toInt())
                        }
                    } else if (viewModel.pieceAt(col + i, row + i)?.player == pieces.player) {
                        break
                    } else {
                        if (row + i in 1..8 && col + i in 1..8)
                            allPosition.add("${col + i}${row + i}".toInt())
                        break
                    }
                }
                for (i in 1..8 - pieces.row) {
                    if (viewModel.pieceAt(col - i, row + i) == null) {
                        if (row + i in 1..8 && col - i in 1..8) {
                            allPosition.add("${col - i}${row + i}".toInt())
                        }
                    } else if (viewModel.pieceAt(col - i, row + i)?.player == pieces.player) {
                        break
                    } else {
                        if (row + i in 1..8 && col - i in 1..8)
                            allPosition.add("${col - i}${row + i}".toInt())
                        break
                    }
                }
                for (i in 1 until 8) {
                    if (viewModel.pieceAt(col + i, row) == null) {
                        if (col + i in 1..8) {
                            allPosition.add("${col + i}$row".toInt())
                        }
                    } else if (viewModel.pieceAt(col + i, row)?.player == pieces.player) {
                        break
                    } else {
                        if (col + i in 1..8) {
                            allPosition.add("${col + i}$row".toInt())
                        }
                        break
                    }
                }
                for (i in 1 until 8) {
                    if (viewModel.pieceAt(col - i, row) == null) {
                        if (col - i in 1..8)
                            allPosition.add("${col - i}$row".toInt())
                    } else if (viewModel.pieceAt(col - i, row)?.player == pieces.player) {
                        break
                    } else {
                        if (col - i in 1..8)
                            allPosition.add("${col - i}$row".toInt())
                        break
                    }
                }
                for (i in 1..8) {
                    if (viewModel.pieceAt(col, row + i) == null) {
                        if (row + i in 1..8) {
                            allPosition.add("$col${row + i}".toInt())
                        }
                    } else if (viewModel.pieceAt(col, row + i)?.player == pieces.player) {
                        break
                    } else {
                        if (row + i in 1..8)
                            allPosition.add("$col${row + i}".toInt())
                        break
                    }
                }
                for (i in 1..8) {
                    if (viewModel.pieceAt(col, row - i) == null) {
                        if (row - i in 1..8) {
                            allPosition.add("$col${row - i}".toInt())
                        }
                    } else if (viewModel.pieceAt(col, row - i)?.player == pieces.player) {
                        break
                    } else {
                        if (row - i in 1..8)
                            allPosition.add("$col${row - i}".toInt())
                        break
                    }
                }
            }
            Rank.KNIGHT -> {
                if (viewModel.pieceAt(col + 2, row + 1)?.player != pieces.player) {
                    if (col + 2 in 1..8 && row + 1 in 1..8) {
                        allPosition.add("${col + 2}${row + 1}".toInt())
                    }
                }
                if (viewModel.pieceAt(col + 2, row - 1)?.player != pieces.player) {
                    if (col + 2 in 1..8 && row - 1 in 1..8) {
                        allPosition.add("${col + 2}${row - 1}".toInt())
                    }
                }
                if (viewModel.pieceAt(col + 1, row + 2)?.player != pieces.player) {
                    if (col + 1 in 1..8 && row + 2 in 1..8) {
                        allPosition.add("${col + 1}${row + 2}".toInt())
                    }
                }
                if (viewModel.pieceAt(col + 1, row - 2)?.player != pieces.player) {
                    if (col + 1 in 1..8 && row - 2 in 1..8) {
                        allPosition.add("${col + 1}${row - 2}".toInt())
                    }
                }
                if (viewModel.pieceAt(col - 1, row + 2)?.player != pieces.player) {
                    if (col - 1 in 1..8 && row + 2 in 1..8) {
                        allPosition.add("${col - 1}${row + 2}".toInt())
                    }
                }
                if (viewModel.pieceAt(col - 1, row - 2)?.player != pieces.player) {
                    if (col - 1 in 1..8 && row - 2 in 1..8) {
                        allPosition.add("${col - 1}${row - 2}".toInt())
                    }
                }
                if (viewModel.pieceAt(col - 2, row + 1)?.player != pieces.player) {
                    if (col - 2 in 1..8 && row + 1 in 1..8) {
                        allPosition.add("${col - 2}${row + 1}".toInt())
                    }
                }
                if (viewModel.pieceAt(col - 2, row - 1)?.player != pieces.player) {
                    if (col - 2 in 1..8 && row - 1 in 1..8) {
                        allPosition.add("${col - 2}${row - 1}".toInt())
                    }
                }
            }
        }
        return allPosition
    }
}