package smartlibrary;
import java.util.Scanner;
class BST{}
class HistoryStack{}
public class SmartLibrary implements LibraryADT{
    private BST bst;
    private HistoryStack history;
    public SmartLibrary(){
        bst=new BST();
        history=new HistoryStack();
    }
    @Override
    public void addBook(int isbn,String title,String author){
        Book book=new Book(isbn,title,author);
        bst.insert(book);
        System.out.println("Book added successfully.");
    }
    @Override
    public void searchBook(int isbn){
        Book book=bst.search(isbn);
        if(book!=null){
            System.out.println("Found: "+book.getTitle());
        }else{
            System.out.println("Not Found.");
        }
    }
    @Override
    public void borrowBook(int isbn){
        Book book=bst.search(isbn);
        if(book!=null){
            history.push(book);
            bst.delete(isbn);
            System.out.println("Book borrowed successfully.");
        }else{System.out.println("Not Found.");}
    }
    @Override
    public void viewLatestHistory(){
        history.display();
    }
    @Override
    public void Menu(){
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("\n---Smart Library System---");
            System.out.println("1.Add book");
            System.out.println("2.Search book by ISBN");
            System.out.println("3.Borrow book");
            System.out.println("4.View borrowing history");
            System.out.println("5.Exit");
            System.out.print("Choice: ");
            int choice=scanner.nextInt();
            if(choice==5)break;
            switch(choice){
                case 1:
                    System.out.print("Enter ISBN: ");
                    int i=scanner.nextInt();
                    System.out.print("Enter Title: ");
                    String t=scanner.next();
                    System.out.print("Enter Author: ");
                    String a=scanner.next();
                    addBook(i,t,a);
                    break;
                case 2:
                    System.out.print("Enter ISBN to search: ");
                    searchBook(scanner.nextInt());
                    break;
                case 3:
                    System.out.print("Enter ISBN to borrow: ");
                    borrowBook(scanner.nextInt());
                    break;
                case 4:
                    viewLatestHistory();
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    public static void main(String[]args){
        
    }
}
