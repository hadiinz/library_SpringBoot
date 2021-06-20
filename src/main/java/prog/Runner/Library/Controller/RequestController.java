package prog.Runner.Library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prog.Runner.Library.Model.Request;
import prog.Runner.Library.Service.ServiceAdmin;
import prog.Runner.Library.Service.ServiceRequests;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "request")
public class RequestController {
    @Autowired
    ServiceRequests serviceRequests;




    ///////Read
    @GetMapping(value = "/allRequests")
    public List<Request> getAllRequests(){
        return serviceRequests.getAllRequests();
    }
    @GetMapping(value = "/acceptedRequests")
    public List<Request> getAcceptedRequests(){
        return serviceRequests.getAllAcceptedRequests();
    }
    @GetMapping(value = "/rejectedRequests")
    public List<Request> getRejectedRequests(){
        return serviceRequests.getAllRejectedRequests();
    }

    @GetMapping(value = "/pendingRequests")
    public  List<Request> getPendingRequests(){
        return serviceRequests.getAllIgnoredRequests();
    }











}
