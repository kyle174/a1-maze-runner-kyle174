package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class MazeGenerator {
    private static final Logger logger = LogManager.getLogger();
    private final String file;
    private final int rows;
    private final int cols;

    public MazeGenerator(String file) {
        this.file = file;
        this.rows = this.getSize()[0];
        this.cols = this.getSize()[1];
    }

    public int[][] loadMaze() {
        int[][] maze = new int[this.rows][this.cols];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String line;
            for(int i=0; i<this.rows; i++) {
                line = reader.readLine();
                for(int j=0; j<this.cols; j++) {
                    if (line.charAt(j) == '#') {
                        maze[i][j] = 0;
                    } else if (line.charAt(j) == ' ') {
                        maze[i][j] = 1;
                    }
                }
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occurred /!\\: "+e.getMessage());
            System.exit(1);
        }
        return maze;
    }

    private int[] getSize() {
        int rows=0;
        int cols=0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String line;
            while ((line = reader.readLine()) != null) {
                rows += 1;
                cols = line.length();
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occurred /!\\: "+e.getMessage());
            System.exit(1);
        }
        return new int[]{rows, cols};
    }
}
