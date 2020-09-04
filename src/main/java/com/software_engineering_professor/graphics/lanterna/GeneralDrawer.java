package com.software_engineering_professor.graphics.lanterna;

import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.piece.Piece;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class GeneralDrawer {
    private BoardDrawer boardDrawer;
    private PieceDrawer pieceDrawer;

    public GeneralDrawer(BoardDrawer boardDrawer, PieceDrawer pieceDrawer) {
        Objects.requireNonNull(boardDrawer);
        Objects.requireNonNull(pieceDrawer);
        this.boardDrawer = boardDrawer;
        this.pieceDrawer = pieceDrawer;
    }

    public Collection<DrawPoints> getDrawPoints(Board board) {
        return boardDrawer.getDrawPoints(board);
    }

    public Collection<DrawPoints> getDrawPoints(Piece p) {
        return Collections.singletonList(pieceDrawer.getDrawPoints(p));
    }
}
