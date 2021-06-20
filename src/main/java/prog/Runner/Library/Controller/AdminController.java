package prog.Runner.Library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import prog.Runner.Library.Model.Status;
import prog.Runner.Library.Service.ServiceAdmin;
@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    ServiceAdmin serviceAdmin;







    /**
     * accept or reject request by get id of request and status
     * @param idRequest id of request
     * @param status accept or reject
     */
    @PostMapping(value = "acceptOrReject/{idRequest}/{status}")
    public void acceptOrReject(@PathVariable(value = "idRequest")int idRequest, @PathVariable(value = "status")String status){
        serviceAdmin.acceptOrRejectRequest(idRequest,Status.valueOf(status));


    }
















}
