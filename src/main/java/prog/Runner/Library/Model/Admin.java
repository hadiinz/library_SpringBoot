package prog.Runner.Library.Model;

import org.hibernate.annotations.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import prog.Runner.Library.Repository.RequestRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class Admin  {

    @Id
    protected int id;

    //unique name
    @Column(unique = true,nullable = false)
    protected   String username;

    //password
    @Column
    protected String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Admin(String name,String pass){
        setUsername(name);
        setPassword(pass);
    }

    public Admin() {

    }

}
