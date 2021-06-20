package prog.Runner.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prog.Runner.Library.Model.Author;
import prog.Runner.Library.Model.Book;
import prog.Runner.Library.Model.Genre;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query
    public List<Book> findBooksByAuthor(Author author);

    @Query
    public Book findBookByTitle(String name);

    @Query
    public Book findBookById(int id);

    @Query
    public void deleteBookByTitle(String name);

    @Query
    public List<Book> findBooksByGenre(Genre genre);

    @Query void deleteBookById(int id);

    @Query List<Book> findBooksByTitleContains(String str);


    @Query List<Book> findBooksByGenreAndTitleContains(Genre genre,String str);

    @Query
    public List<Book> findBooksByIsAvailableFalse();

    @Query
    public List<Book> findBooksByIsAvailableTrue();


}
