package prog.Runner.ConfigsAndClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import prog.Runner.Library.Model.Admin;
import prog.Runner.Library.Model.User;
import prog.Runner.Library.Repository.AdminRepository;
import prog.Runner.Library.Repository.UserRepository;
import prog.Runner.Library.Service.ServiceRequests;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    ServiceRequests serviceRequests;

    @Override
    public void run(String...args) throws Exception {
        Admin admin=new Admin("admin",String.valueOf("admin".hashCode()));

        /// add admin for first time
       adminRepository.save(admin);
//        adminRepository.findAdminById(0).setRequests(serviceRequests.getAllRequests());

    }
}