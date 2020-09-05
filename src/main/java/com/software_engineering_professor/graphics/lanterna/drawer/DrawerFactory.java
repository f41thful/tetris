package com.software_engineering_professor.graphics.lanterna.drawer;

import com.software_engineering_professor.geom.Point;

public class DrawerFactory {
    private Point origin;

    public DrawerFactory(Point origin) {
        this.origin = origin;
    }

    public GeneralDrawer generalDrawer() {
        return new GeneralDrawer(boardDrawer(), pieceDrawer());
    }

    private BoardDrawer boardDrawer() {
        return new BoardDrawer(origin);
    }

    private PieceDrawer pieceDrawer() {
        return new PieceDrawer(origin);
    }
}
