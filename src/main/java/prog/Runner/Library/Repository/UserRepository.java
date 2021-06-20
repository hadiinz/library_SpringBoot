package prog.Runner.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prog.Runner.Library.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query
    public User getUserByUsername(String username);

    @Query
    public void deleteUserByUsername(String name);

    @Query
    public User getUserById(int id);


}
