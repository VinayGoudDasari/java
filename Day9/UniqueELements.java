import java.util.HashMap;
import java.util.Map;

class UniqueAndRepeatedElements {

    // Function to find the two non-repeating elements
    public static int[] findUniqueElements(int[] arr) {
        int xor = 0;

        // Step 1: XOR all the elements
        for (int num : arr) {
            xor ^= num;
        }

        // Step 2: Find a set bit in the XOR result
        int setBit = xor & -xor;

        int[] result = {0, 0};

        // Step 3: Divide the elements into two groups and XOR each group separately
        for (int num : arr) {
            if ((num & setBit) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }

        return result;
    }

    // Function to print repeated elements
    public static void printRepeatedElements(int[] arr) {
        Map<Integer, Integer> elementCount = new HashMap<>();

        // Count the occurrences of each element
        for (int num : arr) {
            elementCount.put(num, elementCount.getOrDefault(num, 0) + 1);
        }

        System.out.print("Repeated elements are: ");
        for (Map.Entry<Integer, Integer> entry : elementCount.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.print(entry.getKey() + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 4, 1};

        // Find the two unique elements
        int[] uniqueElements = findUniqueElements(arr);
        System.out.println("The two non-repeating elements are: " + uniqueElements[0] + " and " + uniqueElements[1]);

        // Print the repeated elements
        printRepeatedElements(arr);
    }
}
