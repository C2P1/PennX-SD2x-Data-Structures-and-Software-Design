public class LinkedList {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addToFront(5);
        list.addToFront(8);
        // 8 5

        list.addToBack(9);
        list.addToBack(10);
        // 8 5 9 10

        list.addAtIndex(7, 3);
        // 8 5 9 7 10

        list.removeFromFront();
        // 5 9 7 10

        list.removeFromBack();
        // 5 9 7

        list.removeAtIndex(1);
        // 5 7



        list.printList();
    }

    private class Node {
        int value;
        Node next = null;

        public Node(int value) {
            this.value = value;
        }
    }

    protected Node head = null;
    protected Node tail = null;

    public void addToFront(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        if (newNode.next == null) {
            tail = newNode;
        }
    }

    public void addToBack(int value) {
        Node newNode = new Node(value);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
    }

    public void addAtIndex(int value, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            addToFront(value);
        } else {

            Node newNode = new Node(value);
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                if (current == null) {
                    System.out.println("out of bounds");
                    throw new IndexOutOfBoundsException();
                }
                current = current.next;
            }
            if (current.next == null) {
                System.out.println("new tail!");
                addToBack(value);
            } else {
                newNode.next = current.next;
                current.next = newNode;
            }
        }
    }

    public void removeFromFront() {
        if (head != null) {
            head = head.next;
        } else {
            tail = null;
        }
    }

    public void removeFromBack() {
        if (head == null) {
            // empty list
            return;
        } else if (head.equals(tail)) {
            // single item list
            head = null;
            tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            tail = current;
            current.next = null;
        }
    }

    public void removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            removeFromFront();
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                if (current == null) {
                    throw new IndexOutOfBoundsException();
                }
                current = current.next;
            }
            current.next = current.next.next;
            if (current.next == null) {
                tail = current;
            }
        }
    }

    public void printList() {
        Node current = head;
        System.out.println("LinkedList From Top:");
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
        System.out.println("===========");
    }
}
