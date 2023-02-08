package com.example.chess.chess_board

data class Pieces(
    val col: Int,
    val row: Int,
    val rank: Rank,
    val player: Player
)

enum class Rank {
    ROOK,
    BISHOP,
    PAWN,
    KING,
    QUEEN,
    KNIGHT
}

enum class Player{
    WHITE,
    BLACK
}

