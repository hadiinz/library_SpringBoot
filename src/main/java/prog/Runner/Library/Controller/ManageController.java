package prog.Runner.Library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prog.Runner.Library.Model.*;
import prog.Runner.Library.Service.*;
import java.util.List;
@CrossOrigin
@RestController
public class ManageController {

    @Autowired
    ServiceBooks serviceBooks;
    @Autowired
    ServiceUsers serviceUsers;
    @Autowired
    ServiceAuthors serviceAuthors;
    @Autowired
    Manager manager;
    @Autowired
    ServiceAdmin serviceAdmin;



    @PostMapping("/login/{name}/{pass}")
    public int checkUser(@PathVariable(value = "name") String name,@PathVariable(value = "pass") String pass){

        return manager.findUserOrAdminAndGetId(name,pass);
    }



    /**
     * if this id is for an admin return ->true
     * else ->false
     * @param id suspect id
     * @return
     */
    @GetMapping(value = "/isAdmin/{id}")
    public Boolean isAdmin(@PathVariable(value = "id")int id){
        return manager.isAdminOrIsUser(id);
    }


    @PostMapping("/authorBooks")
    public List<Book> getBooksOfAuthor(@RequestParam String authorName){
        Author author1=serviceAuthors.getAuthorByName(authorName);
        return serviceBooks.getAuthorBooks(author1);
    }






/////////////////////////////////////add and delet user's book
    @PostMapping("/addBookToUser/{id}/{nameBook}/{day}")
    public User addBookToUser(@PathVariable(value = "id") int id,@PathVariable(value = "nameBook") String nameBook,@PathVariable(value = "day",required = false) int dayUse){
        //find book and user
        Book book=serviceBooks.getBookByName(nameBook);
        User user= (User) serviceUsers.getUserById(id);
        //add book to user
        manager.addBookToUser(book,user,dayUse);

        //save in
        serviceBooks.updateBook(book);
        serviceUsers.saveOrUpdateUser(user);

        return  user;

    }


    @PostMapping(value = "/deleteBookFromUser/{id}/{nameBook}")
    public List<Book> deleteBookFromUser(@PathVariable(value = "id")int idUser,@PathVariable(value = "nameBook")String nameBook){
        User user= (User) serviceUsers.getUserById(idUser);
        Book book=serviceBooks.getBookByName(nameBook);

        manager.deleteBookFromUser(idUser,book);
        user.getBookList().remove(book);
        return user.getBookList();
    }










}
