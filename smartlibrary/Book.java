package smartlibrary;
public class Book {
    private int isbn;
    private String title;
    private String author;
    public Book(int isbn,String title,String author){
        this.isbn=isbn;
        this.title=title;
        this.author=author;
    }
    public int getIsbn(){return isbn;}
    public String getTitle(){return title;}
    public String getAuthor(){return author;}
    @Override
    public String toString(){//display book info
        return"ISBN: "+isbn+",Title: "+title+",Author: "+author;}
}
