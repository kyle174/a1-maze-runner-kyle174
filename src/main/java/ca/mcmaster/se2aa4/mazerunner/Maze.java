package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;

public class Maze {

    private static final Logger logger = LogManager.getLogger();
    private final String[][] maze;

    public Maze(String file) {
        this.maze = loadMaze(file);
    }

    private String[][] loadMaze(String file) {
        String[][] maze = { {}, {} };
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occurred (file not found) /!\\");
            System.exit(1);
        }
        return maze;
    }

    public void processPath(String path) {
        if (path.isEmpty()) {
            findPath();
        }
        else {
            verifyPath(path);
        }
    }

    private boolean verifyPath(String path) {
        Path pathVerifier = new Path(path, this.maze);
        return pathVerifier.verifyPath();
    }

    public String findPath() {
        MazeRunner runner = new MazeRunner();
        return runner.calcPath(this.maze);
    }
}
