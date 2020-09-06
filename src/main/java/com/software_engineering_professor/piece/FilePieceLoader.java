package com.software_engineering_professor.piece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class FilePieceLoader {
    private static final String DEFAULT_PATH = "/extra-pieces/";

    private final String path;

    public FilePieceLoader() {
        this(DEFAULT_PATH);
    }

    public FilePieceLoader(String path) {
        Objects.requireNonNull(path);
        this.path = path;
    }

    public Collection<Piece> getPieces(int nextType) {
        if(nextType < 0) {
            throw new IllegalArgumentException("Type must be >= 0. It was " + nextType + ".");
        }

        try {
            List<String> fileNames = getPieceFileNames();
            return createPieces(nextType, fileNames);
        } catch(Exception e) {
            System.out.println("Error trying to read " + path + ". Just skip it.");
        }

        return new ArrayList<>();
    }

    private Collection<Piece> createPieces(int nextType, Collection<String> fileNames) throws IOException {
        Collection<Piece> pieces = new ArrayList<>();
        for(String fileName : fileNames) {
            if(fileName == null) {continue;}

            pieces.add(createPiece(nextType, fileName));
            nextType++;
        }

        return pieces;
    }

    private Piece createPiece(int nextType, String fileName) throws IOException {
        List<String> lines = readLines(absPath(fileName));
        PieceBuilder builder = PieceBuilder.create(nextType);
        lines.stream().forEach(builder::add);

        return builder.build();
    }

    private String absPath(String fileName) {
        return path + fileName;
    }

    private List<String> getPieceFileNames() throws IOException {
        return readLines(this.path);
    }

    private List<String> readLines(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        InputStream in = getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String resource;

        while ((resource = br.readLine()) != null) {
            filenames.add(resource);
        }

        return filenames;
    }

    private InputStream getResourceAsStream(String resource) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
