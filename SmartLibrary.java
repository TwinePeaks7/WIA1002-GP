package smartlibrary;

import java.util.InputMismatchException;
import java.util.Scanner;

//class HistoryStack{}
public class SmartLibrary implements LibraryADT{
    private BookSearchBST bst;
    private HistoryStack history;
    private static final int BORROW_LIMIT_DAYS = 7;
    private static final double FINE_PER_DAY = 1.00;

    public SmartLibrary() {
        bst = new BookSearchBST();
        history = new HistoryStack();

        bst.loadCatalogueFromFile();
    }

    @Override
    public void addBook(int isbn, String title, String author) {
        Book book = new Book(isbn, title, author);
        bst.insert(book);
        bst.saveCatalogueToFile();
        System.out.println("Book added successfully.");
    }

    @Override
    public void searchBook(int isbn) {
        Book book = bst.search(isbn);

        if (book != null) {
            System.out.println("Found: " + book.getTitle() + " by " + book.getAuthor());
        } else {
            System.out.println("Not Found.");
        }
    }

    @Override
    public void borrowBook(int isbn) {
        Book book = bst.search(isbn);

        if (book != null) {
            history.push(new BookNode(book.getIsbn() + "", book.getTitle(), book.getAuthor()));
            bst.delete(isbn);
            bst.saveCatalogueToFile();
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Not Found.");
        }
    }

    @Override
    public void viewLatestHistory() {
        history.display();
    }

    @Override
    public void Menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n---Smart Library System---");
            System.out.println("1. Add book");
            System.out.println("2. Search book by ISBN");
            System.out.println("3. Borrow book");
            System.out.println("4. View borrowing history");
            System.out.println("5. Return book");
            System.out.println("6. Display Full Catalogue");
            System.out.println("7. Exit");
            System.out.print("Choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 7) {
                    System.out.println("Program exited.");
                    break;
                }

                switch (choice) {
                    case 1:
                        System.out.print("Enter ISBN: ");
                        int isbn = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();

                        addBook(isbn, title, author);
                        break;

                    case 2:
                        System.out.print("Enter ISBN to search: ");
                        searchBook(scanner.nextInt());
                        scanner.nextLine();
                        break;

                    case 3:
                        System.out.print("Enter ISBN to borrow: ");
                        borrowBook(scanner.nextInt());
                        scanner.nextLine();
                        break;

                    case 4:
                        viewLatestHistory();
                        break;

                    case 5:
                        returnBook();
                        break;

                    case 6:
                        displayCatalogue();
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers only for ISBN and menu choice.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    @Override
    public void returnBook() {
        Scanner scanner = new Scanner(System.in);

        if (!history.isEmpty()) {
            Book book = history.pop();

            System.out.print("Enter number of days the book was borrowed: ");
            int daysBorrowed = scanner.nextInt();

            bst.insert(book);
            bst.saveCatalogueToFile();

            System.out.println("Book returned successfully: " + book.getTitle());

            if (daysBorrowed > BORROW_LIMIT_DAYS) {
                int lateDays = daysBorrowed - BORROW_LIMIT_DAYS;
                double fine = lateDays * FINE_PER_DAY;

                System.out.println("Late by " + lateDays + " day(s).");
                System.out.println("Fine amount: RM" + fine);
            } else {
                System.out.println("No fine. Book returned on time.");
            }

        } else {
            System.out.println("No books to return.");
        }
    }

    @Override
    public void displayCatalogue() {
        bst.displayCatalogue();
    }

    public static void main(String[] args) {
        SmartLibrary library = new SmartLibrary();
        library.Menu();
    }
}