package com.software_engineering_professor.graphics.lanterna;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.software_engineering_professor.geom.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ScreenManager {
    private Screen screen;
    private List<DrawPoints> toDraw;

    public ScreenManager() throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

        Terminal terminal = defaultTerminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);

        toDraw = new ArrayList<>();
    }

    public void add(Collection<DrawPoints> points) {
        toDraw.addAll(points);
    }

    public void add(DrawPoints point) {
        toDraw.add(point);
    }

    public void draw() throws IOException {
        screen.clear();
        for(DrawPoints dp : toDraw) {
            for(Point p : dp.points) {
                screen.setCharacter(p.x, p.y, dp.character);
            }
        }
        screen.refresh();
        toDraw.clear();
    }

}
