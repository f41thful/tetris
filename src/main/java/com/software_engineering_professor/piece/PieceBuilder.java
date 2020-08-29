package com.software_engineering_professor.piece;

import com.software_engineering_professor.board.PositionValidation;
import com.software_engineering_professor.geom.Point;

import java.util.ArrayList;
import java.util.Collection;

public class PieceBuilder {
    private static final char OCCUPIED = 'x';
    private static final Point DEFAULT_POSITION = new Point(0, 0);

    private int type;
    private Collection<Point> points;
    private int y;
    private Point position;
    private PositionValidation positionValidation;


    public static PieceBuilder create(int type) {
        return create(type, DEFAULT_POSITION);
    }

    public static PieceBuilder create(int type, Point position) {
        if(type < 0) {
            throw new IllegalArgumentException("Type must be > 0.");
        }

        PieceBuilder pieceBuilder = new PieceBuilder();
        pieceBuilder.type = type;

        pieceBuilder.y = 0;
        pieceBuilder.points = new ArrayList<>();

        if(position == null) {
            position = DEFAULT_POSITION;
        }

        pieceBuilder.position = position.clone();

        return pieceBuilder;
    }

    /*
        'x' means occupied, any other thing means empty.
        no empty pieces allowed.
     */
    public PieceBuilder add(String dsl) {
        if (dsl == null || dsl.trim().isEmpty()) {
            throw new IllegalArgumentException("The definition of a piece cannot be empty.");
        }

        dsl = dsl.toLowerCase();
        for (int i = 0; i < dsl.length(); i++) {
            if (dsl.charAt(i) == OCCUPIED) {
                points.add(new Point(i, y));
            }
        }

        y++;
        return this;
    }

    public PieceBuilder positionValidation(PositionValidation positionValidation) {
        this.positionValidation = positionValidation;

        return this;
    }

    public Piece build() {
        if(positionValidation == null) {
            positionValidation = new PositionValidation(null){
                public boolean isValid(Piece p, Collection<Point> points) {
                    System.out.println("Using always valid PositionValidation.");
                    return true;
                }
            };
        }

        return new Piece(type, position, points, positionValidation);
    }
}
