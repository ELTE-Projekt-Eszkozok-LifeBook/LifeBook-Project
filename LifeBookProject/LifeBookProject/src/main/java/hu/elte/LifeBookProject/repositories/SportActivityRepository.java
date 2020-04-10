
package hu.elte.LifeBookProject.repositories;

import hu.elte.LifeBookProject.entities.SportActivity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportActivityRepository extends CrudRepository<SportActivity, Long> {

    @Query("SELECT s FROM SportActivity s WHERE s.name = ?1")
    public SportActivity getByName(String name);

    @Query("SELECT s FROM SportActivity s WHERE s.regularity = ?1 ORDER BY s.name ASC")
    public List<SportActivity> getByRegularity(String regularity);

    @Query("SELECT s FROM SportActivity s WHERE s.isOfficial = ?1 ORDER BY s.name ASC")
    public List<SportActivity> getByOfficiality(boolean official);
}
