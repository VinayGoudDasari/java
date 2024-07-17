package Day10;

 class Main {
    static int INF = Integer.MAX_VALUE;

    static int findMinCost(int[][] graph, int n) {
        int dp[][] = new int[1 << n][n];
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return tspUtil(graph, dp, 0, 0, n);
    }

    static int tspUtil(int[][] graph, int[][] dp, int mask, int pos, int n) {
        if (mask == ((1 << n) - 1)) {
            return graph[pos][0];
        }
        if (dp[mask][pos]!= -1) {
            return dp[mask][pos];
        }
        int ans = INF;
        for (int i = 0; i < n; i++) {
            if (((mask >> i) & 1) == 0) {
                int newMask = mask | (1 << i);
                ans = Math.min(ans, graph[pos][i] + tspUtil(graph, dp, newMask, i, n));
            }
        }
        dp[mask][pos] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 25, 10, 15, 20},
                {25, 0, 18, 22, 12},
                {10, 18, 0, 16, 25},
                {15, 22, 16, 0, 18},
                {20, 12, 25, 18, 0}
        };
        int n = graph.length;
        System.out.println("Minimum cost is " + findMinCost(graph, n));
    }
}
