import java.util.ArrayList;
import java.util.List;
 class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean available;
    
    public Book() {
        this("N/A", "untitled", "unknown", true);
    }

    public Book(String isbn, String title, String author, boolean available) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = available;
    }
     public String getisbn() { return isbn; }
    public void setisbn(String isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Book{isbn='" + isbn + "', title='" + title + "', author='" + author
                + "', available=" + available + "}";
    }
} 
abstract class Member {
protected static final double FLAT_FEE = 5000;
private String memberId;
    private String name;
    private String email;
    private final List<Book> borrowedBooks = new ArrayList<>();

    public Member() {
        this("N/A", "Unknown", "N/A");
    }

    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            borrowedBooks.add(book);
        } else {
            System.out.println("Sorry, \"" + book.getTitle() + "\" is not available.");
        }
    }
public abstract double calculateFees();

    @Override
    public String toString() {
        return "memberId='" + memberId + "', name='" + name + "', email='" + email
                + "', booksBorrowed=" + borrowedBooks.size();
    }
}

class PublisherMember extends Member {
    private String publishingHouse;

    public PublisherMember() {
        super();
        this.publishingHouse = "N/A";
    }

    public PublisherMember(String memberId, String name, String email, String publishingHouse) {
        super(memberId, name, email);
        this.publishingHouse = publishingHouse;
    }

    public String getPublishingHouse() { return publishingHouse; }
    public void setPublishingHouse(String publishingHouse) { this.publishingHouse = publishingHouse; }
 @Override
    public double calculateFees() {
        return FLAT_FEE + (0.05 * FLAT_FEE) + 20000;
    }

    @Override
    public String toString() {
        return "PublisherMember{" + super.toString() + ", publishingHouse='" + publishingHouse
                + "', fees=" + calculateFees() + "}";
    }
}
class StudentMember extends Member {
    private String studentId;
    public StudentMember() {
        super();
        this.studentId = null;
    }

    public StudentMember(String memberId, String name, String email, String studentId) {
        super(memberId, name, email);
        this.studentId = studentId;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    @Override

     public double calculateFees() {
        if (studentId != null) {
            return 0;
        }
        return FLAT_FEE;
    }

    @Override
    public String toString() {
        return "StudentMember{" + super.toString() + ", studentId='" + studentId
                + "', fees=" + calculateFees() + "}";
    }
}  
        
    
class LibrarianMember extends Member {
    private String department;

    public LibrarianMember() {
        super();
        this.department = "N/A";
    }

    public LibrarianMember(String memberId, String name, String email, String department) {
        super(memberId, name, email);
        this.department = department;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    // Librarians pay nothing
    @Override
    public double calculateFees() {
        return 0;
    }

    @Override
    public String toString() {
        return "LibrarianMember{" + super.toString() + ", department='" + department
                + "', fees=" + calculateFees() + "}";
    }
}
public class LibrarySystem {
    public static void main(String[] args) {
        Book b1 = new Book("978-1", "Clean Code", "Robert C. Martin", true);
        Member[] members = {
            new PublisherMember("M001", "Alice", "alice@pub.com", "Kigali Press"),
            new StudentMember("M002", "Bob", "bob@uni.edu", "STU-2024-17"), // has ID -> 0
            new StudentMember("M003", "Carol", "carol@uni.edu", null),      // no ID  -> 5000
            new LibrarianMember("M004", "David", "david@lib.org", "Reference")
        };
        members[0].borrowBook(b1);

        for (Member m : members) {
             System.out.println(m);
            System.out.println("  -> Fees: " + m.calculateFees() + "\n");
        }
        System.out.println(b1);
    }
}