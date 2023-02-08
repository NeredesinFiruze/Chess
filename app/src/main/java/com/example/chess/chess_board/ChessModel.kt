package com.example.chess.chess_board

class ChessModel {

    private var pieceBox = mutableSetOf<Pieces>()

    init {
        reset()
    }
    private fun reset(){
        pieceBox.removeAll(pieceBox)
        for (i in 0..7){
            pieceBox.add(Pieces(i , 1,Rank.PAWN,Player.WHITE))
            pieceBox.add(Pieces(i , 6,Rank.PAWN,Player.BLACK))
        }
        for (i in 0..1){
            pieceBox.add(Pieces(i * 1 * 7 , 0,Rank.ROOK,Player.WHITE))
            pieceBox.add(Pieces(i * 1 * 7 , 7,Rank.ROOK,Player.BLACK))

            pieceBox.add(Pieces(i * 5 + 1  , 0,Rank.KNIGHT,Player.WHITE))
            pieceBox.add(Pieces(i * 5 + 1  , 7,Rank.KNIGHT,Player.BLACK))

            pieceBox.add(Pieces(i * 3 + 2 , 0,Rank.BISHOP,Player.WHITE))
            pieceBox.add(Pieces(i * 3 + 2 , 7,Rank.BISHOP,Player.BLACK))
        }
        pieceBox.add(Pieces(3, 0,Rank.QUEEN,Player.WHITE))
        pieceBox.add(Pieces(3, 7,Rank.QUEEN,Player.BLACK))

        pieceBox.add(Pieces(4, 0,Rank.KING,Player.WHITE))
        pieceBox.add(Pieces(4, 7,Rank.KING,Player.BLACK))
    }

    private fun pieceAt(col: Int, row: Int): Pieces?{
        for (piece in pieceBox){
            if (col == piece.col && row == piece.row){
                return piece
            }
        }
        return null
    }

    override fun toString(): String {
        var desc = " \n"
        for (row in 7 downTo 0 ){
            desc += "$row"
            for (col in 0..7){
                val piece = pieceAt(col,row)
                if (piece == null){
                    desc += " ."
                }else{
                    val white = piece.player == Player.WHITE
                    desc += " "
                    desc += when(piece.rank){
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
        desc += "  0 1 2 3 4 5 6 7"
        return desc
    }
}