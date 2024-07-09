package linkedlist;
import java.util.Scanner;

class ListNode {
    int data;
    ListNode next;

    // Constructor to create a new node
    ListNode(int d) {
        data = d;
        next = null;
    }
}

class SortedList {
    ListNode head; // head of list

    // Method to insert a new node at the end of the list
    public void insertAtEnd(int new_data) {
        ListNode new_node = new ListNode(new_data);
        if (head == null) {
            head = new_node;
            return;
        }
        ListNode last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = new_node;
    }

    // Method to display the linked list
    public void display() {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Method to merge two sorted linked lists
    public static ListNode mergeSortedLists(ListNode head1, ListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        ListNode mergedHead;

        if (head1.data < head2.data) {
            mergedHead = head1;
            head1 = head1.next;
        } else {
            mergedHead = head2;
            head2 = head2.next;
        }

        ListNode current = mergedHead;

        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                current.next = head1;
                head1 = head1.next;
            } else {
                current.next = head2;
                head2 = head2.next;
            }
            current = current.next;
        }

        if (head1 != null) {
            current.next = head1;
        } else {
            current.next = head2;
        }

        return mergedHead;
    }

    // Main method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SortedList list1 = new SortedList();
        SortedList list2 = new SortedList();

        // Input for list1
        System.out.println("Enter the number of elements in list 1:");
        int numElements1 = scanner.nextInt();
        System.out.println("Enter the elements of list 1:");
        for (int i = 0; i < numElements1; i++) {
            int data = scanner.nextInt();
            list1.insertAtEnd(data);
        }

        // Input for list2
        System.out.println("Enter the number of elements in list 2:");
        int numElements2 = scanner.nextInt();
        System.out.println("Enter the elements of list 2:");
        for (int i = 0; i < numElements2; i++) {
            int data = scanner.nextInt();
            list2.insertAtEnd(data);
        }

        System.out.println("List 1:");
        list1.display();
        System.out.println("List 2:");
        list2.display();

        ListNode mergedHead = mergeSortedLists(list1.head, list2.head);

        System.out.println("Merged List:");
        SortedList mergedList = new SortedList();
        mergedList.head = mergedHead;
        mergedList.display();

        scanner.close();
    }
}
