package com.example.chess.chess_engine

data class EngineState(
    val toCol: Int? = null,
    val toRow: Int? = null,
    var piece: Pieces? = null,
)

data class BoardState(
    val board: ArrayList<Pieces> = arrayListOf(),
    val state: EngineState = EngineState()
)