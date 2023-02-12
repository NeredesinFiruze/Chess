package com.example.chess.chess_board

import androidx.lifecycle.ViewModel
import com.example.chess.R

class ChessModel : ViewModel() {

    var cellBox = mutableSetOf<Pieces>()

    init {
        startPosition()
    }

    private fun startPosition() {
        cellBox.removeAll(cellBox)
        for (i in 1..8) {
            cellBox.add(Pieces(2, i, Rank.PAWN, Player.WHITE))
            cellBox.add(Pieces(7, i, Rank.PAWN, Player.BLACK))
        }
        for (i in 0..1) {
            cellBox.add(Pieces(1, if (i * 8 == 0) 1 else i * 8, Rank.ROOK, Player.WHITE))
            cellBox.add(Pieces(8, if (i * 8 == 0) 1 else i * 8, Rank.ROOK, Player.BLACK))

            cellBox.add(Pieces(1, i * 5 + 2, Rank.KNIGHT, Player.WHITE))
            cellBox.add(Pieces(8, i * 5 + 2, Rank.KNIGHT, Player.BLACK))

            cellBox.add(Pieces(1, i * 3 + 3, Rank.BISHOP, Player.WHITE))
            cellBox.add(Pieces(8, i * 3 + 3, Rank.BISHOP, Player.BLACK))
        }
        cellBox.add(Pieces(1, 4, Rank.QUEEN, Player.WHITE))
        cellBox.add(Pieces(8, 4, Rank.QUEEN, Player.BLACK))

        cellBox.add(Pieces(1, 5, Rank.KING, Player.WHITE))
        cellBox.add(Pieces(8, 5, Rank.KING, Player.BLACK))
    }

    fun possibleMoves() {
        cellBox.remove(Pieces(2, 5, Rank.PAWN, Player.WHITE))
        cellBox.add(Pieces(4, 5, Rank.PAWN, Player.WHITE))
    }

    fun pieceAt(col: Int, row: Int): Pieces? {
        for (piece in cellBox) {
            if (col == piece.col && row == piece.row) {
                return piece
            }
        }
        return null
    }

    fun piecePicture(col: Int, row: Int): Int?{
        val piece = pieceAt(col, row)
        val white = piece?.player == Player.WHITE

        if(piece != null){
            when(piece.rank){
                Rank.ROOK -> {
                    return if (white) R.drawable.w_rook
                    else R.drawable.b_rook
                }
                Rank.BISHOP -> {
                    return if (white) R.drawable.w_bishop
                    else R.drawable.b_bishop
                }
                Rank.PAWN -> {
                    return if (white)R.drawable.w_pawn
                    else  R.drawable.b_pawn
                }
                Rank.KING -> {
                    return if (white)  R.drawable.w_king
                    else R.drawable.b_king
                }
                Rank.QUEEN -> {
                    return if (white) R.drawable.w_queen
                    else  R.drawable.b_queen
                }
                Rank.KNIGHT -> {
                    return if (white)  R.drawable.w_knight
                    else  R.drawable.b_knight
                }
            }
        }
        return null
    }

    override fun toString(): String {
        var desc = " \n"
        for (row in 8 downTo 1) {
            desc += "$row"
            for (col in 1..8) {
                val piece = pieceAt(col, row)
                if (piece == null) {
                    desc += " ."
                } else {
                    val white = piece.player == Player.WHITE
                    desc += " "
                    desc += when (piece.rank) {
                        Rank.ROOK -> {
                            if (white) "r" else "R"
                        }
                        Rank.BISHOP -> {
                            if (white) "b" else "B"
                        }
                        Rank.PAWN -> {
                            if (white) "p" else "P"
                        }
                        Rank.KING -> {
                            if (white) "k" else "K"
                        }
                        Rank.QUEEN -> {
                            if (white) "q" else "Q"
                        }
                        Rank.KNIGHT -> {
                            if (white) "n" else "N"
                        }
                    }
                }
            }
            desc += " \n"
        }
        desc += "  1 2 3 4 5 6 7 8"
        return desc
    }
}