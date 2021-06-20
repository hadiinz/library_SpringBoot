package prog.Runner.Library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prog.Runner.Library.Model.*;
import prog.Runner.Library.Service.ServiceAuthors;
import prog.Runner.Library.Service.ServiceBooks;
import prog.Runner.Library.Service.ServicePublishers;

import javax.transaction.Transactional;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    ServiceBooks serviceBooks;
    @Autowired
    ServiceAuthors serviceAuthors;
    @Autowired
    ServicePublishers servicePublishers;


////////add book with author and publisher
    /**
     * Create
     * @param book
     * @return list of all books
     */
    @PostMapping(value = "/addBook/{nameAuthor}/{namePublisher}")
    public List<Book> createBook(@RequestBody Book book,@PathVariable(value = "nameAuthor")String nameAuthor,@PathVariable(value = "namePublisher")String namePublisher){
        Publisher publisher=servicePublishers.getPublisherByName(namePublisher);
        Author author=serviceAuthors.getAuthorByName(nameAuthor);
        System.out.println(publisher.getId());

        serviceBooks.saveBook(book,publisher,author);
        return serviceBooks.getAllBooks();
    }


    /**
     * Read
     * @return all books in reposi
     */
    @GetMapping("/allBooks")
    public List<Book> getBooks(){
        return serviceBooks.getAllBooks();
    }

    /**
     * Read a book by name in service book
     * @param name
     * @return book
     */
    @GetMapping("/{name}")
    public Book getBookByName(@PathVariable(value = "name") String name ){
        Book book=serviceBooks.getBookByName(name);
       return book;
    }



    /**
     * return books that have same genre
     * @param genre
     * @return books
     */
    @GetMapping("/sameGenre/{genre}")
    public  List<Book> getSameBooksGenre(@PathVariable(value = "genre") String genre){
        System.out.println(Genre.valueOf(genre));

        return serviceBooks.getBooksSameGenre(Genre.valueOf(genre));

    }

    @GetMapping(value = "sameGenre/search/{genre}/{str}")
    public List<Book> searchInSameGenre(@PathVariable(value = "genre")String  genre,@PathVariable(value = "str")String str){
        return serviceBooks.getListOfSameGenreAndContainsStr(Genre.valueOf(genre),str);
    }

    /**
     * delete a book by search name in serviceBook and Repository
     * @param nameBook book that should delete
     * @return book that deleted
     */
    @Transactional
    @DeleteMapping("/delete/{name}")
    public List<Book> deleteBookByName(@PathVariable(value = "name") String nameBook){
      //  Book book=serviceBooks.getBookByName(nameBook);
        serviceBooks.deleteBookByName(nameBook);
        return serviceBooks.getAllBooks();

    }


    @GetMapping(value = "/search/{str}")
    public List<Book > findBookContainsStr(@PathVariable(value = "str")String str){
        return serviceBooks.findBooksContainsStr(str);
    }



    @PostMapping(value = "/edit/{name}")
    public Book editBookByName(@PathVariable(value = "name") String nameOldBook,@RequestBody Book newBook){
        return serviceBooks.editBook(nameOldBook,newBook);
    }
}
