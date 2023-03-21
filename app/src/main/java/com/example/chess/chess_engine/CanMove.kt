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
                for (i in 1 until 7) {
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
                for (i in 1 until 7) {
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
                for (i in 1..7) {
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
                for (i in 1..7) {
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
                    if (col == 2 && viewModel.pieceAt(col + 2, row) == null) allPosition.add("${col + 2}$row".toInt())
                    if (enemy?.player == Player.BLACK) allPosition.add("${enemy.col}${enemy.row}".toInt())
                    if (enemy2?.player == Player.BLACK) allPosition.add("${enemy2.col}${enemy2.row}".toInt())
                } else {
                    val enemy = viewModel.pieceAt(col - 1, row + 1)
                    val enemy2 = viewModel.pieceAt(col - 1, row - 1)
                    val enemy3 = viewModel.pieceAt(col - 1, row)

                    if (enemy3 == null) allPosition.add("${col - 1}$row".toInt())
                    if (col == 7) allPosition.add("${col - 2}$row".toInt())
                    if (enemy?.player == Player.WHITE) allPosition.add("${enemy.col}${enemy.row}".toInt())
                    if (enemy2?.player == Player.WHITE) allPosition.add("${enemy2.col}${enemy2.row}".toInt())
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
                    allPosition.add("${col}${row + 1}".toInt())

                if (viewModel.pieceAt(col, row - 1)?.player != pieces.player)
                    allPosition.add("${col}${row - 1}".toInt())

                if (viewModel.pieceAt(col + 1, row)?.player != pieces.player)
                    allPosition.add("${col + 1}${row}".toInt())

                if (viewModel.pieceAt(col - 1, row)?.player != pieces.player)
                    allPosition.add("${col - 1}${row}".toInt())
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
                for (i in 1 until 7) {
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
                for (i in 1 until 7) {
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
                for (i in 1..7) {
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
                for (i in 1..7) {
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
                if (viewModel.pieceAt(col + 2, row + 1)?.player != pieces.player){
                    if (col + 2 in 1..8 && row + 1 in 1..8) {
                        allPosition.add("${col+2}${row + 1}".toInt())
                    }
                }
                if (viewModel.pieceAt(col + 2, row - 1)?.player != pieces.player){
                    if (col + 2 in 1..8 && row - 1 in 1..8) {
                        allPosition.add("${col + 2}${row - 1}".toInt())
                    }
                }
                if (viewModel.pieceAt(col + 1, row + 2)?.player != pieces.player){
                    if (col + 1 in 1..8 && row + 2 in 1..8) {
                        allPosition.add("${col + 1}${row + 2}".toInt())
                    }
                }
                if (viewModel.pieceAt(col + 1, row - 2)?.player != pieces.player){
                    if (col + 1 in 1..8 && row - 2 in 1..8) {
                        allPosition.add("${col + 1}${row - 2}".toInt())
                    }
                }
                if (viewModel.pieceAt(col - 1, row + 2)?.player != pieces.player){
                    if (col - 1 in 1..8 && row + 2 in 1..8) {
                        allPosition.add("${col - 1}${row + 2}".toInt())
                    }
                }
                if (viewModel.pieceAt(col - 1, row - 2)?.player != pieces.player){
                    if (col - 1 in 1..8 && row - 2 in 1..8) {
                        allPosition.add("${col - 1}${row - 2}".toInt())
                    }
                }
                if (viewModel.pieceAt(col - 2, row + 1)?.player != pieces.player){
                    if (col - 2 in 1..8 && row + 1 in 1..8) {
                        allPosition.add("${col - 2}${row + 1}".toInt())
                    }
                }
                if (viewModel.pieceAt(col - 2, row - 1)?.player != pieces.player){
                    if (col - 2 in 1..8 && row - 1 in 1..8) {
                        allPosition.add("${col - 2}${row - 1}".toInt())
                    }
                }
            }
        }
        return allPosition
    }
}