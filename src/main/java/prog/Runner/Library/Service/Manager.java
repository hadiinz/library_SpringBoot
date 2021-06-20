package prog.Runner.Library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prog.Runner.Library.Model.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class Manager {
    @Autowired
    ServiceBooks serviceBooks;
    @Autowired
    ServiceUsers serviceUsers;
    @Autowired
    ServiceAuthors serviceAuthors;
    @Autowired
    ServicePublishers servicePublishers;
    @Autowired
    ServiceRequests serviceRequests;
    @Autowired
    ServiceAdmin serviceAdmin;
///////add and delete book to user
    public User addBookToUser(Book book,User user,int dayForUse){
        if (book.getIsAvailable()) {
            user.getBookList().add(book);

            book.setUser(user);
            book.setIsAvailable(false);
            book.setStartDate(LocalDate.now());
            book.setFinishDate(LocalDate.now().plus(Period.ofDays(dayForUse)));


            serviceUsers.saveOrUpdateUser(user);
            serviceBooks.updateBook(book);
            return user;
        }
        else {
            //leave a log
            return null;
        }

    }


    public List<Book>deleteBookFromUser(int idUser,Book book){
        User user=serviceUsers.getUserById(idUser);
        if (user.getBookList().contains(book)) {
            user.getBookList().remove(book);
            book.setUser(null);
            book.setIsAvailable(true);
            book.setStartDate(null);
            book.setFinishDate(null);
        }
        serviceBooks.updateBook(book);
        serviceUsers.saveOrUpdateUser(user);

        return user.getBookList();
    }


/////////////


    /**
     * the main method for login and get id of user or admin
     * @param name name of user or admin
     * @param pass password
     * @return -1->>>>>if doesn't exist
     */
    public int findUserOrAdminAndGetId(String name, String pass){
        User user= serviceUsers.getUserByUsername(name);
        Admin admin=serviceAdmin.getAdminByName(name);
        String pass1= String.valueOf(pass.hashCode());
        if (user!=null&&user.getPassword().equals(pass1)){
            return user.getId();
        }
        else if (admin!=null && admin.getPassword().equals(pass1)){
            return admin.getId();
        }

        else return -1;

    }


    public Boolean isAdminOrIsUser(int id){
        User user=serviceUsers.getUserById(id);
        Admin admin=serviceAdmin.getAdminById(id);
        if (user!=null )return  false;
        else return true;
    }


//
//    public List<Book> addBookToAuthor(String nameBook,String nameAuthor){
//        Book book=serviceBooks.getBookByName(nameBook);
//        Author author= serviceAuthors.getAuthorByName(nameAuthor);
//        book.setAuthor(author);
//        author.getBooks().add(book);
//        serviceBooks.updateBook(book);
//        serviceAuthors.saveOrUpdateAuthor(author);
//        return serviceBooks.getAllBooks();
//    }
//
//    public List<Book> addBookToPublisher(String nameBook,String namePublisher){
//        Book book=serviceBooks.getBookByName(nameBook);
//        Publisher publisher= servicePublishers.getPublisherByName(namePublisher);
//
//        publisher.getBookList().add(book);
//        book.setPublisher(publisher);
//
//        serviceBooks.updateBook(book);
//        servicePublishers.saveOrUpdate(publisher);
//
//        return serviceBooks.getAllBooks();
//    }


//
//
//    public List<Request> addRequestToUser(User user,Request request){
//        user.getRequests().add(request);
//        request.setUser(user);
//        serviceUsers.saveOrUpdateUser(user);
//        serviceRequests.saveOrUpdateARequest(request);
//
//        return user.getRequests();
//    }




}
