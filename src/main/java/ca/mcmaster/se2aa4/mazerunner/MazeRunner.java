package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeRunner {

    private static final Logger logger = LogManager.getLogger();
    public void findPath(Maze theMaze) {
        logger.info("**** Computing path");
        logger.error("PATH NOT COMPUTED");
    }

    public boolean verifyPath(String path) {
        logger.info("**** Verifying path");
        logger.error("PATH NOT VALID");
        return false;
    }
}
