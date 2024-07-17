package Day12;
class LongestCommonSubsequence {
    public static void LCS(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int lcsLength = dp[m][n];
        if (lcsLength == 0) {
            System.out.println("Sequence not found");
            return;
        }

        System.out.println("Length of the longest common subsequence = " + lcsLength);

        // Print the longest common subsequence
        int i = m, j = n;
        StringBuilder lcs = new StringBuilder();
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.insert(0, text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1])
                i--;
            else
                j--;
        }

        System.out.println("Longest common subsequence = " + lcs.toString());
    }

    public static void main(String[] args) {
        String text1 = "abcdefghij";
        String text2 = "abcd";
        LCS(text1, text2);
    }
}
