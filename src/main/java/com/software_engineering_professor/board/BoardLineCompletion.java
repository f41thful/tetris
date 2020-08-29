package com.software_engineering_professor.board;

import com.software_engineering_professor.geom.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BoardLineCompletion {
    private LineCompletion lineCompletion;

    public BoardLineCompletion(LineCompletion lineCompletion) {
        Objects.requireNonNull(lineCompletion);
        this.lineCompletion = lineCompletion;
    }

    public Collection<Integer> getCompletedLines(Board board) {
        Objects.requireNonNull(board);
        int boardWidth = board.getWidth();

        Map<Integer, Collection<Point>> pointsByLine = groupedByLine(board.getOccupiedPoints(null));
        List<Integer> completedLines = new ArrayList<>();

        for(Map.Entry<Integer, Collection<Point>> entry : pointsByLine.entrySet()) {
            if (lineCompletion.isComplete(boardWidth, entry.getValue())) {
                completedLines.add(entry.getKey());
            }
        }

        return completedLines;
    }

    private Map<Integer, Collection<Point>> groupedByLine(Collection<Point> occupiedPoints) {
        return
        occupiedPoints.stream().collect(Collectors.groupingBy(p -> p.y));
    }
}
