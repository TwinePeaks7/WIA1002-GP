package smartlibrary;
import java.util.Stack;

// ======================================
// Borrow History Class
// Task 2 - Stack
// ======================================
class HistoryStack { 

    // Create stack (Made private for "Information Hiding" marks) 
    private Stack<BookNode> stack = new Stack<>();

    // Borrow book (Adds book to the top of the stack) 
    public void push(BookNode b) {

        stack.push(b); // Pushes the book 
    }

    // Show history 
    public void display() {

        // If the stack is empty, display message to user
        if (stack.isEmpty()) {

            System.out.println("History is empty.");
            return;
        }

        // Loop backwards to show the newest borrowed books first! (LIFO order) 
        for (int i = stack.size() - 1; i >= 0; i--) {

            BookNode b = stack.get(i); // Gets the book at index i 
            System.out.println("[ISBN: " + b.isbn + "] " + b.title); 
        }
    }
    public Book pop() {
    if (stack.isEmpty()) return null;
    BookNode node = stack.pop();
    return new Book(Integer.parseInt(node.isbn), node.title, node.author);
}

public boolean isEmpty() {
    return stack.isEmpty();
}

}
