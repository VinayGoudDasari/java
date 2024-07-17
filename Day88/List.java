package list;

import java.util.Scanner;

 class LinkedListExample {
    Node head; // head of the list

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    // Method to insert a new node
    public static LinkedListExample insert(LinkedListExample list, int data) {
        Node new_node = new Node(data);
        new_node.next = null;

        if (list.head == null) {
            list.head = new_node;
        } else {
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new_node;
        }
        return list;
    }

    // Method to print the LinkedList.
    public static void printList(LinkedListExample list) {
        Node currNode = list.head;

        System.out.print("LinkedList: ");

        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println();
    }

    // Method to remove duplicates from the sorted linked list
    public static void removeDuplicates(LinkedListExample list) {
        Node current = list.head;

        while (current != null && current.next != null) {
            if (current.data == current.next.data) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }

    // main method to test the above methods
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedListExample list = new LinkedListExample();

        System.out.println("Enter the number of elements you want to insert:");
        int numElements = scanner.nextInt();

        System.out.println("Enter the elements in sorted order:");
        for (int i = 0; i < numElements; i++) {
            int data = scanner.nextInt();
            list = insert(list, data);
        }

        System.out.println("Linked List before removing duplicates:");
        printList(list);

        removeDuplicates(list);

        System.out.println("Linked List after removing duplicates:");
        printList(list);

        scanner.close();
    }
}

