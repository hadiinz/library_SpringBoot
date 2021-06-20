package prog.Runner.Library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import prog.Runner.Library.Model.*;
import prog.Runner.Library.Repository.BookRepository;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ServiceBooks {
    @Autowired
    private BookRepository bookRepository;


    /**
     * Create
     * update
     * save new book or update old book in repository
     * @param book choosed book for update
     */
    public void saveBook(Book book, Publisher publisher, Author author){
        book.setAuthor(author);
        Boolean b1=true;
        Boolean b2=true;
        if (publisher!=null&&author!=null) {

            for (Book b : publisher.getBookList()
            ) {
                if (b.getTitle().equals(book.getTitle())) {
                    b1 = false;
                }
            }
            for (Book b : author.getBooks()) {
                if (b.getTitle().equals(book.getTitle())) {
                    b2 = false;
                }
            }
            if (b1==true) {

                publisher.getBookList().add(book);
                book.setPublisher(publisher);
            }
            if (b2==true){

                author.getBooks().add(book);
                book.setAuthor(author);
            }
        }


        bookRepository.save(book);
    }

    public void updateBook(Book book){
        bookRepository.save(book);
    }
    /**
     * Read
     * @return all books of repository
     */
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    /**
     * return author's books
     * @param author
     * @return all books of an author
     */
    public List getAuthorBooks(Author author){
        return bookRepository.findBooksByAuthor(author);
    }


    /**
     * Read
     *
     * @param bookName
     * @return a book by name from repository
     */
    public Book getBookByName(String bookName){
        return bookRepository.findBookByTitle(bookName);
    }


    /**
     * Read
     * @param id
     * @return a book by id
     */
    public Book getBookById(int id){
        return bookRepository.findBookById(id);
    }


    /**
     * Delete
     * delete the book by name
     * @param name name of Book
     */

    public void deleteBookByName(String name){
        int id=bookRepository.findBookByTitle(name).getId();

        bookRepository.deleteBookById(id);
    }

    /**
     * Read
     * return books that have same Genre
     * @param genre genre of books
     * @return same genre books
     */
    public List<Book> getBooksSameGenre(Genre genre){
        List<Book> books=bookRepository.findBooksByGenre(genre);
        return books;
    }

    ///new
    public List<Book>getListOfSameGenreAndContainsStr(Genre genre,String str){
        return bookRepository.findBooksByGenreAndTitleContains(genre,str);
    }


    /**
     * edite
     * @param nameOfOldBook
     * @param newBook
     * @return
     */
    public Book editBook(String nameOfOldBook, Book newBook){
        Book oldBook=bookRepository.findBookByTitle(nameOfOldBook);
        oldBook.setTitle(newBook.getTitle());
        oldBook.setGenre(newBook.getGenre());
        oldBook.setPublicationDate(newBook.getPublicationDate());
        oldBook.setRating(newBook.getRating());
        oldBook.setSummary(newBook.getSummary());

        oldBook.setIsAvailable(newBook.getIsAvailable());
        oldBook.setFinishDate(newBook.getFinishDate());
        oldBook.setStartDate(newBook.getStartDate());


        ////////edit Author
        Author oldAuthor=oldBook.getAuthor();
        Author newAuthor=newBook.getAuthor();

        oldBook.setAuthor(newAuthor);
        oldAuthor.getBooks().remove(oldBook);
        if (newAuthor.getBooks()==null){
            newAuthor.setBooks(new ArrayList<Book>());
        }
        newAuthor.getBooks().add(oldBook);

        /////////edit Publisher
        Publisher oldPublisher=oldBook.getPublisher();
        Publisher newPublisher=newBook.getPublisher();
        oldBook.setPublisher(newPublisher);
        oldPublisher.getBookList().remove(oldBook);
        if (newPublisher.getBookList()==null){
            newPublisher.setBookList(new ArrayList<Book>());
        }
        newPublisher.getBookList().add(oldBook);


        bookRepository.save(oldBook);
        return oldBook;
    }
    public List<Book> findBooksContainsStr(String str){
        return bookRepository.findBooksByTitleContains(str);
    }


//
//    public Double updateRating(Book book, User user,int rateNumber){
//        int sum=0;
//        Double average= Double.valueOf(0);
//        int numbersOfRates=book.getRates().size();
//        for (Map.Entry<User,Rate> r:        book.getRates().entrySet()
//        ) {
//            sum+=r.getValue().getRateNumber();
//
//        }
//
//        Rate rate=new Rate();
//        rate.setRateNumber(rateNumber);
//        book.getRates().put(user,rate);
//
//        average= Double.valueOf((sum+rateNumber)/(numbersOfRates+1));
//        book.setRating(average);
//        bookRepository.save(book);
//        return average;
//
//    }
//
//
//    public int getRatingOfUser(User user,Book book){
//        return book.getRates().get(user).getRateNumber();
//    }

}
