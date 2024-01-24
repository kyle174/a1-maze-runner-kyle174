package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Maze {

    private static final Logger logger = LogManager.getLogger();
    private final int[][] maze;
    private final int entry;
    private final int exit;

    public Maze(String file) {
        MazeGenerator mazeGen = new MazeGenerator(file);
        this.maze = mazeGen.loadMaze();
        this.entry = findEntry();
        this.exit = findExit();
    }

    private int findEntry() {
        int entry = -1;
        for(int i=0; i<this.maze.length; i++) {
            if (this.maze[i][0] == 1) {
                entry = i;
            }
        }
        return entry;
    }

    private int findExit() {
        int exit = -1;
        for(int i=0; i<this.maze.length; i++) {
            if (this.maze[i][this.maze.length-1] == 1) {
                exit = i;
            }
        }
        return exit;
    }

    public void processPath(String path) {
        if (path.isEmpty()) {
            System.out.println("Path: "+findPath(this.entry, this.exit));
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

    public String findPath(int entry, int exit) {
        MazeRunner runner = new RightHand(this.maze, entry, exit);
        return runner.calcPath();
    }



}
