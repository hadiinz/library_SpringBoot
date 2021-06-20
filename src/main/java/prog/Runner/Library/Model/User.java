package prog.Runner.Library.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    //unique name
    @Column(unique = true,nullable = false)
    protected   String username;

    //password
    @Column
    protected String password;

    @JsonBackReference(value = "book")
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Book> bookList;

    @JsonBackReference(value = "requests")
    @OneToMany(mappedBy = "user")
    private List<Request> requests;


    @JsonBackReference(value = "favoriteBooks")
    @ManyToMany()//cant add to many users
    @JoinTable(name = "favoriteBooks")
    private List<Book> favoriteBooks;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(List<Book> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }
}
