package com.software_engineering_professor.graphics.lanterna;

import com.software_engineering_professor.board.Board;
import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.graphics.lanterna.drawer.DrawerFactory;
import com.software_engineering_professor.graphics.lanterna.drawer.GeneralDrawer;

import java.io.IOException;
import java.util.Objects;

public class TetrisGUI {
    private ScreenManager screenManager;
    private GeneralDrawer generalDrawer;

    public TetrisGUI(Point origin) throws IOException {
        Objects.requireNonNull(origin);
        screenManager = new ScreenManager();
        generalDrawer = new DrawerFactory(origin).generalDrawer();
    }

    public void draw(Board board) throws IOException {
        screenManager.add(generalDrawer.getAllDrawPoints(board));
        screenManager.draw();
    }
}
