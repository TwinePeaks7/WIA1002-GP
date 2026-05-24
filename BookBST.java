// File: BookBST.java
public class BookBST {
    BookNode root;

    public BookBST() {
        this.root = null;
    }

    // Public method to insert a new book
    public void insert(String isbn, String title, String author) {
        root = insertRec(root, isbn, title, author);
        System.out.println("Book '" + title + "' (ISBN: " + isbn + ") successfully added to the catalogue.");
    }

    // Recursive helper method to find the correct position in the BST
    private BookNode insertRec(BookNode current, String isbn, String title, String author) {
        // If the tree is empty or we've reached a leaf (null), create a new node here
        if (current == null) {
            return new BookNode(isbn, title, author);
        }

        // Compare ISBN to determine the direction (Left or Right)
        int compareResult = isbn.compareTo(current.isbn);

        if (compareResult < 0) {
            // If the new ISBN is lexicographically smaller, go to the left subtree
            current.left = insertRec(current.left, isbn, title, author);
        } else if (compareResult > 0) {
            // If the new ISBN is lexicographically larger, go to the right subtree
            current.right = insertRec(current.right, isbn, title, author);
        } else {
            // If compareResult == 0, the ISBN already exists. 
            // We assume ISBNs are unique, so we don't insert duplicates.
            System.out.println("A book with ISBN " + isbn + " already exists in the catalogue!");
        }

        return current;
    }

    // Additional method to verify if the BST is formed correctly (In-Order Traversal)
    // This will display the books in ascending order based on ISBN.
    public void displayCatalogue() {
        System.out.println("\n--- Book Catalogue (Sorted by ISBN) ---");
        inOrderRec(root);
        System.out.println("---------------------------------------");
    }

    private void displayBook(BookNode node) {
        System.out.println("ISBN: " + node.isbn + " | Title: " + node.title + " | Author: " + node.author);
    }

    private void inOrderRec(BookNode root) {
        if (root != null) {
            inOrderRec(root.left);
            displayBook(root);
            inOrderRec(root.right);
        }
    }
}