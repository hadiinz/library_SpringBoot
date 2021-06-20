package prog.Runner.Library.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.net.UnknownServiceException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


@Entity
@Table()
@JsonDeserialize
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    //unique id
    private int id;

    @Column(unique = true,nullable = false)
    //unique name
    private String title;

    @Column
    private String summary;

    @Column
    private Double rating;

///////test
    @JsonBackReference(value = "request")
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    @Column
    List<Request> request;



    @ManyToOne
    @JoinColumn()
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher")
    private Publisher publisher;



    @Column
    private int publicationDate;

    @Column //(nullable = false)
    private Genre genre;


    @JoinColumn
    @ManyToOne
    private User user;


    @ManyToMany(mappedBy = "favoriteBooks",cascade = CascadeType.ALL)
    private List<User> usersOfFavorite;


    ///for reserve book
    @Column
    private boolean isAvailable=true;


    /////newwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
//
//    @OneToMany
//    @JsonBackReference(value = "rate")
//    @JoinTable(name = "rates")
//    private Map<  User , Rate> rates=new HashMap<>();



    @Column
    @JsonFormat( pattern="yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;
    @Column
    @JsonFormat( pattern="yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private  LocalDate finishDate;
    ///



    //getters and setters

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }


    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }


    public List<User> getUsersOfFavorite() {
        return usersOfFavorite;
    }

    public void setUsersOfFavorite(List<User> usersOfFavorite) {
        this.usersOfFavorite = usersOfFavorite;
    }

//    public Map<User, Rate> getRates() {
//        return rates;
//    }
//
//    public void setRates(Map<User, Rate> rates) {
//        this.rates = rates;
//    }


}
