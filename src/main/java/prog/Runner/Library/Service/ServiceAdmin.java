package prog.Runner.Library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prog.Runner.Library.Model.Admin;
import prog.Runner.Library.Model.Book;
import prog.Runner.Library.Model.Request;
import prog.Runner.Library.Model.Status;
import prog.Runner.Library.Repository.AdminRepository;

@Service
public class ServiceAdmin {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ServiceRequests serviceRequests;
    @Autowired
    ServiceBooks serviceBooks;
    @Autowired
    Manager manager;






    /**
     * if the name is name of an admin return true
     * else return false
     * @param name suspect name
     * @return true or false
     */
    public Boolean isAdmin( String name){

        Boolean isAdmin=false;
        for (Admin a:adminRepository.findAll()
             ) {
            if (a.getUsername().equals(name)){
                isAdmin=true;
            }
        }
        return isAdmin;
    }



    /**accept or reject requests
     * get an id of request                get status {
     *     if request accepted all request for that book reject
     *          add book to user
     *          set isAvailable and Date
     *     if request rejected reject that request
     * }
     */

    public void acceptOrRejectRequest(int idRequest, Status status){
        System.out.println("statuuuuusuuus     j,jc,bc      "+status);
        Request request=serviceRequests.getRequestById(idRequest);
        System.out.println("before"+request.getStatus());
        Book book=serviceBooks.getBookByName(request.getBook().getTitle());

        if (status.equals(Status.Accepted)){
            request.setStatus(status);
            manager.addBookToUser(book,request.getUser(),7);
//            System.out.println(request.getStatus());
        }

        if (status.equals(Status.Rejected)){
            request.setStatus(status);
            if (book.getUser().equals(request.getUser())) {
                book.setIsAvailable(true);
                book.setStartDate(null);
                book.setFinishDate(null);
            }

//            System.out.println(request.getStatus());
        }
        serviceRequests.saveOrUpdateARequest(request);
//        serviceBooks.saveBook(book,book.getPublisher(),book.getAuthor());
        System.out.println("after"+request.getStatus());
    }


    public Admin getAdminByName(String name) {
        return adminRepository.findAdminByUsername(name);
    }
    public Admin getAdminById(int id){
        return adminRepository.findAdminById(id);
    }

}
