package smartlibrary;

public class BookSearchNode {

    public Book book;
    public BookSearchNode left;
    public BookSearchNode right;

    public BookSearchNode(Book book) {
        this.book = book;
        this.left = null;
        this.right = null;
    }
}
