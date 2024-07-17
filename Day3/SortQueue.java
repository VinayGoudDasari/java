/*Create a new empty stack that will be used for sorting.

While the queue is not empty, dequeue an element from the front of the queue.
Compare the dequeued element with the elements in the stack.
Pop elements from the stack and enqueue them back into the queue
 until you find the correct position for the dequeued element or the stack is empty.
Push the dequeued element onto the stack.
Rebuild the Queue:
Once the queue is empty,
pop all elements from the stack and enqueue them back into the queue.
 The queue will be sorted in order.


 We create a queue and add some integers to it.
We create an empty stack.
We dequeue an element from the queue (temp).
We compare temp with the elements in the stack.
We pop elements from the stack back into the queue until we find the correct position for temp.
We push temp onto the stack.
We move elements back from the queue to the stack to maintain the order.
Rebuild the Queue:

Once the queue is empty, we transfer all elements from the stack back to the queue,
 ensuring they are in sorted order.
 */



import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class SortQueueWithStack {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of elements in the queue:");
        int n = scanner.nextInt();

        System.out.println("Enter the elements of the queue:");
        for (int i = 0; i < n; i++) {
            queue.add(scanner.nextInt());
        }

        System.out.println("Original Queue: " + queue);
        sortQueue(queue);
        System.out.println("Sorted Queue: " + queue);
    }

    public static void sortQueue(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();

        while (!queue.isEmpty()) {
            int temp = queue.poll();

            // Place the element in the correct position in the stack
            while (!stack.isEmpty() && stack.peek() > temp) {
                queue.add(stack.pop());
            }

            stack.push(temp);

            // Move the elements back to the stack to maintain order
            while (!queue.isEmpty() && queue.peek() >= stack.peek()) {
                stack.push(queue.poll());
            }
        }

        // Transfer sorted elements back to the queue
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }
}
