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
    public boolean verifyPath(String path) {
        int curRow = this.entry;
        int curCol = 0;
        try {
            for (int i = 0; i < path.length(); i++) {
                char curr = path.charAt(i);
                if (curr == 'R') {
                    changeDirec("R");
                } else if (curr == 'L') {
                    changeDirec("L");
                } else {
                    switch (curDirection) {
                        case NORTH -> {
                            if (this.maze[curRow - 1][curCol] == 1) {
                                curRow -= 1;
                            } else {
                                return false;
                            }
                        }
                        case EAST -> {
                            if (this.maze[curRow][curCol + 1] == 1) {
                                curCol += 1;
                            } else {
                                return false;
                            }
                        }
                        case SOUTH -> {
                            if (this.maze[curRow + 1][curCol] == 1) {
                                curRow += 1;
                            } else {
                                return false;
                            }
                        }
                        case WEST -> {
                            if (this.maze[curRow][curCol - 1] == 1) {
                                curCol -= 1;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return (curRow == this.exit && curCol == this.maze[0].length - 1);
    }

    @Override
    public String calcPath() {
        logger.info("**** Computing path");
        int curRow = this.entry;
        int curCol = 0;
        int colSize = this.maze[0].length-1;
        String result = "";
        String prevMove = "";
        while (!(curRow == this.exit && curCol == colSize)) {
            if (checkRight(curRow,curCol) && !prevMove.equals("R")) {
                changeDirec("R");
                result+="R";
            }
            else if (checkFront(curRow, curCol)) {
                switch (curDirection) {
                    case NORTH -> curRow -= 1;
                    case EAST -> curCol += 1;
                    case SOUTH -> curRow += 1;
                    case WEST -> curCol -= 1;
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
        Path mazePath = new Path(result);
        return mazePath.factorPath();
    }

    private boolean checkRight(int curRow, int curCol) {
        return switch (curDirection) {
            case NORTH -> (this.maze[curRow][curCol + 1] == 1);
            case EAST -> (this.maze[curRow + 1][curCol] == 1);
            case SOUTH -> (this.maze[curRow][curCol - 1] == 1);
            case WEST -> (this.maze[curRow - 1][curCol] == 1);
            default -> false;
        };
    }

    private boolean checkFront(int curRow, int curCol) {
        return switch (curDirection) {
            case NORTH -> (this.maze[curRow - 1][curCol] == 1);
            case EAST -> (this.maze[curRow][curCol + 1] == 1);
            case SOUTH -> (this.maze[curRow + 1][curCol] == 1);
            case WEST -> (this.maze[curRow][curCol - 1] == 1);
            default -> false;
        };
    }

    private boolean checkLeft(int curRow, int curCol) {
        return switch (curDirection) {
            case NORTH -> (this.maze[curRow][curCol - 1] == 1);
            case EAST -> (this.maze[curRow - 1][curCol] == 1);
            case SOUTH -> (this.maze[curRow][curCol + 1] == 1);
            case WEST -> (this.maze[curRow + 1][curCol] == 1);
            default -> false;
        };
    }

    private void changeDirec(String turn) {
        if (turn.equals("R")) {
            switch (curDirection) {
                case NORTH -> curDirection = Direction.EAST;
                case EAST -> curDirection = Direction.SOUTH;
                case SOUTH -> curDirection = Direction.WEST;
                case WEST -> curDirection = Direction.NORTH;
            }
        }
        else if(turn.equals("L")) {
            switch (curDirection) {
                case NORTH -> curDirection = Direction.WEST;
                case EAST -> curDirection = Direction.NORTH;
                case SOUTH -> curDirection = Direction.EAST;
                case WEST -> curDirection = Direction.SOUTH;
            }
        }
    }
}