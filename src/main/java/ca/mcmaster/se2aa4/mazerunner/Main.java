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
            Maze theMaze = new Maze(config.file);
            theMaze.processPath(config.path);
        } catch(Exception e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
        logger.info("** End of MazeRunner");
    }

    private static Configuration configure(String[] args) throws ParseException {
        Options options = new Options();
        Option input = new Option("i", "input", true, "Name of Input File");
        options.addOption(input);
        options.addOption("p", true, "Path to Verify");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String fileName = cmd.getOptionValue(input,"./examples/small.maz.txt");
        String inputPath = cmd.getOptionValue("p","");
        logger.info("**** Reading the maze from file " + fileName);
        logger.info("**** Path to verify " + inputPath);
        return new Configuration(fileName,inputPath);
    }

    private record Configuration(String file, String path) {
        Configuration {
        }
    }
}