package com.software_engineering_professor.main;

import com.software_engineering_professor.TetrisPackage;

import java.io.IOException;

public class TetrisEntrypoint {
    private static final int DEFAULT_BOARD_SIZE = 30;

    private int width = DEFAULT_BOARD_SIZE;
    private int height = DEFAULT_BOARD_SIZE;

    public static void main(String[] args) throws IOException, InterruptedException {
        TetrisEntrypoint entrypoint = new TetrisEntrypoint();
        entrypoint.parseParams(args);
        new TetrisPackage(entrypoint.width, entrypoint.height).start();
    }

    private void parseParams(String[] args) {
        if(args.length == 2) {
            width = Integer.valueOf(args[0]);
            height = Integer.valueOf(args[1]);
        } else if(args.length == 1 || args.length > 2) {
            printHelp();
            System.exit(-1);
        }
    }

    private void printHelp() {
        System.out.println("You are using the program wrong. The right ways are:");
        System.out.println(" * tetris");
        System.out.println(" * tetris <width> <height>");
    }
}
