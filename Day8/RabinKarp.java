package Day8;

public class RabinKarp {

    // Prime number for hashing
    private static final int PRIME = 101;

    public static void main(String[] args) {
        String text = "thequickbrownfoxjumpsoverthelazydog";
        String pattern = "brown";
        int result = rabinKarp(text, pattern);
        if (result == -1) {
            System.out.println("Pattern not found.");
        } else {
            System.out.println("Pattern found at index " + result);
        }
    }

    public static int rabinKarp(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();
        long patternHash = createHash(pattern, m);
        long textHash = createHash(text, m);

        for (int i = 0; i <= n - m; i++) {
            // If the hash values match, check the actual substrings
            if (patternHash == textHash && checkEqual(text, pattern, i)) {
                return i;
            }
            // Recalculate hash value for the next substring in the text
            if (i < n - m) {
                textHash = recalculateHash(text, i, i + m, textHash, m);
            }
        }
        return -1;
    }

    private static long createHash(String str, int end) {
        long hash = 0;
        for (int i = 0; i < end; i++) {
            hash += str.charAt(i) * Math.pow(PRIME, i);
        }
        return hash;
    }

    private static long recalculateHash(String str, int oldIndex, int newIndex, long oldHash, int patternLength) {
        long newHash = oldHash - str.charAt(oldIndex);
        newHash = newHash / PRIME;
        newHash += str.charAt(newIndex) * Math.pow(PRIME, patternLength - 1);
        return newHash;
    }

    private static boolean checkEqual(String text, String pattern, int start) {
        for (int i = 0; i < pattern.length(); i++) {
            if (text.charAt(start + i) != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
