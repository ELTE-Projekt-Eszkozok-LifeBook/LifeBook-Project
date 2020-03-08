package hu.elte.LifeBookProject.repositories;

import hu.elte.LifeBookProject.entities.Diary;
import org.springframework.data.repository.CrudRepository;


public interface DiaryRepository extends CrudRepository<Diary, Long> {
    
}