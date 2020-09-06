package com.software_engineering_professor.piece;

import com.software_engineering_professor.board.PositionValidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class PieceLoader {
    private PositionValidation positionValidation;
    private Collection<Piece> pieces;
    private FilePieceLoader filePieceLoader;

    public PieceLoader(PositionValidation positionValidation) {
        Objects.requireNonNull(positionValidation);
        this.positionValidation = positionValidation;
        filePieceLoader = new FilePieceLoader();
    }

    public Collection<Piece> getPieces() {
        if(pieces == null) {
            pieces = getDefaultPieces();
            pieces.addAll(filePieceLoader.getPieces(getNextAvailableType()));
        } else {
            System.out.println("Pieces not loaded again.");
        }

        return pieces;
    }

    public Collection<Piece> getDefaultPieces() {
        return new ArrayList<>(Arrays.asList(
                piece0(),
                piece1(),
                piece2(),
                piece3(),
                piece4(),
                piece5(),
                piece6()
        ));
    }

    public int getMaxWidth() {
        return getPieces().stream().mapToInt(Piece::getWidth).max().getAsInt();
    }

    private int getNextAvailableType() {
        return getPieces().stream().mapToInt(Piece::getType).max().getAsInt() + 1;
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
