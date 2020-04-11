
package hu.elte.LifeBookProject.repositories;

import hu.elte.LifeBookProject.entities.TimeTable;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TimeTableRepository extends CrudRepository<TimeTable, Long> {

    @Query("SELECT e FROM TimeTable e WHERE e.date >= ?1 and e.date <= ?2 ORDER BY e.date ASC")
    public List<TimeTable> getByDate(Date date1, Date date2);

    @Query("SELECT e FROM TimeTable e WHERE e.event = ?1")
    public TimeTable getByName(String name);
    
}
