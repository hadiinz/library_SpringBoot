package prog.Runner.Library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import prog.Runner.Library.Model.Book;
import prog.Runner.Library.Model.Request;
import prog.Runner.Library.Model.Status;
import prog.Runner.Library.Model.User;
import prog.Runner.Library.Service.ServiceBooks;
import prog.Runner.Library.Service.ServiceRequests;
import prog.Runner.Library.Service.ServiceUsers;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    ServiceUsers serviceUsers;
    @Autowired
    ServiceBooks serviceBooks;
    @Autowired
    ServiceRequests serviceRequests;

    /**
     * add user by user name and pass
     * if you send repeated username  -> return false
     * if it add -> return true
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/addUser/{name}/{pass}")
    public Boolean addUser(@PathVariable (value = "name")String username,@PathVariable(value = "pass")String password){
        password= String.valueOf(password.hashCode());
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        return serviceUsers.saveOrUpdateUser( user);

    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return serviceUsers.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable(value = "id")int id){
            return  serviceUsers.getUserById(id);
    }

    @GetMapping(value = "/booksOfUser/{idUser}")
    public List<Book> allBooksOfUser(@PathVariable(value = "idUser")int idUser){
        User user=serviceUsers.getUserById(idUser);
        return user.getBookList();
    }

    /**
     * add request to a (user)
     * an user have requests for add book to his books
     * @param idUser
     * @return
     */

    @PostMapping(value = "/addRequest/{id}/{nameBook}")
    public List<Request> addReQuest( @PathVariable(value = "id") int idUser,@PathVariable(value = "nameBook")String nameBook){
        User user=serviceUsers.getUserById(idUser);
        Request request=new Request();
        request.setBook(serviceBooks.getBookByName(nameBook));
        request.setStatus(Status.valueOf("Pending"));
        request.setDate(LocalDate.now());
        //send a request by book and for setUser of request next line

        serviceUsers.addRequestToUser(user,request);
        return user.getRequests();
    }


    /**
     * READ
     * get all requests of user
     * @param idUser id of user
     * @return list of requests
     */
    @GetMapping(value = "/allRequests/{id}")
    public List<Request> getAllRequests(@PathVariable(value = "id") int idUser){
        User user= serviceUsers.getUserById(idUser);
        return user.getRequests();
    }




    @GetMapping(value = "acceptedRequests/{id}")
    public List acceptedReq(@PathVariable(value = "id")int idUser){
        User user=serviceUsers.getUserById(idUser);
        return serviceUsers.getAcceptedRequests(user);
    }

    /**
     * DELETE
     * delete user's request
     * @param idRequest
     * @param idUser
     * @return remains requests
     */
    @Transactional
    @DeleteMapping(value = "/deleteRequest/{idUser}/{idRequest}")
    public List<Request> deleteRequest(@PathVariable(value = "idRequest") int idRequest,@PathVariable(value = "idUser")int idUser){
        User user=serviceUsers.getUserById(idUser);
        Request request=serviceRequests.getRequestById(idRequest);
        return serviceUsers.deleteRequestFromUser(user,request);
    }


    ///////felan unused
    @PostMapping(value = "/editRequest/{idUser}/{idRequest}")
    public Request editRequest(@PathVariable(value = "idUser") int idUser,@PathVariable(value = "idRequest") int idRequest,@RequestBody Request newRequest){
        User user=serviceUsers.getUserById(idUser);
        return serviceUsers.editRequestOfAUser(user,idRequest,newRequest);
    }


    /**
     * search in user's request by book's name
     * @param idUser
     * @param nameBook
     * @return
     */
    @GetMapping (value = "/isInRequest/{id}/{nameBook}")
    public Boolean isBookInRequests(@PathVariable(value = "id")int idUser,@PathVariable(value = "nameBook")String nameBook){
        User user=serviceUsers.getUserById(idUser);
        Book book=serviceBooks.getBookByName(nameBook);

        return serviceUsers.isBookInRequests(user,book);




    }

    @PostMapping(value = "/addFavoriteBook/{idUser}/{nameBook}")
    public List<Book> addBookToFavoriteList(@PathVariable(value = "idUser")int idUser,@PathVariable(value = "nameBook")String nameBook){
        User user=serviceUsers.getUserById(idUser);
        Book book=serviceBooks.getBookByName(nameBook);
        serviceUsers.addFavoriteBook(user,book);
        serviceUsers.saveOrUpdateUser(user);
        return serviceUsers.getFavoriteBooks(user);
    }

    @GetMapping(value = "/isInFavoriteBooks/{idUser}/{nameBook}")
    public boolean isInFavoriteBook(@PathVariable(value = "idUser")int idUser,@PathVariable(value = "nameBook")String nameBook){
        User user =serviceUsers.getUserById(idUser);
        Book book=serviceBooks.getBookByName(nameBook);



        return serviceUsers.isInFavoriteBook(user,book);
    }

    @DeleteMapping(value = "/deleteFavoriteBook/{idUser}/{nameBook}")
    public void deleteFavoriteBook(@PathVariable(value = "idUser")int idUser,@PathVariable(value = "nameBook")String nameBook){
        Book book=serviceBooks.getBookByName(nameBook);
        User user=serviceUsers.getUserById(idUser);

        serviceUsers.deleteFromFavoriteBooks(user,book);
    }


    @GetMapping(value = "allFavoriteBooks/{idUser}")
    public List<Book> getFavoriteBook(@PathVariable(value = "idUser")int idUser){
        User user=serviceUsers.getUserById(idUser);
        return serviceUsers.getFavoriteBooks(user);
    }


//
//    @PostMapping(value = "addRating/{id}/{book}")
//    public Double addRating(@PathVariable(value = "id")int id,@PathVariable(value = "book")String nameBook,@RequestBody  int rateNumber){
//
//
//       return serviceBooks.updateRating(serviceBooks.getBookByName(nameBook),serviceUsers.getUserById(id),rateNumber);
//    }
//
//    @GetMapping(value = "getRating/{idUser}/{nameBook}")
//    public int getRatingOfUser(@PathVariable(value = "idUser")int id,@PathVariable(value = "nameBook")String nameBook){
//        return serviceBooks.getRatingOfUser(serviceUsers.getUserById(id),serviceBooks.getBookByName(nameBook));
//    }
//
}
