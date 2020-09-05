package com.software_engineering_professor.main;

import com.software_engineering_professor.TetrisPackage;

import java.io.IOException;

public class TetrisEntrypoint {
    public static void main(String[] args) throws IOException, InterruptedException {
        new TetrisPackage(40, 19).start();
    }
}
