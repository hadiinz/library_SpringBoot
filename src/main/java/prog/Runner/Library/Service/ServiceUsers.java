package prog.Runner.Library.Service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prog.Runner.Library.Model.Book;
import prog.Runner.Library.Model.Request;
import prog.Runner.Library.Model.Status;
import prog.Runner.Library.Model.User;
import prog.Runner.Library.Repository.RequestRepository;
import prog.Runner.Library.Repository.UserRepository;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceUsers {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRequests serviceRequests;

    @Autowired
    private ServiceAdmin serviceAdmin;

    /**
     * CREATE OR SAVE
     * @param user
     * @return
     */
    public boolean saveOrUpdateUser(User user){
        if (!serviceAdmin.isAdmin(user.getUsername())){
            userRepository.save(user);
            return true;
        }else return false;

    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.getUserById(id);
    }

    public User getUserByUsername(String name){
        return userRepository.getUserByUsername(name);
    }

    /**
     * send request
     * @param user user that send request
     * @param request request
     */
    public void addRequestToUser(User user,Request request){



        Boolean flag=false;
        ///search in requests of user for un repeated request for one book
        if (user.getRequests()==null)user.setRequests(new ArrayList<>());
        for (Request r:user.getRequests()
             ) {
            if (r.getBook().getTitle().equals(request.getBook().getTitle())){
                // means its repeated
                flag=true;
            }
        }
        if (flag==false){
            //un repeated request for a book
            request.setUser(user);
            user.getRequests().add(request);
        }




        if (request.getBook()!=null){
            Book book=request.getBook();
            if (book.getRequest()==null)book.setRequest(new ArrayList<>());
            Boolean flag2=false;
            for (Request r:book.getRequest()
                 ) {
                if (r.getUser().getUsername().equals(request.getUser().getUsername())){
                    //if for one book one user didnt send request
                    flag2=true;
                }
            }
            if (flag2==false){
                book.getRequest().add(request);
            }
        }



        userRepository.save(user);
        serviceRequests.saveOrUpdateARequest(request);
    }


    /**
     * delete request from user's request
     * @param user user
     * @param request want to delete request
     * @return list of remain requests of user
     */
    public List<Request> deleteRequestFromUser(User user,Request request){
        if (user.getRequests().contains(request)){
            user.getRequests().remove(request);
            request.setUser(null);
            userRepository.save(user);
            serviceRequests.deleteRequest(request);
            return user.getRequests();
        }
        return null;
    }

    /////////////un used
    public Request editRequestOfAUser(User user,int idOldRequest,Request newRequest){
        Request oldRequest=serviceRequests.getRequestById(idOldRequest);
        if (user.getRequests().contains(oldRequest)){
            int indexOfOldRequest=user.getRequests().indexOf(oldRequest);
            oldRequest=user.getRequests().get(indexOfOldRequest);
            oldRequest.setBook(newRequest.getBook());
            oldRequest.setStatus(newRequest.getStatus());

            userRepository.save(user);
            serviceRequests.saveOrUpdateARequest(oldRequest);
            return oldRequest;
        }
        return null;
    }

    /**
     * if book is in requests of an user return true
     * else false
     * @param user user
     * @param book book for search
     * @return true or false
     */
    public Boolean isBookInRequests(User user,Book book){
        Boolean flag=false;
        if (user!=null&&book!=null) {
            for (Request r : user.getRequests()
            ) {
                if (r.getBook().getTitle().equals
                        (book.getTitle())) {
                    flag = true;
                }
            }
        }
        return flag;
    }






    public boolean isInFavoriteBook(User user,Book book){
        Boolean flag=false;
        if (user!=null&&book!=null){
            System.out.println("user and book isnt null");
            for (Book b:user.getFavoriteBooks()
                 ) {
                System.out.println("fB    "+b.getTitle());
                if (b.getTitle().equals(book.getTitle())){
                    flag=true;
                    System.out.println("user has this bookname"+book.getTitle());
                }
            }


        }
        System.out.println("flag is "+flag);
       return flag;

    }

    public void addFavoriteBook(User user,Book book){
        if (user.getFavoriteBooks()==null){
            System.out.println("listOfFavorite is null");
            user.setFavoriteBooks(new ArrayList<>());
        }

        if (!isInFavoriteBook(user,book)){
            System.out.println("added favoriteBook"+book.getTitle());
            user.getFavoriteBooks().add(book);
            book.getUsersOfFavorite().add(user);
            userRepository.save(user);

        }
    }


    public void deleteFromFavoriteBooks(User user,Book book){
        if (user!=null){
            if (isInFavoriteBook(user,book)){
                user.getFavoriteBooks().remove(book);
                book.getUsersOfFavorite().remove(user);

            }

            saveOrUpdateUser(user);


        }
    }


    /**
     * get user's accepted requests
     * @param user user
     * @return list of accepted
     */
    public List<Request> getAcceptedRequests(User user){
        List<Request> acceptedRequest=new ArrayList<>();
        for (Request r:user.getRequests()
             ) {
            if (r.getStatus().equals(Status.Accepted)){
                acceptedRequest.add(r);
            }

        }
        return acceptedRequest;
    }











    public List<Book> getFavoriteBooks(User user){
        return  user.getFavoriteBooks();
    }

}
