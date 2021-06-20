package prog.Runner.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prog.Runner.Library.Model.Author;
import prog.Runner.Library.Model.Book;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    @Query
    public Author findAuthorByName(String name);

    @Query
    public void deleteAuthorByName(String name);

    @Query
    public List<Author> findAuthorsByNameContains(String  str);
}
