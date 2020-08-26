package com.software_engineering_professor.board;

import com.software_engineering_professor.piece.Piece;

public interface Board {
    void addPiece(Piece p);
    int getWidth();
    int getHeight();
}
