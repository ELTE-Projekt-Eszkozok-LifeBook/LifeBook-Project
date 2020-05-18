
package hu.elte.LifeBookProject.testingEntities;


import hu.elte.LifeBookProject.controllers.TimeTableController;
import hu.elte.LifeBookProject.entities.TimeTable;
import hu.elte.LifeBookProject.repositories.TimeTableRepository;
import java.sql.Date;
import java.sql.Time;
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
public class TimeTableEntityTest {
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private TimeTableRepository timeTableRepository;
    private TimeTableController timeTableController;

    @Test
    void injectedComponentsAreNotNull(){
      assertThat(dataSource).isNotNull();
      assertThat(jdbcTemplate).isNotNull();
      assertThat(entityManager).isNotNull();
      assertThat(timeTableRepository).isNotNull();
    }
    
    @Test
    void foundAllEventsInTimeTableRepository(){
        Iterable<TimeTable> events = timeTableRepository.findAll();
        assertThat(events).isNotNull();
        assertThat(events).isNotEmpty();
        assertThat(events).hasSize(5);
    }
    
    @Test
    void foundEventsByGivenDateIntervalInTimeTableRepository(){
        Iterable<TimeTable> events = timeTableRepository.getByDate(Date.valueOf("2020-03-01"), Date.valueOf("2020-06-31"));
        assertThat(events).isNotNull();
        assertThat(events).isNotEmpty();
        assertThat(events).hasSize(3);
        
        events = timeTableRepository.getByDate(Date.valueOf("2019-09-01"), Date.valueOf("2019-12-31"));
        assertThat(events).isNotNull();
        assertThat(events).isEmpty();
    }
    
     @Test
    void whenSaved_foundByNameInTimeTableRepository(){
        TimeTable event = timeTableRepository.save(new TimeTable("Cinema with family", "OCCASIONAL", Date.valueOf("2020-05-13"), Time.valueOf("20:00:00"), "The Avatar 2"));
        assertThat(timeTableRepository.getByName("Cinema with family")).isNotNull();
        Iterable<TimeTable> events = timeTableRepository.findAll();
        assertThat(events).isNotNull();
        assertThat(events).isNotEmpty();
        assertThat(events).hasSize(6);
    }
    
     @Test
    void whenDeleted_notFoundByNameInTimeTableRepository(){
        timeTableRepository.delete(timeTableRepository.getByName("Birthday Party"));
        assertThat(timeTableRepository.getByName("Birthday Party")).isNull();
        Iterable<TimeTable> events = timeTableRepository.findAll();
        assertThat(events).isNotNull();
        assertThat(events).hasSize(4);
    }
    
    @Test
    void whenUpdated_changesOccuredAlsoInTimeTableRepository(){
        TimeTable event = timeTableRepository.findById(timeTableRepository.getByName("MI exam").getId()).get();
        TimeTable newEvent = new TimeTable("MI exam", "OCCASIONAL", Date.valueOf("2020-06-10"), Time.valueOf("11:30:00"), "I dont want it :(((");
        event.setEvent(newEvent.getEvent());
        event.setFrequency(newEvent.getFrequency());
        event.setDate(newEvent.getDate());
        event.setNote(newEvent.getNote());
        assertThat(timeTableRepository.save(event)).isNotNull();
        assertThat(timeTableRepository.findAll()).isNotEmpty();
        assertThat(timeTableRepository.findAll()).hasSize(5);
        
        event = timeTableRepository.findById(timeTableRepository.getByName("MI exam").getId()).get();
        assertThat(event).isNotNull();
        assertThat(event.getEvent()).isEqualTo(newEvent.getEvent());
        assertThat(event.getFrequency()).isEqualTo(newEvent.getFrequency());
        assertThat(event.getDate()).isEqualTo(newEvent.getDate());
        assertThat(event.getNote()).isEqualTo(newEvent.getNote());
    }
}
