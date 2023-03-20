package com.example.chess.chess_engine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chess.R
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ChessModel: ViewModel() {

    private val _state = MutableStateFlow(EngineState())
    private val _boardState = MutableStateFlow(BoardState())
    val boardState = combine(_state,_boardState){state, boardState->
        boardState.copy(
            state = state
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), BoardState())

    init {
        startPosition()
    }


    fun onEvent(event: EngineEvent) {
        when (event) {
            is EngineEvent.ChoosePiece -> {
                _state.value = _state.value.copy(
                    piece = event.piece
                )
            }
            is EngineEvent.MoveTo -> {
                if (_state.value.piece != null) {
                    viewModelScope.launch {

                        val piece = _state.value.piece!!

                        _state.value = _state.value.copy(
                            toCol = event.col,
                            toRow = event.row
                        )

                        _boardState.collect { setBoard ->
                            setBoard.board.remove(_state.value.piece)
                            setBoard.board.add(
                                Pieces(
                                    _state.value.toCol!!,
                                    _state.value.toRow!!,
                                    piece.rank,
                                    piece.player
                                )
                            )
                            _state.value = _state.value.copy(
                                piece = null
                            )
                        }
                    }

                } else return
            }
        }
    }

    private fun startPosition() {
        val setToBoard = _boardState.value.board
        setToBoard.removeAll(_boardState.value.board.toSet())
        for (i in 1..8) {
            setToBoard.add(Pieces(2, i, Rank.PAWN, Player.WHITE))
            setToBoard.add(Pieces(7, i, Rank.PAWN, Player.BLACK))
        }
        for (i in 0..1) {
            setToBoard.add(Pieces(1, if (i * 8 == 0) 1 else i * 8, Rank.ROOK, Player.WHITE))
            setToBoard.add(Pieces(8, if (i * 8 == 0) 1 else i * 8, Rank.ROOK, Player.BLACK))

            setToBoard.add(Pieces(1, i * 5 + 2, Rank.KNIGHT, Player.WHITE))
            setToBoard.add(Pieces(8, i * 5 + 2, Rank.KNIGHT, Player.BLACK))

            setToBoard.add(Pieces(1, i * 3 + 3, Rank.BISHOP, Player.WHITE))
            setToBoard.add(Pieces(8, i * 3 + 3, Rank.BISHOP, Player.BLACK))
        }
        setToBoard.add(Pieces(1, 4, Rank.QUEEN, Player.WHITE))
        setToBoard.add(Pieces(8, 4, Rank.QUEEN, Player.BLACK))

        setToBoard.add(Pieces(1, 5, Rank.KING, Player.WHITE))
        setToBoard.add(Pieces(8, 5, Rank.KING, Player.BLACK))
    }

    fun pieceAt(col: Int, row: Int): Pieces? {
        for (piece in _boardState.value.board) {
            if (col == piece.col && row == piece.row) {
                return piece
            }
        }
        return null
    }


    fun piecePicture(col: Int, row: Int): Int? {
        val piece = pieceAt(col, row)
        val white = piece?.player == Player.WHITE

        if (piece != null) {
            when (piece.rank) {
                Rank.ROOK -> {
                    return if (white) R.drawable.w_rook
                    else R.drawable.b_rook
                }
                Rank.BISHOP -> {
                    return if (white) R.drawable.w_bishop
                    else R.drawable.b_bishop
                }
                Rank.PAWN -> {
                    return if (white) R.drawable.w_pawn
                    else R.drawable.b_pawn
                }
                Rank.KING -> {
                    return if (white) R.drawable.w_king
                    else R.drawable.b_king
                }
                Rank.QUEEN -> {
                    return if (white) R.drawable.w_queen
                    else R.drawable.b_queen
                }
                Rank.KNIGHT -> {
                    return if (white) R.drawable.w_knight
                    else R.drawable.b_knight
                }
            }
        }
        return null
    }
}