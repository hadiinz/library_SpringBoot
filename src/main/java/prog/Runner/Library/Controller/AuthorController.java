package prog.Runner.Library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prog.Runner.Library.Model.Author;
import prog.Runner.Library.Model.Book;
import prog.Runner.Library.Service.ServiceAuthors;

import javax.transaction.Transactional;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    ServiceAuthors serviceAuthors;

    /**
     * CREATE author
     * @param author new Author
     * @return all authors
     */
    @PostMapping("/addAuthor")
    public List<Author> createAuthor(@RequestBody Author author){
        serviceAuthors.saveOrUpdateAuthor(author);
        return serviceAuthors.getAllAuthors();
    }


    /**
     * READ
     * read an author by search in Repository
     * @param nameAuthor
     * @return author
     */
    @GetMapping("/{name}")
    public Author getAuthorByName(@PathVariable (value = "name") String nameAuthor){
        Author author=serviceAuthors.getAuthorByName(nameAuthor);
        return author;
    }


    /**
     * READ
     * get all authors
     * @return all authors
     */
    @GetMapping("/allAuthors")
    public List<Author> getAllAuthor(){
        return serviceAuthors.getAllAuthors();
    }

    /**
     * DELETE
     * delete author by name
     * @param nameAuthor
     * @return all author
     */
    @Transactional
    @DeleteMapping("/delete/{name}")
    public List<Author> deleteAuthorByName(@PathVariable (value = "name") String nameAuthor){
        serviceAuthors.deleteAuthorByNAme(nameAuthor);
        return serviceAuthors.getAllAuthors();
    }

    /**
     * return all books of an author
     * @param nameAuthor
     * @return list of books
     */
    @GetMapping("/books/{nameAuthor}")
    public List<Book> getBooksOfAuthor(@PathVariable (value = "nameAuthor") String nameAuthor){
        List books=serviceAuthors.getBooksOfAuthor(nameAuthor);
        return books;
    }


    @GetMapping(value = "/search/{str}")
    public List<Author>search(@PathVariable(value = "str")String str){
        if (str.equals(null)||str.equals(""))return serviceAuthors.getAllAuthors();
        return serviceAuthors.getAuthorsContainStr(str);
    }

    @PostMapping(value = "/edit/{name}")
    public Author edit(@RequestBody Author newAuthor, @PathVariable (value = "name")String name){
        return serviceAuthors.editAuthor(name,newAuthor);
    }
}
