package com.example.chess.chess_engine

sealed class EngineEvent {
    data class ChoosePiece(val piece: Pieces): EngineEvent()
    data class MoveTo(val col: Int, val row: Int): EngineEvent()
}