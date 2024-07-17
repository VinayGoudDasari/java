package Day9;

class BitManipulation {

    // Function to count the number of set bits in a single integer
    public static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            count += (n & 1);  // Add 1 if the least significant bit is set
            n >>= 1;           // Shift right by 1 to check the next bit
        }
        return count;
    }

    // Function to count the total number of set bits in all integers from 1 to n
    public static int totalSetBits(int n) {
        int totalCount = 0;
        for (int i = 1; i <= n; i++) {
            totalCount += countSetBits(i);
        }
        return totalCount;
    }

    public static void main(String[] args) {
        int num = 9; // Example number
        System.out.println("Number of set bits in " + num + " is: " + countSetBits(num));
        System.out.println("Total number of set bits from 1 to " + num + " is: " + totalSetBits(num));
    }
}
