package com.software_engineering_professor;

import com.software_engineering_professor.engine.controller.MoveDownController;
import org.junit.jupiter.api.Test;

class TetrisGameTest {
    @Test
    public void tetrisGameCanBeCreated() {
        new TetrisGame(10, 10, new MoveDownController(1));
    }
}