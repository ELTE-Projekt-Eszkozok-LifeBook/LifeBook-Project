
package hu.elte.LifeBookProject.testingEntities;


import hu.elte.LifeBookProject.controllers.SportActivityController;
import hu.elte.LifeBookProject.entities.SportActivity;
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
public class SportActivityEntityTest {
     
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private SportActivityRepository sportActivityRepo;
    private SportActivityController sportActivityCont;

    @Test
    void injectedComponentsAreNotNull(){
      assertThat(dataSource).isNotNull();
      assertThat(jdbcTemplate).isNotNull();
      assertThat(entityManager).isNotNull();
      assertThat(sportActivityRepo).isNotNull();
    }
    
    @Test
    void foundAllSportsInSportActivityRepository(){
        Iterable<SportActivity> sports = sportActivityRepo.findAll();
        assertThat(sports).isNotNull();
        assertThat(sports).isNotEmpty();
        assertThat(sports).hasSize(4);
    }
    
    @Test
    void foundSpecificSportByNameInSportActivityRepository(){
        SportActivity sport = sportActivityRepo.getByName("basketball");
        assertThat(sport).isNotNull();
        
        sport = sportActivityRepo.getByName("running");
        assertThat(sport).isNotNull();
        
        sport = sportActivityRepo.getByName("handball");
        assertThat(sport).isNull();
    }
    
    @Test
    void foundAllSportsByGivenRegularityInSportActivityRepository(){
        Iterable<SportActivity> sports = sportActivityRepo.getByRegularity("WEEKLY");
        assertThat(sports).isNotNull();
        assertThat(sports).isNotEmpty();
        assertThat(sports).hasSize(2);
        
        sports = sportActivityRepo.getByRegularity("DAILY");
        assertThat(sports).isNotNull();
        assertThat(sports).isNotEmpty();
        assertThat(sports).hasSize(1);
        
        sports = sportActivityRepo.getByRegularity("MONTHLY");
        assertThat(sports).isNotNull();
        assertThat(sports).isEmpty();
    }
    
     @Test
    void foundAllSportsByGivenOfficialityInSportActivityRepository(){
        Iterable<SportActivity> sports = sportActivityRepo.getByOfficiality(true);
        assertThat(sports).isNotNull();
        assertThat(sports).isNotEmpty();
        assertThat(sports).hasSize(1);
        assertThat(sports).doesNotContainAnyElementsOf(sportActivityRepo.getByOfficiality(false));
        
        sports = sportActivityRepo.getByOfficiality(false);
        assertThat(sports).isNotNull();
        assertThat(sports).isNotEmpty();
        assertThat(sports).hasSize(3);
        assertThat(sports).doesNotContainAnyElementsOf(sportActivityRepo.getByOfficiality(true));
    }
    
    @Test
    void whenSaved_foundByNameInSportActivityRepository(){
        SportActivity sport = sportActivityRepo.save(new SportActivity("baseball", "MONTHLY", 1.0, Date.valueOf("2020-04-03"), true));
        assertThat(sportActivityRepo.getByName(sport.getName())).isNotNull();
        Iterable<SportActivity> sports = sportActivityRepo.findAll();
        assertThat(sports).isNotEmpty();
        assertThat(sports).hasSize(5);
    }
    
    @Test
    void whenDeleted_notFoundByNameInSportActivityRepository(){
        sportActivityRepo.delete(sportActivityRepo.getByName("basketball"));
        assertThat(sportActivityRepo.getByName("basketball")).isNull();
        Iterable<SportActivity> sports = sportActivityRepo.findAll();
        assertThat(sports).hasSize(3);
    }
    
    @Test
    void whenUpdated_changesOccuredAlsoInSportActivityRepository(){
        SportActivity sport = sportActivityRepo.findById(sportActivityRepo.getByName("running").getId()).get();
        SportActivity newActivity = new SportActivity("running", "DAILY", 1.5, Date.valueOf("2018-01-05"), false);
        sport.setRegularity(newActivity.getRegularity());
        sport.setDuration(newActivity.getDuration());
        sport.setStartTime(newActivity.getStartTime());
        sport.setOfficial(newActivity.isOfficial());
        assertThat(sportActivityRepo.save(sport)).isNotNull();
        assertThat(sportActivityRepo.findAll()).isNotEmpty();
        assertThat(sportActivityRepo.findAll()).hasSize(4);
        
        sport = sportActivityRepo.findById(sportActivityRepo.getByName(newActivity.getName()).getId()).get();
        assertThat(sport).isNotNull();
        assertThat(sport.getRegularity()).isEqualTo(newActivity.getRegularity());
        assertThat(sport.getDuration()).isEqualTo(newActivity.getDuration());
        assertThat(sport.getStartTime()).isEqualTo(newActivity.getStartTime());
        assertThat(sport.isOfficial()).isEqualTo(newActivity.isOfficial());
    }
    
}
