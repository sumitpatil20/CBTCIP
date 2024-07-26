import java.util.HashMap;
import java.util.Scanner;

class Book {
    String bookId;
    String title;
    String author;
    boolean isIssued;

    Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Issued: " + isIssued;
    }
}

class Member {
    String memberId;
    String name;

    Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId + ", Name: " + name;
    }
}

class Admin {
    private HashMap<String, Book> books = new HashMap<>();
    private HashMap<String, Member> members = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public HashMap<String, Book> getBooks() {
        return books;
    }

    public void menu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View Books");
            System.out.println("4. Add Member");
            System.out.println("5. Remove Member");
            System.out.println("6. View Members");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    viewBooks();
                    break;
                case 4:
                    addMember();
                    break;
                case 5:
                    removeMember();
                    break;
                case 6:
                    viewMembers();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        books.put(bookId, new Book(bookId, title, author));
        System.out.println("Book added successfully.");
    }

    private void removeBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        if (books.remove(bookId) != null) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books.values()) {
                System.out.println(book);
            }
        }
    }

    private void addMember() {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        members.put(memberId, new Member(memberId, name));
        System.out.println("Member added successfully.");
    }

    private void removeMember() {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        if (members.remove(memberId) != null) {
            System.out.println("Member removed successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }

    private void viewMembers() {
        if (members.isEmpty()) {
            System.out.println("No members available.");
        } else {
            for (Member member : members.values()) {
                System.out.println(member);
            }
        }
    }
}

class User1 {
    private HashMap<String, Book> books;
    private Scanner scanner = new Scanner(System.in);

    public User1(HashMap<String, Book> books) {
        this.books = books;
    }

    public void menu() {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View Books");
            System.out.println("2. Search Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    viewBooks();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    issueBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books.values()) {
                System.out.println(book);
            }
        }
    }

    private void searchBook() {
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        boolean found = false;
        for (Book book : books.values()) {
            if (book.title.equalsIgnoreCase(title)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }

    private void issueBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        Book book = books.get(bookId);
        if (book != null && !book.isIssued) {
            book.isIssued = true;
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Book not found or already issued.");
        }
    }

    private void returnBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        Book book = books.get(bookId);
        if (book != null && book.isIssued) {
            book.isIssued = false;
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book not found or was not issued.");
        }
    }
}

public class DigitalLibraryManagement {
    public static void main(String[] args) {
        Admin admin = new Admin();
        User1 user = new User1(admin.getBooks());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (option == 1) {
                admin.menu();
            } else if (option == 2) {
                user.menu();
            } else if (option == 3) {
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }
}

