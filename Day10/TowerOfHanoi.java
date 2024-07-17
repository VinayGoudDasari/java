package Day10;

import java.util.Scanner;

public class TowerOfHanoi {

    // Recursive function to solve the Tower of Hanoi puzzle
    public static void solveHanoi(int n, char source, char auxiliary, char destination) {
        // Base case: only one disk to move
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }

        // Move n-1 disks from source to auxiliary, using destination as a buffer
        solveHanoi(n - 1, source, destination, auxiliary);

        // Move the nth disk from source to destination
        System.out.println("Move disk " + n + " from " + source + " to " + destination);

        // Move the n-1 disks from auxiliary to destination, using source as a buffer
        solveHanoi(n - 1, auxiliary, source, destination);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take input from the user for the number of disks
        System.out.print("Enter the number of disks: ");
        int n = scanner.nextInt();

        // Solve the Tower of Hanoi puzzle
        solveHanoi(n, 'A', 'B', 'C'); // A is the source, B is the auxiliary, and C is the destination

        scanner.close();
    }
}

