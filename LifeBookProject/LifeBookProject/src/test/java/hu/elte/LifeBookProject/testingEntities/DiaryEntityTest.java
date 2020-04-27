
package hu.elte.LifeBookProject.testingEntities;

import hu.elte.LifeBookProject.controllers.DiaryController;
import hu.elte.LifeBookProject.repositories.DiaryRepository;
import hu.elte.LifeBookProject.entities.Diary;
import hu.elte.LifeBookProject.enums.Mood;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DiaryEntityTest {
    
    
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private DiaryRepository diaryRepository;
    private DiaryController diaryController;

    @Test
    void injectedComponentsAreNotNull(){
      assertThat(dataSource).isNotNull();
      assertThat(jdbcTemplate).isNotNull();
      assertThat(entityManager).isNotNull();
      assertThat(diaryRepository).isNotNull();
    }


    /*@Test
    void whenInitializedByDbUnit_thenGetAll() {
      ResponseEntity<Iterable<Diary>> diary = diaryController.getAll();
      assertThat(diary).isNotNull();
      assertEquals(diary.getStatusCode(), HttpStatus.OK);
      //assertEquals(diary..size(),2);
    }*/

    @Test
    void foundAllEntriesInDiaryRepository(){
        Iterable<Diary> entries = diaryRepository.findAll();
        int numberOfEntries = 5;
        assertThat(entries).hasSize(numberOfEntries);
    }

    @Test
    void foundAllEntriesByGivenDateInDiaryRepository(){
        Iterable<Diary> entries = diaryRepository.getPostsByDate(Date.valueOf("2020-04-10"));
        assertThat(entries).isNotNull();
        assertThat(entries).hasSize(2);
        
        entries = diaryRepository.getPostsByDate(Date.valueOf("2020-01-20"));
        assertThat(entries).isNotNull();
        assertThat(entries).hasSize(0);
    }
    
    @Test
    void foundAllEntriesByGivenMoodInDiaryRepository(){
        String mood = "HAPPY";
        Iterable<Diary> entries = diaryRepository.getPostsByMood(mood);
        assertThat(entries).isNotNull();
        assertThat(entries).hasSize(1);
        //assertThat(mood).isIn(Mood.values());
        
        mood = "SAD";
        entries = diaryRepository.getPostsByMood(mood);
        assertThat(entries).isNotNull();
        assertThat(entries).hasSize(1);
        
        mood = "DEPRESSED";
        entries = diaryRepository.getPostsByMood(mood);
        assertThat(entries).isNotNull();
        assertThat(entries).hasSize(1);
        
        mood = "OK";
        entries = diaryRepository.getPostsByMood(mood);
        assertThat(entries).isNotNull();
        assertThat(entries).hasSize(2);
    }
    
    @Test
    void whenSaved_foundByIdInDiaryRepository(){
        Diary entry = diaryRepository.save(new Diary(
                "something",
                null,
                null,
                "OVERJOYED",
                Date.valueOf("2020-05-01")));
        assertThat(diaryRepository.findById(entry.getId())).isNotNull();
        Iterable<Diary> entries = diaryRepository.findAll();
        assertThat(entries).hasSize(6);
    }
    
    @Test
    void whenDeleted_notFoundByIdInDiaryRepository(){
        diaryRepository.delete(diaryRepository.findById(1L).get());
        assertThat(diaryRepository.findById(1L)).isEmpty();
        Iterable<Diary> entries = diaryRepository.findAll();
        assertThat(entries).hasSize(4);
    }
    
    @Test
    void whenUpdated_changesOccuredAlsoInDiaryRepository(){
        Diary entry = diaryRepository.findById(1L).get();
        Diary newEntry = new Diary("halika", null, null, "happy",Date.valueOf("2020-05-12"));
        entry.setText(newEntry.getText());
        entry.setCurrentMood(newEntry.getCurrentMood());
        assertThat(diaryRepository.save(entry)).isNotNull();
        assertThat(diaryRepository.findAll()).hasSize(5);
        
        entry = diaryRepository.findById(1L).get();
        assertThat(entry.getText()).isEqualTo(newEntry.getText());
        assertThat(entry.getCurrentMood()).isEqualTo(newEntry.getCurrentMood());
    }
}
