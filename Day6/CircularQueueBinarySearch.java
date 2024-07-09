package Day6;

import java.util.Scanner;

public class CircularQueueBinarySearch {
    public static int circularArraySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if mid element is the target
            if (arr[mid] == target) {
                return mid;
            }

            // Determine which part is sorted
            if (arr[left] <= arr[mid]) { // Left half is sorted
                if (arr[left] <= target && target < arr[mid]) {
                    right = mid - 1; // Target in the left half
                } else {
                    left = mid + 1; // Target in the right half
                }
            } else { // Right half is sorted
                if (arr[mid] < target && target <= arr[right]) {
                    left = mid + 1; // Target in the right half
                } else {
                    right = mid - 1; // Target in the left half
                }
            }
        }
        return -1; // Target not found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of elements in the circular queue:");
        int n = scanner.nextInt();

        int[] circularQueue = new int[n];
        System.out.println("Enter the elements of the circular queue:");
        for (int i = 0; i < n; i++) {
            circularQueue[i] = scanner.nextInt();
        }

        System.out.println("Enter the target element:");
        int target = scanner.nextInt();

        int index = circularArraySearch(circularQueue, target);

        if (index != -1) {
            System.out.println("Element " + target + " found at index " + index);
        } else {
            System.out.println("Element " + target + " not found in the array.");
        }

        scanner.close();
    }
}
