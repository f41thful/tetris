package com.software_engineering_professor.engine;

import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.piece.Piece;
import com.software_engineering_professor.piece.PieceStore;

import java.util.Objects;
import java.util.Random;

public class PieceGenerator {
    private PieceStore pieceStore;
    private Random random;
    private Point origin;

    public PieceGenerator(PieceStore pieceStore, Random random, Point origin) {
        Objects.requireNonNull(pieceStore);
        Objects.requireNonNull(random);
        Objects.requireNonNull(origin);

        this.pieceStore = pieceStore;
        this.random = random;
        this.origin = origin;
    }

    public Piece next() {
        Piece p = pieceStore.getPiece(random.nextInt(pieceStore.getNumberOfPieces()));
        p.setPosition(origin.clone());

        return p;
    }
}
