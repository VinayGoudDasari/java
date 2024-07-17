import java.util.ArrayList;
import java.util.Scanner;

 class MinHeap {
    private ArrayList<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public void insert(int val) {
        heap.add(val);
        int currentIndex = heap.size() - 1;
        int parentIndex = (currentIndex - 1) / 2;

        // Bubble up
        while (currentIndex > 0 && heap.get(currentIndex) < heap.get(parentIndex)) {
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = (currentIndex - 1) / 2;
        }
    }

    public int extractMin() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        if (heap.size() == 1) {
            return heap.remove(0);
        }

        int min = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));

        // Bubble down
        heapify(0);

        return min;
    }

    public int getMin() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        return heap.get(0);
    }

    private void heapify(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int smallest = index;

        if (leftChildIndex < heap.size() && heap.get(leftChildIndex) < heap.get(smallest)) {
            smallest = leftChildIndex;
        }

        if (rightChildIndex < heap.size() && heap.get(rightChildIndex) < heap.get(smallest)) {
            smallest = rightChildIndex;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of elements to insert initially:");
        int numElements = scanner.nextInt();

        System.out.println("Enter the elements:");
        for (int i = 0; i < numElements; i++) {
            int value = scanner.nextInt();
            minHeap.insert(value);
        }

        String command;
        scanner.nextLine(); // consume the newline character

        System.out.println("Min-Heap operations: insert <number>, extractMin, getMin, exit");

        while (true) {
            System.out.print("Enter command: ");
            command = scanner.nextLine();

            if (command.startsWith("insert")) {
                int value = Integer.parseInt(command.split(" ")[1]);
                minHeap.insert(value);
                System.out.println("Inserted " + value);
            } else if (command.equals("extractMin")) {
                try {
                    int min = minHeap.extractMin();
                    System.out.println("Extracted min: " + min);
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("getMin")) {
                try {
                    int min = minHeap.getMin();
                    System.out.println("Current min: " + min);
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid command");
            }
        }

        scanner.close();
    }
}
