
package hu.elte.LifeBookProject.repositories;

import hu.elte.LifeBookProject.entities.EatingHabit;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EatingHabitRepository extends CrudRepository<EatingHabit, Long>{

    @Query("SELECT e FROM EatingHabit e WHERE e.type = ?1 ORDER BY e.name ASC")
    public List<EatingHabit> getByType(String type);

    @Query("SELECT e FROM EatingHabit e WHERE e.isFood = ?1 ORDER BY e.name ASC")
    public List<EatingHabit> getByIsFood(boolean eating);

    @Query("SELECT e FROM EatingHabit e WHERE e.name = ?1")
    public EatingHabit getByName(String name);
    
}
