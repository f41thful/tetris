package com.software_engineering_professor;

import com.software_engineering_professor.engine.controller.InputController;
import com.software_engineering_professor.engine.iteration_listener.IterationListener;
import com.software_engineering_professor.geom.Point;
import com.software_engineering_professor.graphics.lanterna.TetrisGUI;

import java.io.IOException;

public class TetrisPackage {
    private static final Point GUI_ORIGIN = new Point(3, 3);

    private TetrisGame tetrisGame;
    private TetrisGUI tetrisGUI;

    public TetrisPackage(int width, int height) throws IOException {
        tetrisGUI = new TetrisGUI(GUI_ORIGIN);

        InputController inputController = new InputController();
        tetrisGame = new TetrisGame(width, height, inputController);

        tetrisGame.addIterationListener(getDrawIterationListener());
    }

    public void start() throws InterruptedException {
        tetrisGame.start();
    }

    private IterationListener getDrawIterationListener() {
        return new IterationListener() {
            @Override
            public void start(int iteration) {

            }

            @Override
            public void finish(int iteration) {
                try {
                    tetrisGUI.draw(tetrisGame.getBoard());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
