package prog.Runner.Library.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prog.Runner.Library.Model.Request;
import prog.Runner.Library.Model.Status;
import prog.Runner.Library.Repository.AdminRepository;
import prog.Runner.Library.Repository.RequestRepository;

import java.util.List;

@Service
public class ServiceRequests {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    ServiceUsers serviceUsers;
    @Autowired
    AdminRepository adminRepository;


    /**
     * update a request
     * @param request
     * @return allRequests
     */
    public void saveOrUpdateARequest(Request request){
        requestRepository.save(request);
    }



    public List<Request>deleteRequest(Request request){
        requestRepository.delete(request);
        return requestRepository.findAll();
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public List<Request>getAllIgnoredRequests(){
        return requestRepository.findRequestsByStatus(Status.Pending);
    }

    public List<Request>getAllAcceptedRequests(){
        return requestRepository.findRequestsByStatus(Status.Accepted);

    }
    public List<Request>getAllRejectedRequests(){
        return requestRepository.findRequestsByStatus(Status.Rejected);
    }


    public Request getRequestById(int id){
        return requestRepository.findRequestsById(id);
    }
}
