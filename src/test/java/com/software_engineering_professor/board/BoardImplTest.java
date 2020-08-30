package com.software_engineering_professor.board;

import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.piece.Piece;
import com.software_engineering_professor.piece.PieceBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardImplTest {
    private Board board;
    private Piece piece0;
    private Piece piece1;

    @BeforeEach
    public void setUp() {
        board = new BoardImpl(30, 30);
        board.addPiece(piece0());
        board.addPiece(piece1());
    }

    @Test
    public void givenPiecesBelowLine_thenReturnNothing() {
        assertTrue(board.getPiecesAboveOrInLineOrderedByHeightDesc(0).isEmpty());
    }

    @Test
    public void givenOnePieceInLine_thenReturnThat() {
        List<Piece> pieces = board.getPiecesAboveOrInLineOrderedByHeightDesc(1);
        assertEquals(1, pieces.size());
        assertTrue(pieces.contains(piece0));
    }

    @Test
    public void givenOneInAndTheTheOtherAbove_thenReturnBothInOrder() {
        List<Piece> pieces = board.getPiecesAboveOrInLineOrderedByHeightDesc(2);
        assertEquals(2, pieces.size());
        assertEquals(piece1, pieces.get(0));
        assertEquals(piece0, pieces.get(1));
    }

    @Test
    public void givenBothAbove_thenReturnBothInOrder() {
        List<Piece> pieces = board.getPiecesAboveOrInLineOrderedByHeightDesc(10);
        assertEquals(2, pieces.size());
        assertEquals(piece1, pieces.get(0));
        assertEquals(piece0, pieces.get(1));
    }

    private Piece piece0() {
        piece0 = PieceBuilder.create(0, new Point(0, 1))
                           .add("x")
                           .add("x")
                           .add("x")
                           .add("x")
                           .build();

        return piece0;
    }

    private Piece piece1() {
        piece1 = PieceBuilder.create(0, new Point(1, 2))
                           .add("x")
                           .add("x")
                           .add("x")
                           .add("x")
                           .build();
        return piece1;
    }
}