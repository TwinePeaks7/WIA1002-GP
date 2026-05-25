import java.util.Stack;
class HistoryStack {
    private Stack<BookNode> stack = new Stack<>();
}
import java.util.Stack;

// Task 2 - Stack Structure
class HistoryStack {
    private Stack<BookNode> stack = new Stack<>();

    // Adds book to the top of the stack
    public void push(BookNode b) {
        stack.push(b);
    }
}
