package ca.mcmaster.se2aa4.mazerunner;

public class Maze {
    private final int[][] maze;
    private final int entry;
    private final int exit;

    public Maze(String file) {
        MazeGenerator mazeGen = new MazeGenerator(file);
        this.maze = mazeGen.loadMaze();
        this.entry = this.findEntry();
        this.exit = this.findExit();
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
        MazeRunner runner = new RightHand(this.maze, this.entry, this.exit);
        if (inputPath == null) {
            return(runner.calcPath());
        }
        else {
            Path mazePath = new Path(inputPath);
            String fixedPath = mazePath.expandPath();
            if (runner.verifyPaths(fixedPath) && !fixedPath.isEmpty()) {
                return("correct path");
            }
            else {
                return("incorrect path");
            }
        }
    }
}
