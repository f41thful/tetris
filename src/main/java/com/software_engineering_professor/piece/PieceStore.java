package com.software_engineering_professor.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PieceStore {
    private List<Piece> pieces;

    public PieceStore() {
        pieces = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        Objects.requireNonNull(piece);
        pieces.add(piece);
    }

    public int getNumberOfPieces() {
        return pieces.size();
    }

    public Piece getPiece(int number) {
        return pieces.get(number).clone();
    }
}
