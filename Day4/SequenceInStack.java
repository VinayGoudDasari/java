import java.util.*;

class StackSequenceChecker {

    public static int isSequenceInStack(Stack<Integer> stack, int[] sequence) {
        if (sequence.length == 0) {
            return -1; // An empty sequence cannot have a valid start index
        }

        Stack<Integer> tempStack = new Stack<>();
        boolean found = false;
        int index = -1;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            tempStack.push(current);

            if (current == sequence[0]) {
                index = stack.size(); // Since stack.size() gives the index of the next element to pop
                found = checkSequence(stack, tempStack, sequence);
                if (found) {
                    break;
                } else {
                    index = -1; // Reset index if not found
                }
            }
        }

        // Restore the original stack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        return index;
    }

    private static boolean checkSequence(Stack<Integer> stack, Stack<Integer> tempStack, int[] sequence) {
        int index = 0;

        // Check the sequence in the tempStack
        while (!tempStack.isEmpty() && index < sequence.length) {
            if (tempStack.peek() == sequence[index]) {
                tempStack.pop();
                index++;
            } else {
                return false;
            }
        }

        // If we matched all elements of the sequence
        if (index == sequence.length) {
            return true;
        }

        // If we have not matched all elements, check the remaining stack
        while (!stack.isEmpty() && index < sequence.length) {
            if (stack.pop() == sequence[index]) {
                index++;
            } else {
                return false;
            }
        }

        return index == sequence.length;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(5);
        stack.push(9);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        int[] sequence = {1, 2, 3};

        int resultIndex = isSequenceInStack(stack, sequence);
        if (resultIndex != -1) {
            System.out.println("Sequence " + Arrays.toString(sequence) + " starts at index: " + resultIndex);
        } else {
            System.out.println("Sequence " + Arrays.toString(sequence) + " not found in the stack");
        }
    }
}
