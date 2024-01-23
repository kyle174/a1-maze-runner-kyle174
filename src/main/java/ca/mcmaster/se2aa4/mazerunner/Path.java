package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Path {

    private String path;
    private int[][] maze;

    public Path(String path, int[][] theMaze) {
        this.path = path;
        this.maze = theMaze;
    }
    private static final Logger logger = LogManager.getLogger();

    public boolean verifyPath() {
        logger.info("**** Verifying path");
        return (this.path.equals("FFFF") || this.path.equals("4F"));
    }

    public String convertPath() {
        return "";
    }
}
