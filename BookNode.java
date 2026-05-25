// File: BookNode.java
public class BookNode {
    String isbn;
    String title;
    String author;
    BookNode left;
    BookNode right;

    // Constructor to create a new book node
    public BookNode(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.left = null;
        this.right = null;
    }
}