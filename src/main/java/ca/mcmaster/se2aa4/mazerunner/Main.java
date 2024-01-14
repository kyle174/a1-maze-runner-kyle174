package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        if (args[0].equals("-i") || args[0].equals("--input")) {
            logger.info("** Starting Maze Runner");
            try {
                logger.info("**** Reading the maze from file " + args[1]);
                BufferedReader reader = new BufferedReader(new FileReader(args[1]));
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
                logger.error("/!\\ An error has occurred /!\\");
            }
            logger.info("**** Computing path");
            logger.error("PATH NOT COMPUTED");
            logger.info("** End of MazeRunner");
        }
        else {
            logger.error("MISSING INPUT FLAG");
        }
    }
}
