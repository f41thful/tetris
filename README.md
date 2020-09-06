# Extendable tetris
Tetris game that you can extend with new pieces.

## Compilation
mvn install

## Execution
You can execute it from the terminal or directly from IntelliJ

### From terminal
**java -cp tetris2-1.0-SNAPSHOT-jar-with-dependencies.jar:. com.software_engineering_professor.main.TetrisEntrypoint width height**
width and height, both as a package, are optional parameters (0 or 2 parameters can be passed but not 1).

## How to use it
In order to move the piece, just use the arrow keys (left, right and down).
To rotate, you can use any character key.

## Add new pieces
In order to add new pieces just create a new directory called "extra-pieces" and put them there under the name you want. 
Each line in the file is a line in the piece. Each column is a column.

### Example
 
 Current dir
  * jar
  * extra-pieces
    * piece-0.txt
  
 And the contents of piece-0 could be
 
 x     x
 
 x x   x
 
 x   x x
 
 x     x
 
 
 A piece with 4 rows and 7 columns (there are spaces between the 'x's).
