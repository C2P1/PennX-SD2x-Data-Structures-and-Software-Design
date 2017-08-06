import java.util.LinkedList;

public class Stack {
    protected LinkedList list = new LinkedList();

    // add to top of stack
    public void push(Object o) {
        list.addFirst(o);
    }

    // remove from top of stack
    public Object pop() {
        if (list.isEmpty()) {
            return null;
        }
        return list.removeFirst();
    }

    // return the top of the stack (but not remove it!)
    public Object peek() {
        return list.getFirst();
    }
}
