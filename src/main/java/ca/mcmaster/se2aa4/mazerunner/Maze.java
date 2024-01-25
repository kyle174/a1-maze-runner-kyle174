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
                break;
            }
        }
        return entry;
    }

    private int findExit() {
        int exit = -1;
        for(int i=0; i<this.maze.length; i++) {
            if (this.maze[i][this.maze[0].length-1] == 1) {
                exit = i;
                break;
            }
        }
        return exit;
    }

    public String processPath(String inputPath) {
        MazeRunner runner = new RightHand(this.maze, entry, exit);
        if (inputPath.isEmpty()) {
            String path = runner.calcPath();
            return("Path: "+path);
        }
        else {
            Path mazePath = new Path(inputPath);
            String fixedPath = mazePath.expandPath();
            if (runner.verifyPath(fixedPath) && !fixedPath.isEmpty()) {
                return(inputPath+" is a valid path!");
            }
            else {
                return(inputPath+" is NOT a valid path!");
            }
        }
    }
}
