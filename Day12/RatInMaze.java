 class RatInMaze {
    private static final int MAZE_SIZE = 6;
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static boolean SolveMaze(int[][] maze) {
        int[][] solution = new int[MAZE_SIZE][MAZE_SIZE];
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++) {
                solution[i][j] = 0;
            }
        }

        if (!solveMazeUtil(maze, 0, 0, solution)) {
            System.out.println("No solution exists");
            return false;
        }

        printSolution(solution);
        return true;
    }

    private static boolean solveMazeUtil(int[][] maze, int x, int y, int[][] solution) {
        if (x == MAZE_SIZE - 1 && y == MAZE_SIZE - 1) {
            solution[x][y] = 1;
            return true;
        }

        if (isSafe(maze, x, y) && solution[x][y] == 0) {
            solution[x][y] = 1;

            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (solveMazeUtil(maze, newX, newY, solution)) {
                    return true;
                }
            }

            solution[x][y] = 0; // backtrack
        }

        return false;
    }

    private static boolean isSafe(int[][] maze, int x, int y) {
        return (x >= 0 && x < MAZE_SIZE && y >= 0 && y < MAZE_SIZE && maze[x][y] == 1);
    }

    private static void printSolution(int[][] solution) {
        for (int i = 0; i < MAZE_SIZE; i++) {
            for (int j = 0; j < MAZE_SIZE; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] maze = {
                {1, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 1}
        };

        if (SolveMaze(maze)) {
            System.out.println("Solution exists");
        } else {
            System.out.println("No solution exists");
        }
    }
}