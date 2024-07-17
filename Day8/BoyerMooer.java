 class BoyerMoore {

    private static final int ALPHABET_SIZE = 256;

    public static int boyerMooreLastOccurrence(String text, String pattern) {
        int[] badCharTable = preprocessBadCharacterTable(pattern);
        int[] goodSuffixTable = preprocessGoodSuffixTable(pattern);
        int textLength = text.length();
        int patternLength = pattern.length();

        int s = 0; // s is the shift of the pattern with respect to the text
        int lastOccurrence = -1;

        while (s <= textLength - patternLength) {
            int j = patternLength - 1;

            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j))
                j--;

            if (j < 0) {
                lastOccurrence = s;
                s += goodSuffixTable[0];
            } else {
                s += Math.max(goodSuffixTable[j], j - badCharTable[text.charAt(s + j)]);
            }
        }

        return lastOccurrence;
    }

    private static int[] preprocessBadCharacterTable(String pattern) {
        int[] badCharTable = new int[ALPHABET_SIZE];
        int patternLength = pattern.length();

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            badCharTable[i] = -1;
        }

        for (int i = 0; i < patternLength; i++) {
            badCharTable[pattern.charAt(i)] = i;
        }

        return badCharTable;
    }

    private static int[] preprocessGoodSuffixTable(String pattern) {
        int patternLength = pattern.length();
        int[] goodSuffixTable = new int[patternLength];
        int[] suffixes = new int[patternLength];

        suffixes[patternLength - 1] = patternLength;
        int g = patternLength - 1;
        int f = patternLength - 1;

        for (int i = patternLength - 2; i >= 0; i--) {
            if (i > g && suffixes[i + patternLength - 1 - f] < i - g) {
                suffixes[i] = suffixes[i + patternLength - 1 - f];
            } else {
                if (i < g) g = i;
                f = i;
                while (g >= 0 && pattern.charAt(g) == pattern.charAt(g + patternLength - 1 - f))
                    g--;
                suffixes[i] = f - g;
            }
        }

        for (int i = 0; i < patternLength; i++) {
            goodSuffixTable[i] = patternLength;
        }

        int j = 0;
        for (int i = patternLength - 1; i >= 0; i--) {
            if (suffixes[i] == i + 1) {
                for (; j < patternLength - 1 - i; j++) {
                    if (goodSuffixTable[j] == patternLength) {
                        goodSuffixTable[j] = patternLength - 1 - i;
                    }
                }
            }
        }

        for (int i = 0; i <= patternLength - 2; i++) {
            goodSuffixTable[patternLength - 1 - suffixes[i]] = patternLength - 1 - i;
        }

        return goodSuffixTable;
    }

    public static void main(String[] args) {
        String text = "abracadabra";
        String pattern = "abra";
        int index = boyerMooreLastOccurrence(text, pattern);
        System.out.println("Last occurrence of '" + pattern + "' in '" + text + "' is at index: " + index);
    }
}
