package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MVP implements MazeRunner {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String calcPath(int[][] theMaze) {
        logger.info("**** Computing path");
        return "4F";
    }


}
