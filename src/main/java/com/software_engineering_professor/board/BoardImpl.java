package com.software_engineering_professor.board;

import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.piece.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BoardImpl implements Board{
    private Collection<Piece> pieces;
    private int width;
    private int height;
    private Point upperLeftPoint = new Point(0, 0);
    private BoardLineCompletion boardLineCompletion;

    public BoardImpl(int width, int height) {
        if(width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be greater than 0.");
        }

        this.width = width;
        this.height = height;
        pieces = new ArrayList<>();
        boardLineCompletion = new BoardLineCompletion();
    }

    @Override
    public void addPiece(Piece p) {
        if(p.getHeight() > height || p.getWidth() > width) {
            throw new IllegalArgumentException("The piece dimensions cannot be greater than board dimensions (" + width + ", " + height + ")");
        }
        pieces.add(p);
    }

    @Override
    public Point getUpperLeftPoint() {
        return upperLeftPoint;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Collection<Point> getOccupiedPoints(Piece p) {
        return Collections.unmodifiableCollection(
            pieces.stream()
                  .filter(bp -> bp != p)
                  .flatMap(bp -> bp.getGlobalPoints().stream())
                  .collect(Collectors.toList()));
    }

    public Collection<Integer> detectAndDeleteCompletedLines() {
        Collection<Integer> completedLines = boardLineCompletion.getCompletedLines(this);
        deleteCompletedLines(completedLines);

        return completedLines;
    }

    @Override
    public Collection<Piece> getPieces() {
        return Collections.unmodifiableCollection(pieces);
    }

    // The order will be based on position. Meaning, a piece closer to the bottom is considered to have more height.
    @Override
    public List<Piece> getPiecesAboveOrInLineOrderedByHeightDesc(int line) {
        return
                pieces.stream().filter(piece -> piece.isAboveOrInGlobalLine(line))
                        .sorted(Collections.reverseOrder(Comparator.comparingInt(piece -> piece.getPosition().y)))
                        .collect(Collectors.toList());
    }

    private void deleteCompletedLines(Collection<Integer> lines) {
        Objects.requireNonNull(lines);
        pieces.forEach(piece -> lines.forEach(piece::deleteGlobalLine));
    }
}
