package smartlibrary;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class BookSearchBST {

    private BookSearchNode root;

    // Insert a book into the BST
    public void insert(Book book) {
        root = insertRec(root, book);
    }

    private BookSearchNode insertRec(BookSearchNode node, Book book) {
        if (node == null) {
            return new BookSearchNode(book);
        }

        if (book.getIsbn() < node.book.getIsbn()) {
            node.left = insertRec(node.left, book);
        } else if (book.getIsbn() > node.book.getIsbn()) {
            node.right = insertRec(node.right, book);
        }

        return node;
    }

    // TASK 3: RECURSIVE SEARCH FUNCTION
    public Book search(int isbn) {
        return searchRec(root, isbn);
    }

    // Recursive helper function to search by ISBN
    private Book searchRec(BookSearchNode node, int isbn) {
        if (node == null) {
            return null;
        }

        if (node.book.getIsbn() == isbn) {
            return node.book;
        }

        if (isbn < node.book.getIsbn()) {
            return searchRec(node.left, isbn);
        }

        return searchRec(node.right, isbn);
    }

    // Display full catalogue in console
    public void displayCatalogue() {
        if (root == null) {
            System.out.println("Catalogue is empty.");
            return;
        }

        System.out.println("\n--- Full Book Catalogue ---");
        displayCatalogueRec(root);
        System.out.println("----------------------------");
    }

    private void displayCatalogueRec(BookSearchNode node) {
        if (node != null) {
            displayCatalogueRec(node.left);

            System.out.println(
                    "ISBN: " + node.book.getIsbn()
                            + " | Title: " + node.book.getTitle()
                            + " | Author: " + node.book.getAuthor()
            );

            displayCatalogueRec(node.right);
        }
    }

    // Save current BST catalogue to catalogue.txt
    public void saveCatalogueToFile() {
        try {
            FileWriter writer = new FileWriter("catalogue.txt");

            saveCatalogueRec(root, writer);

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving catalogue to file.");
        }
    }

    private void saveCatalogueRec(BookSearchNode node, FileWriter writer) throws IOException {
        if (node != null) {
            saveCatalogueRec(node.left, writer);

            writer.write(
                    node.book.getIsbn() + "|"
                            + node.book.getTitle() + "|"
                            + node.book.getAuthor() + "\n"
            );

            saveCatalogueRec(node.right, writer);
        }
    }

    // Load catalogue from catalogue.txt when program starts
    public void loadCatalogueFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("catalogue.txt"));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if (parts.length == 3) {
                    int isbn = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];

                    insert(new Book(isbn, title, author));
                }
            }

            reader.close();

        } catch (IOException e) {
            // File may not exist the first time, so no problem.
        } catch (NumberFormatException e) {
            System.out.println("Error reading ISBN from catalogue file.");
        }
    }

    // Delete a book from the BST
    public void delete(int isbn) {
        root = deleteRec(root, isbn);
    }

    private BookSearchNode deleteRec(BookSearchNode node, int isbn) {
        if (node == null) {
            return null;
        }

        if (isbn < node.book.getIsbn()) {
            node.left = deleteRec(node.left, isbn);
        } else if (isbn > node.book.getIsbn()) {
            node.right = deleteRec(node.right, isbn);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            BookSearchNode minRight = findMin(node.right);
            node.book = minRight.book;
            node.right = deleteRec(node.right, minRight.book.getIsbn());
        }

        return node;
    }

    private BookSearchNode findMin(BookSearchNode node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }
}