
package hu.elte.LifeBookProject.testingEntities;

import hu.elte.LifeBookProject.controllers.FinancialStatsController;
import hu.elte.LifeBookProject.controllers.SportActivityController;
import hu.elte.LifeBookProject.entities.FinancialStats;
import hu.elte.LifeBookProject.repositories.FinancialStatsRepository;
import hu.elte.LifeBookProject.repositories.SportActivityRepository;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FinancialStatsEntityTest {
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private FinancialStatsRepository financialStatsRepo;
    private FinancialStatsController financialStatsCont;

    @Test
    void injectedComponentsAreNotNull(){
      assertThat(dataSource).isNotNull();
      assertThat(jdbcTemplate).isNotNull();
      assertThat(entityManager).isNotNull();
      assertThat(financialStatsRepo).isNotNull();
    }
    
    @Test
    void foundAllFinancialStatsInFinancialStatsRepository(){
        Iterable<FinancialStats> stats = financialStatsRepo.findAll();
        assertThat(stats).isNotNull();
        assertThat(stats).isNotEmpty();
        assertThat(stats).hasSize(3);
    }
    
     @Test
    void foundFinancialStatsByGivenCategoryInFinancialStatsRepository(){
        Iterable<FinancialStats> stats = financialStatsRepo.getByCategory("INCOME");
        assertThat(stats).isNotNull();
        assertThat(stats).isNotEmpty();
        assertThat(stats).hasSize(2);
        
        stats = financialStatsRepo.getByCategory("COST");
        assertThat(stats).isNotNull();
        assertThat(stats).isNotEmpty();
        assertThat(stats).hasSize(1);
        
        stats = financialStatsRepo.getByCategory("SOMETHING");
        assertThat(stats).isNotNull();
        assertThat(stats).isEmpty();
    }
    
     @Test
    void foundFinancialStatsByGivenDateInFinancialStatsRepository(){
        Iterable<FinancialStats> stats = financialStatsRepo.getByDate(Date.valueOf("2020-04-11"));
        assertThat(stats).isNotNull();
        assertThat(stats).isNotEmpty();
        assertThat(stats).hasSize(2);
        
        stats = financialStatsRepo.getByDate(Date.valueOf("2020-04-10"));
        assertThat(stats).isNotNull();
        assertThat(stats).isNotEmpty();
        assertThat(stats).hasSize(1);
        
        stats = financialStatsRepo.getByCategory("2020-02-01");
        assertThat(stats).isNotNull();
        assertThat(stats).isEmpty();
    }
    
     @Test
    void foundFinancialStatsByGivenMonthInFinancialStatsRepository(){
        Iterable<FinancialStats> stats = financialStatsRepo.getByMonth(4);
        assertThat(stats).isNotNull();
        assertThat(stats).isNotEmpty();
        assertThat(stats).hasSize(3);
        
        stats = financialStatsRepo.getByMonth(1);
        assertThat(stats).isNotNull();
        assertThat(stats).isEmpty();
    }
    
     @Test
    void whenSaved_foundByIdInFinancialStatsRepository(){
        FinancialStats stat = financialStatsRepo.save(new FinancialStats("COST", Date.valueOf("2020-04-08"), 13000, "Buying new puzzle for mom because of her nameday :D"));
        assertThat(financialStatsRepo.findById(stat.getId())).isNotNull();
        Iterable<FinancialStats> stats = financialStatsRepo.findAll();
        assertThat(stats).isNotNull();
        assertThat(stats).isNotEmpty();
        assertThat(stats).hasSize(4);
    }
    
     @Test
    void whenDeleted_notFoundByIdInFinancialStatsRepository(){
        financialStatsRepo.delete(financialStatsRepo.findById(3L).get());
        assertThat(financialStatsRepo.findById(3L)).isEmpty();
        Iterable<FinancialStats> stats = financialStatsRepo.findAll();
        assertThat(stats).isNotNull();
        assertThat(stats).hasSize(2);
    }
    
     @Test
    void whenUpdated_changesOccuredAlsoInFinancialStatsRepository(){
        FinancialStats stat = financialStatsRepo.findById(3L).get();
        FinancialStats newStat = new FinancialStats("INCOME", Date.valueOf("2020-04-10"), 23560, "Scholarship from ELTE");
        stat.setDate(newStat.getDate());
        stat.setCategory(newStat.getCategory());
        stat.setAmount(newStat.getAmount());
        stat.setDescription(newStat.getDescription());
        assertThat(financialStatsRepo.save(stat)).isNotNull();
        assertThat(financialStatsRepo.findAll()).hasSize(3);
        
        stat = financialStatsRepo.findById(3L).get();
        assertThat(stat.getDate()).isEqualTo(newStat.getDate());
        assertThat(stat.getCategory()).isEqualTo(newStat.getCategory());
        assertThat(stat.getAmount()).isEqualTo(newStat.getAmount());
        assertThat(stat.getDescription()).isEqualTo(newStat.getDescription());
    }
}
