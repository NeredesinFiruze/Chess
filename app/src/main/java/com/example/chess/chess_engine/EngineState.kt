package com.example.chess.chess_engine

data class EngineState(
    val toCol: Int? = null,
    val toRow: Int? = null,
    var piece: Pieces? = null,
)

data class BoardState(
    var board: ArrayList<Pieces> = arrayListOf(),
    var canMove: ArrayList<Int> = arrayListOf(),
    var moveList: ArrayList<String> = arrayListOf(),
    var turn: Player = Player.BLACK,
    val state: EngineState = EngineState()
)