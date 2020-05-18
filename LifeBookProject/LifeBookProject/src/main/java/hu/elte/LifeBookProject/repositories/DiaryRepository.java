package hu.elte.LifeBookProject.repositories;

import hu.elte.LifeBookProject.entities.Diary;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends CrudRepository<Diary, Long> {
    
    @Query("SELECT p FROM Diary p ORDER BY p.date DESC")
    List<Diary> findAllEntries();
    
    @Query("SELECT p FROM Diary p WHERE p.date = ?1")
    List<Diary> getPostsByDate(Date dates);
    
    @Query("SELECT p FROM Diary p WHERE p.currentMood = ?1 ORDER BY p.date DESC")
    List<Diary> getPostsByMood(String mood);
}
