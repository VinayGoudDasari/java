package Day11;

public class Knapsack {
    public static int knapsackProblem(int W, int[] weights, int[] values) {
        int n = weights.length;
        int[][] K = new int[n + 1][W + 1];

        // Build table K[][] in bottom up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (weights[i - 1] <= w)
                    K[i][w] = Math.max(values[i - 1] + K[i - 1][w - weights[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        return K[n][W];
    }

    public static void main(String[] args) {
        int W = 50; // Knapsack capacity
        int[] weights = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100}; // Weights of items
        int[] values = {60, 100, 120, 140, 160, 180, 200, 220, 240, 260}; // Values of items

        int maxValue = knapsackProblem(W, weights, values);
        System.out.println("Maximum value in knapsack = " + maxValue);
    }
}
