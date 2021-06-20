package prog.Runner.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prog.Runner.Library.Model.Request;
import prog.Runner.Library.Model.Status;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {
    @Query
    List findRequestsByStatus(Status status);

    @Query
    Request findRequestsById(int id);
}
