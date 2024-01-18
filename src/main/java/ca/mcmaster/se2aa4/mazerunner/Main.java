package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.ParseException;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        try {
            Configuration config = configure(args);
            logger.info(config);
            Maze theMaze = new Maze(config.file());
            MazeRunner runner = new MazeRunner();
            runner.findPath(theMaze);
        } catch(Exception e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
        logger.info("** End of MazeRunner");
    }

    private static Configuration configure(String[] args) throws ParseException {
        Options options = new Options();
        Option input = new Option("i", "input", true, "Input File");
        options.addOption(input);
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String fileName = cmd.getOptionValue(input,"./examples/small.maz.txt");
        logger.info("**** Reading the maze from file " + fileName);
        return new Configuration(fileName);
    }

    private record Configuration(String file) {
        Configuration {
        }
    }
}