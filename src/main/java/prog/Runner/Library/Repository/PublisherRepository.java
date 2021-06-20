package prog.Runner.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prog.Runner.Library.Model.Publisher;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Integer> {
    @Query
    public Publisher findPublisherByName(String name);

    @Query
    public void deletePublisherByName(String name);

    @Query
    public List findPublishersByNameContains(String st);
}
