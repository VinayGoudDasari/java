package linkedlists;
import java.util.Scanner;

class Node{
    int data;
    Node next;

    // Constructor to create a new node
    Node(int d) {
        data = d;
        next = null;
    }
}

class LinkedList {
    Node head; // head of list

    // Method to insert a new node at the end of the list
    public void insertAtEnd(int new_data) {
        Node new_node = new Node(new_data);
        if (head == null) {
            head = new_node;
            return;
        }
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = new_node;
    }

    // Method to find the middle element of the list
    public Node findMiddle() {
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Method to display the linked list
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Main method to run the program
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of elements in the list:");
        int numElements = scanner.nextInt();

        System.out.println("Enter the elements:");
        for (int i = 0; i < numElements; i++) {
            int data = scanner.nextInt();
            list.insertAtEnd(data);
        }

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Find middle element");
            System.out.println("2. Display list");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Node middle = list.findMiddle();
                    if (middle != null) {
                        System.out.println("The middle element is: " + middle.data);
                    } else {
                        System.out.println("The list is empty.");
                    }
                    break;
                case 2:
                    list.display();
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
