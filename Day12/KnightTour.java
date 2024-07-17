package Day12;

 class KnightsTour {
    private static final int BOARD_SIZE = 8;
    private static final int[][] MOVES = {{1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}};

    public static boolean SolveKnightsTour(int[][] board, int moveX, int moveY, int moveCount, int[] xMove, int[] yMove) {
        if (moveCount == BOARD_SIZE * BOARD_SIZE)
            return true;

        for (int i = 0; i < MOVES.length; i++) {
            int newX = moveX + xMove[i];
            int newY = moveY + yMove[i];

            if (isValidMove(newX, newY, board)) {
                board[newX][newY] = moveCount;
                if (SolveKnightsTour(board, newX, newY, moveCount + 1, xMove, yMove))
                    return true;
                board[newX][newY] = 0; // backtrack
            }
        }

        return false;
    }

    private static boolean isValidMove(int x, int y, int[][] board) {
        if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE)
            return false;
        if (board[x][y] != 0)
            return false;
        return true;
    }

    public static void main(String[] args) {
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        int moveX = 0;
        int moveY = 0;
        int moveCount = 1;
        int[] xMove = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] yMove = {-2, -1, 1, 2, 2, 1, -1, -2};

        if (SolveKnightsTour(board, moveX, moveY, moveCount, xMove, yMove)) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution exists");
        }
    }
}
