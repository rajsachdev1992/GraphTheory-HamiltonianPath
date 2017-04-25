package rsachde1;

/**
 *
 * @author Raj.Sachdev
 * This class represents a stack which stores integer values. This stack is used by
 * the MST class for Pre Order Traversal.
 */
public class Stack {
    
    private int top; //top position
    private final int s[]; //stack array
    private final int capacity; //maximum capacity of the stack.
    
    public Stack(int capacity) {
        this.capacity = capacity;
        top = 0;
        s = new int[this.capacity];
    }
    
    /**
     * Pushes in integer into the stack
     * @param v
     * Complexity: Theta(1)
     */
    public void push(int v) {
        s[top++] = v;
    }
    
    /**
     * Deletes and returns an integer from the top of the stack.
     * @return Integer
     * Complexity: Theta(1)
     * PreCondition: Stack should not be empty
     * PostCondition: Element is deleted from the top of the stack.
     */
    public int pop() {
        return s[--top];
    }
    
    /**
     * Checks if the stack is empty
     * @return boolean: True if the stack is empty, else false.
     */
    public boolean isEmpty() {
        return top <= 0;
    }
    
}
