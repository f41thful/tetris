package com.software_engineering_professor.piece;

import java.util.Arrays;
import java.util.Collection;

public class PieceLoader {
    private PieceBuilder builder;

    public PieceLoader() {
        builder = new PieceBuilder();
    }

    public Collection<Piece> getDefaultPieces() {
        return Arrays.asList(
            piece0(),
            piece1(),
            piece2(),
            piece3(),
            piece4(),
            piece5(),
            piece6()
        );
    }

    private Piece piece0() {
        return builder.add("xxxx").build();
    }

    private Piece piece1() {
        return builder.add("x")
                      .add("xxx").build();
    }

    private Piece piece2() {
        return builder.add("  x")
                      .add("xxx").build();
    }

    private Piece piece3() {
        return builder.add("xx")
                      .add("xx").build();
    }

    private Piece piece4() {
        return builder.add(" xx")
                      .add("xx ").build();
    }

    private Piece piece5() {
        return builder.add(" x ")
                      .add("xxx").build();
    }

    private Piece piece6() {
        return builder.add("xx ")
                      .add(" xx").build();
    }
}
