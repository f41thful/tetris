package com.software_engineering_professor.piece;

import com.software_engineering_professor.board.PositionValidation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class PieceLoader {
    private PositionValidation positionValidation;

    public PieceLoader(PositionValidation positionValidation) {
        Objects.requireNonNull(positionValidation);
        this.positionValidation = positionValidation;
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
        return getBuilder(0).add("xxxx").build();
    }

    private Piece piece1() {
        return getBuilder(1).add("x")
                            .add("xxx").build();
    }

    private Piece piece2() {
        return getBuilder(2).add("  x")
                            .add("xxx").build();
    }

    private Piece piece3() {
        return getBuilder(3).add("xx")
                            .add("xx").build();
    }

    private Piece piece4() {
        return getBuilder(4).add(" xx")
                            .add("xx ").build();
    }

    private Piece piece5() {
        return getBuilder(5).add(" x ")
                            .add("xxx").build();
    }

    private Piece piece6() {
        return getBuilder(6).add("xx ")
                            .add(" xx").build();
    }

    private PieceBuilder getBuilder(int type) {
        return PieceBuilder.create(type).positionValidation(positionValidation);
    }
}
