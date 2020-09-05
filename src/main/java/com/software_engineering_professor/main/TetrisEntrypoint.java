package com.software_engineering_professor.main;

import com.software_engineering_professor.TetrisPackage;

import java.io.IOException;

public class TetrisEntrypoint {
    private static final int DEFAULT_BOARD_SIZE = 30;

    public static void main(String[] args) throws IOException, InterruptedException {
        int width = DEFAULT_BOARD_SIZE;
        int height = DEFAULT_BOARD_SIZE;

        if(args.length == 2) {
            width = Integer.valueOf(args[0]);
            height = Integer.valueOf(args[1]);
        } else if(args.length == 1 || args.length > 2) {
            TetrisEntrypoint.printHelp();
            System.exit(-1);
        }
        new TetrisPackage(width, height).start();
    }

    private static void printHelp() {
        System.out.println("You are using the program wrong. The right ways are:");
        System.out.println(" * tetris");
        System.out.println(" * tetris <width> <height>");
    }
}
