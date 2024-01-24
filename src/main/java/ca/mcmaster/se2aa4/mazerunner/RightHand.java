package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHand implements MazeRunner {
    private static final Logger logger = LogManager.getLogger();

    private final int[][] maze;
    private final int entry;
    private final int exit;

    enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
    private Direction curDirection = Direction.EAST;

    public RightHand(int[][] maze, int entry, int exit) {
        this.maze = maze;
        this.entry = entry;
        this.exit = exit;
    }

    @Override
    public String calcPath() {
        logger.info("**** Computing path");
        int curRow = this.entry;
        int curCol = 0;
        int colSize = this.maze[0].length-1;
        String result = "";
        String prevMove = "";
        while (!(curRow == exit && curCol == colSize)) {
            if (checkRight(curRow,curCol) && !prevMove.equals("R")) {
                changeDirec("R");
                result+="R";
            }
            else if (checkFront(curRow, curCol)) {
                switch(curDirection) {
                    case NORTH:
                        curRow-=1;
                        break;
                    case EAST:
                        curCol+=1;
                        break;
                    case SOUTH:
                        curRow+=1;
                        break;
                    case WEST:
                        curCol-=1;
                        break;
                }
                result+="F";
            }
            else if (checkLeft(curRow, curCol)) {
                changeDirec("L");
                result+="L";
            }
            else {
                changeDirec("L");
                changeDirec("L");
                result+="LL";
            }
            prevMove = result.substring(result.length()-1);
        }
        return result;
    }

    private boolean checkRight(int curRow, int curCol) {
        switch(curDirection) {
            case NORTH:
                return(this.maze[curRow][curCol+1] == 1);
            case EAST:
                return(this.maze[curRow+1][curCol] == 1);
            case SOUTH:
                return(this.maze[curRow][curCol-1] == 1);
            case WEST:
                return(this.maze[curRow-1][curCol] == 1);
            default:
                return false;
        }
    }

    private boolean checkFront(int curRow, int curCol) {
        switch(curDirection) {
            case NORTH:
                return(this.maze[curRow-1][curCol] == 1);
            case EAST:
                return(this.maze[curRow][curCol+1] == 1);
            case SOUTH:
                return(this.maze[curRow+1][curCol] == 1);
            case WEST:
                return(this.maze[curRow][curCol-1] == 1);
            default:
                return false;
        }
    }

    private boolean checkLeft(int curRow, int curCol) {
        switch(curDirection) {
            case NORTH:
                return(this.maze[curRow][curCol-1] == 1);
            case EAST:
                return(this.maze[curRow-1][curCol] == 1);
            case SOUTH:
                return(this.maze[curRow][curCol+1] == 1);
            case WEST:
                return(this.maze[curRow+1][curCol] == 1);
            default:
                return false;
        }
    }

    private void changeDirec(String turn) {
        if (turn.equals("R")) {
            switch(curDirection) {
                case NORTH:
                    curDirection = Direction.EAST;
                    break;
                case EAST:
                    curDirection = Direction.SOUTH;
                    break;
                case SOUTH:
                    curDirection = Direction.WEST;
                    break;
                case WEST:
                    curDirection = Direction.NORTH;
                    break;
            }
        }
        else if(turn.equals("L")) {
            switch(curDirection) {
                case NORTH:
                    curDirection = Direction.WEST;
                    break;
                case EAST:
                    curDirection = Direction.NORTH;
                    break;
                case SOUTH:
                    curDirection = Direction.EAST;
                    break;
                case WEST:
                    curDirection = Direction.SOUTH;
                    break;
            }
        }
    }
}