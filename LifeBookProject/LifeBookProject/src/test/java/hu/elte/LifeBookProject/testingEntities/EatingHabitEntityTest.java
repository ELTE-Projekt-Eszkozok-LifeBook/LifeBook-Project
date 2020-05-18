
package hu.elte.LifeBookProject.testingEntities;

import hu.elte.LifeBookProject.controllers.EatingHabitController;
import hu.elte.LifeBookProject.entities.EatingHabit;
import hu.elte.LifeBookProject.repositories.EatingHabitRepository;
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
public class EatingHabitEntityTest {
    
     
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private EatingHabitRepository eatingHabitRepo;
    private EatingHabitController eatingHabitCont;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(eatingHabitRepo).isNotNull();
    }
    
    @Test
    void foundAllEatingsInEatingHabitRepository(){
        Iterable<EatingHabit> eatings = eatingHabitRepo.findAll();
        int numberOfEatings = 7;
        assertThat(eatings).hasSize(numberOfEatings);
    }
    
    @Test
    void foundAllEatingsByGivenTypeInEatingHabitRepository(){
        Iterable<EatingHabit> eatings = eatingHabitRepo.getByType("fruit");
        assertThat(eatings).isNotNull();
        assertThat(eatings).isNotEmpty();
        assertThat(eatings).hasSize(2);
        
        eatings = eatingHabitRepo.getByType("salad");
        assertThat(eatings).isNotNull();
        assertThat(eatings).isNotEmpty();
        assertThat(eatings).hasSize(1);
        
        eatings = eatingHabitRepo.getByType("soup");
        assertThat(eatings).isNotNull();
        assertThat(eatings).isEmpty();
    }
    
    @Test
    void foundAllEatingsWhichAreFoodOrNotInEatingHabitRepository(){
        Iterable<EatingHabit> eatings = eatingHabitRepo.getByIsFood(true);
        assertThat(eatings).isNotNull();
        assertThat(eatings).isNotEmpty();
        assertThat(eatings).hasSize(4);
        assertThat(eatings).doesNotContainAnyElementsOf(eatingHabitRepo.getByIsFood(false));
        
        eatings = eatingHabitRepo.getByIsFood(false);
        assertThat(eatings).isNotNull();
        assertThat(eatings).isNotEmpty();
        assertThat(eatings).hasSize(3);
        assertThat(eatings).doesNotContainAnyElementsOf(eatingHabitRepo.getByIsFood(true));
    }
     
    @Test
    void whenSaved_foundByNameInEatingHabitRepository(){
        EatingHabit eating = eatingHabitRepo.save(new EatingHabit("Gulas soup", "soup", true, "YEARLY", "1 plate"));
        assertThat(eatingHabitRepo.getByName(eating.getName())).isNotNull();
        Iterable<EatingHabit> eatings = eatingHabitRepo.findAll();
        assertThat(eatings).isNotNull();
        assertThat(eatings).isNotEmpty();
        assertThat(eatings).hasSize(8);
    }
    
    @Test
    void whenDeleted_notFoundByNameInEatingHabitRepository(){
        eatingHabitRepo.delete(eatingHabitRepo.getByName("Water"));
        assertThat(eatingHabitRepo.getByName("Water")).isNull();
        Iterable<EatingHabit> eatings = eatingHabitRepo.findAll();
        assertThat(eatings).isNotNull();
        assertThat(eatings).hasSize(6);
    }
    
    @Test
    void whenUpdated_changesOccuredAlsoInEatingHabitRepository(){
        EatingHabit eating = eatingHabitRepo.findById(eatingHabitRepo.getByName("Water").getId()).get();
        EatingHabit newEating = new EatingHabit("Water", "cold drink", false, "DAILY", "2 liter");
        eating = eatingHabitRepo.getByName("Water");
        eating.setName(newEating.getName());
        eating.setType(newEating.getType());
        eating.setFood(newEating.isFood());
        eating.setFrequency(newEating.getFrequency());
        eating.setPortion(newEating.getPortion());
        
        assertThat(eatingHabitRepo.save(eating)).isNotNull();
        assertThat(eatingHabitRepo.findAll()).hasSize(7);
        assertThat(eatingHabitRepo.findAll()).isNotEmpty();
        
        eating = eatingHabitRepo.findById(eatingHabitRepo.getByName(newEating.getName()).getId()).get();
        assertThat(eating).isNotNull();
        assertThat(eating.getType()).isEqualTo(newEating.getType());
        assertThat(eating.isFood()).isEqualTo(newEating.isFood());
        assertThat(eating.getFrequency()).isEqualTo(newEating.getFrequency());
        assertThat(eating.getPortion()).isEqualTo(newEating.getPortion());
    }
}
