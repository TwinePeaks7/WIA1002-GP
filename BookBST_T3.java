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



    

    
    
}
