package com.software_engineering_professor.graphics.lanterna;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.graphics.lanterna.drawer.DrawPoints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ScreenManager {
    private Screen screen;
    private List<DrawPoints> toDraw;
    private Terminal terminal;

    public ScreenManager(int width, int height) throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(width, height));

        terminal = defaultTerminalFactory.createTerminal();
        closeOnExitInCaseOfASwingTerminal(terminal);
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

    public KeyStroke pollInput() throws IOException {
        return terminal.pollInput();
    }

    public KeyStroke readInput() throws IOException {
        return terminal.readInput();
    }

    private void closeOnExitInCaseOfASwingTerminal(Terminal terminal) {
        if(terminal instanceof SwingTerminalFrame) {
            ((SwingTerminalFrame) terminal).setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
}
