package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MVP implements MazeRunner {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean verifyPath(String path) {
        return path.equals("FFFF");
    }

    @Override
    public String calcPath() {
        logger.info("**** Computing path");
        return "4F";
    }


}
