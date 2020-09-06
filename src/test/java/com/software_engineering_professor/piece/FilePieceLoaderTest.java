package com.software_engineering_professor.piece;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilePieceLoaderTest {
    @Test
    public void givenDirDoesNotExist_thenNoExtraPieces() {
        Collection<Piece> pieces = new FilePieceLoader("adsfdfjskjfskdj").getPieces(0);
        assertEquals(0, pieces.size());
    }

    @Test
    public void givenDirHasNoFiles_thenNoExtraPieces() {
        Collection<Piece> pieces = new FilePieceLoader("/extra-pieces-empty/").getPieces(0);
        assertEquals(0, pieces.size());
    }

    @Test
    public void givenDirHasValidFiles_thenReturnThosePieces() {
        Collection<Piece> expected = Arrays.asList(extraPiece0(), piece0(), piece0Txt(), piece1());
        Collection<Piece> pieces = new FilePieceLoader("/extra-pieces-empty/").getPieces(0);
        assertEquals(4, pieces.size());
        expected.forEach(p -> assertTrue(contains(pieces, p)));
    }

    private boolean contains(Collection<Piece> pieces, Piece p) {
        for(Piece ip : pieces) {
            if(pieceEquals(ip, p)) {
                return true;
            }
        }

        return false;
    }

    private boolean pieceEquals(Piece p0, Piece p1) {
        if(p0 == p1) {return true;}
        if(p0 == null || p1 == null) {return false;}
        return p0.getLocalPoints().equals(p1.getLocalPoints());
    }

    private Piece extraPiece0() {
        return PieceBuilder.create(0)
        .add("xxxxxxxxxx")
        .add("xxxx")
        .add("xxxxxxxxxx").build();
    }

    private Piece piece0() {
        return PieceBuilder.create(0)
        .add("x     x")
        .add("x x   x")
        .add("x   x x")
        .add("x     x")
        .build();
    }

    private Piece piece0Txt() {
        return PieceBuilder.create(0)
        .add("xxxxxxxxxxxxxx")
        .add("x")
        .add("x")
        .add("x")
        .add("x")
        .add("x")
        .add("x")
        .add("x")
        .add("x")
        .add("x")
        .add("x")
        .build();
    }

    private Piece piece1() {
        return PieceBuilder.create(0)
        .add("xxxxxxxxxxxxxxxxxxxxxx")
        .add("xxxxx xxxxxxxxxxxxxxxx")
        .add("xxxxx xxxxxxxxxxxxxxxx")
        .add("xxxxx x x x xxxxxxxxxx")
        .add("xxxxxxxxx xx xx xxxxxx")
        .add("xxxxxxxxxxxxxxxxxxxxxx")
                .build();
    }
}