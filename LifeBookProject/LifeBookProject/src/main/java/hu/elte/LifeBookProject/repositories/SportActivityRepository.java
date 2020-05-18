
package hu.elte.LifeBookProject.repositories;

import hu.elte.LifeBookProject.entities.SportActivity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportActivityRepository extends CrudRepository<SportActivity, Long> {

    @Query("SELECT s FROM SportActivity s WHERE s.name = ?1")
    SportActivity getByName(String name);

    @Query("SELECT s FROM SportActivity s WHERE s.regularity = ?1 ORDER BY s.name ASC")
    List<SportActivity> getByRegularity(String regularity);

    @Query("SELECT s FROM SportActivity s WHERE s.isOfficial = ?1 ORDER BY s.name ASC")
    List<SportActivity> getByOfficiality(boolean official);
}
