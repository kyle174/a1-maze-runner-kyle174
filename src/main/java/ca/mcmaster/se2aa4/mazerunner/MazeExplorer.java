package ca.mcmaster.se2aa4.mazerunner;

public class MazeExplorer {
    private final int[][] maze;

    public MazeExplorer(String file) {
        MazeGenerator mazeGen = new MazeGenerator(file);
        this.maze = mazeGen.loadMaze();
    }

    private int findEntry() {
        int entry = -1;
        for(int i=0; i<this.maze.length; i++) {
            if (this.maze[i][0] == 1) {
                entry = i;
                break;
            }
        }
        return entry;
    }

    private int findExit() {
        int exit = -1;
        for(int i=0; i<this.maze.length; i++) {
            if (this.maze[i][this.maze[0].length-1] == 1) {
                exit = i;
                break;
            }
        }
        return exit;
    }

    public String processPath(String inputPath) {
        MazeRunner runner = new RightHand(this.maze, this.findEntry(), this.findExit());
        if (inputPath == null) {
            return(runner.calcPath());
        }
        else {
            if (runner.verifyPath(inputPath)) {
                return("correct path");
            }
            else {
                return("incorrect path");
            }
        }
    }
}
