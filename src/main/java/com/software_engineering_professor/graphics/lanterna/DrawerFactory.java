package com.software_engineering_professor.graphics.lanterna;

import com.software_engineering_professor.geom.Point;

public class DrawerFactory {
    private Point origin;

    public DrawerFactory(Point origin) {
        this.origin = origin;
    }

    public BoardDrawer boardDrawer() {
        return new BoardDrawer(origin);
    }

    public PieceDrawer pieceDrawer() {
        return new PieceDrawer(origin);
    }
}
