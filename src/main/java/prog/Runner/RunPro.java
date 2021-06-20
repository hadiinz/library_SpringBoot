package prog.Runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import prog.Runner.Library.Repository.AdminRepository;
import prog.Runner.Library.Service.ServiceAdmin;

@Configuration
@SpringBootApplication
public class RunPro {
    public static void main(String[] args)
    {

        SpringApplication.run(RunPro.class, args);

    }
}
