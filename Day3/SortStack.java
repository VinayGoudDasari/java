
/*
A Scanner object is created to read input from the user.
The number of elements in the stack (n) is read from the user.
The elements of the stack are read in a loop and pushed onto the stack.


The original stack is printed.
The sortStack method is called to sort the stack.
The sorted stack is printed
*/
import java.util.Scanner;
import java.util.Stack;

class SortedStack {
    public static void sortStack(Stack<Integer> stack) {
        Stack<Integer> tempStack = new Stack<>();

        while (!stack.isEmpty()) {
            // Pop an element from the original stack
            int current = stack.pop();

            // While temporary stack is not empty and top of tempStack is greater than current
            while (!tempStack.isEmpty() && tempStack.peek() > current) {
                // Pop from tempStack and push it back to the original stack
                stack.push(tempStack.pop());
            }

            // Push current element to tempStack
            tempStack.push(current);
        }

        // Transfer sorted elements back to the original stack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();

        System.out.print("Enter the number of elements in the stack: ");
        int n = scanner.nextInt();

        System.out.println("Enter the elements of the stack:");
        for (int i = 0; i < n; i++) {
            int element = scanner.nextInt();
            stack.push(element);
        }

        System.out.println("Original Stack:");
        System.out.println(stack);

        sortStack(stack);

        System.out.println("Sorted Stack:");
        System.out.println(stack);

        scanner.close();
    }
}
