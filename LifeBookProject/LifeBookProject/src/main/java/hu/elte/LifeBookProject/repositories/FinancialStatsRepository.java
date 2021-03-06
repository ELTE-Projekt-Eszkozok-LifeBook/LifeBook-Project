
package hu.elte.LifeBookProject.repositories;

import hu.elte.LifeBookProject.entities.FinancialStats;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialStatsRepository extends CrudRepository<FinancialStats, Long> {

    @Query("SELECT f FROM FinancialStats f WHERE f.category = ?1 ORDER BY f.date DESC")
    List<FinancialStats> getByCategory(String category);

    @Query("SELECT f FROM FinancialStats f WHERE f.date = ?1")
    List<FinancialStats> getByDate(Date date);

    @Query("SELECT f FROM FinancialStats f WHERE MONTH(f.date) = ?1 ORDER BY f.date DESC")
    List<FinancialStats> getByMonth(Integer month);
    
    @Query("SELECT SUM(f.amount) FROM FinancialStats f WHERE f.category <> 'INCOME' ")
    Integer getCosts();
 
    @Query("SELECT SUM(f.amount) FROM FinancialStats f WHERE f.category = ?1 ")
    Integer getCostsByCategory(String category);
    
}
