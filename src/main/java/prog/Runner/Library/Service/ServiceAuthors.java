package prog.Runner.Library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prog.Runner.Library.Model.Author;
import prog.Runner.Library.Model.Book;
import prog.Runner.Library.Model.Publisher;
import prog.Runner.Library.Repository.AuthorRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceAuthors {
    @Autowired
    AuthorRepository authorRepository;

    /**
     * READ
     * return an author that searched by name in Repository
     * @param name
     * @return author
     */
    public Author getAuthorByName(String name){
        return authorRepository.findAuthorByName(name);
    }

    /**
     * READ
     * return all authors
     * @return list of author
     */
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();

    }




    /**
     * DELETE
     * delete an author by name
     * @param name name of author that you wanna delete
     */

    public void deleteAuthorByNAme(String name){
        authorRepository.deleteAuthorByName(name);

    }




    /**
     * UPDATE CREATE
     * save new author or update an old author
     * @param author
     * @return author
     */
    public Author saveOrUpdateAuthor(Author author){
        authorRepository.save(author);
        return author;
    }

    /**
     * return books of author
     * @param nameAuthor
     * @return list of books
     */
    public List<Book> getBooksOfAuthor(String nameAuthor){
        Author author=authorRepository.findAuthorByName(nameAuthor);
        return author.getBooks();
    }


    /**
     * return list of authors that their names contains str
     * @param str string for search
     * @return list of authors
     */
    public List<Author>getAuthorsContainStr(String str){
        List <Author> authors;
        if (authorRepository.findAuthorsByNameContains(str).size()==0){
            authors=new ArrayList<>();
        }else  authors=authorRepository.findAuthorsByNameContains(str);
        return authors;
    }


    public Author editAuthor(String nameOfOldAuthor, Author newAuthor){
        Author oldAuthor=authorRepository.findAuthorByName(nameOfOldAuthor);
        oldAuthor.setName(newAuthor.getName());
        oldAuthor.setBornDate(newAuthor.getBornDate());
        oldAuthor.setDieDate(newAuthor.getDieDate());

        authorRepository.save(oldAuthor);
        return oldAuthor;
    }
}
