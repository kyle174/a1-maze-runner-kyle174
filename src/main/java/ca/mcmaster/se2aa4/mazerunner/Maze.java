package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Maze {

    private static final Logger logger = LogManager.getLogger();
    private final int[][] maze;

    public Maze(String file) {
        MazeGenerator mazeGen = new MazeGenerator(file);
        this.maze = mazeGen.loadMaze();
    }

    public void processPath(String path) {
        if (path.isEmpty()) {
            System.out.println("Path: "+findPath());
        }
        else {
            if (verifyPath(path)) {
                System.out.println(path+" is a valid path!");
            }
            else {
                System.out.println(path+" is NOT a valid path!");
            }
        }
    }

    private boolean verifyPath(String path) {
        Path pathVerifier = new Path(path, this.maze);
        return pathVerifier.verifyPath();
    }

    public String findPath() {
        MazeRunner runner = new MVP();
        return runner.calcPath(this.maze);
    }


}
