package prog.Runner.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import prog.Runner.Library.Model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

    @Query
    public Admin findAdminByUsername(String name);

    @Query Admin findAdminById(int id);
}
