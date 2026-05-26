package smartlibrary;

public class BookBST_T3 {
     private BookNode_T3 root;


     // Insert a book into the BST

    public void insert(Book book) {
        root = insertRec(root, book);
    }

    private BookNode_T3 insertRec(BookNode_T3 node, Book book) {
        if (node == null) {
            return new BookNode_T3(book);
        }
        if (book.getIsbn() < node.book.getIsbn()) {
            node.left = insertRec(node.left, book);
        } else if (book.getIsbn() > node.book.getIsbn()) {
            node.right = insertRec(node.right, book);
        }
        return node;
    }


    // ===== TASK 3: RECURSIVE SEARCH FUNCTION =====
    
    public Book search(int isbn) {
        return searchRec(root, isbn);
    }


    // Recursive helper function to search by ISBN

    private Book searchRec(BookNode_T3 node, int isbn) {
        
        // Base case: node is null (book not found)

        if (node == null) {
            return null;
        }

        // Base case: ISBN found

        if (node.book.getIsbn() == isbn) {
            return node.book;
        }

        // Recursive case: search left subtree if isbn is smaller
        
        if (isbn < node.book.getIsbn()) {
            return searchRec(node.left, isbn);

        }


        // Recursive case: search right subtree if isbn is larger
        
        return searchRec(node.right, isbn);
    }


    
    // Delete a book from the BST
    public void delete(int isbn) {
        root = deleteRec(root, isbn);
    }

    private BookNode_T3 deleteRec(BookNode_T3 node, int isbn) {
        if (node == null) {
            return null;
        }

        if (isbn < node.book.getIsbn()) {
            node.left = deleteRec(node.left, isbn);
        } else if (isbn > node.book.getIsbn()) {
            node.right = deleteRec(node.right, isbn);
        } 
        
        else {
            // Node with only one child or no child
            if (node.left == null) {
                return node.right;
            } 
            else if (node.right == null) {
                return node.left;
            }
    




}
