package com.software_engineering_professor;

import org.junit.jupiter.api.Test;

class TetrisGameTest {
    @Test
    public void tetrisGameCanBeCreated() {
        new TetrisGame(10, 10);
    }
}