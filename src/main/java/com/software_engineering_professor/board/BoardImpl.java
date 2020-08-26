package com.software_engineering_professor.board;

import com.software_engineering_professor.piece.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BoardImpl implements Board{
    private Collection<Piece> pieces;
    private Collection<Piece> unmodifiablePieces;
    private int width;
    private int height;

    public BoardImpl(int width, int height) {
        if(width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be greater than 0.");
        }

        this.width = width;
        this.height = height;
        pieces = new ArrayList<>();
        unmodifiablePieces = Collections.unmodifiableCollection(pieces);
    }

    @Override
    public void addPiece(Piece p) {
        if(p.getHeight() > height || p.getWidth() > width) {
            throw new IllegalArgumentException("The piece dimensions cannot be greater than board dimensions (" + width + ", " + height + ")");
        }
        pieces.add(p);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Collection<Piece> getPieces() {
        return unmodifiablePieces;
    }
}
