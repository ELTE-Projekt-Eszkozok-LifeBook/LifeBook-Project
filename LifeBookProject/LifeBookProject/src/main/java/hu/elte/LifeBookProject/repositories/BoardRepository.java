package hu.elte.LifeBookProject.repositories;

import hu.elte.LifeBookProject.entities.Board;
import org.springframework.data.repository.CrudRepository;


public interface BoardRepository extends CrudRepository<Board, Long> {
    
}
