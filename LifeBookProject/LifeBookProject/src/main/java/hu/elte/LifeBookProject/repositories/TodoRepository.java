package hu.elte.LifeBookProject.repositories;

import hu.elte.LifeBookProject.entities.Todo;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

    @Query("SELECT p FROM Todo p WHERE p.category = ?1 ORDER BY p.checked DESC")
    List<Todo> getTodosInSequence(String category);

    @Query("SELECT p.checked FROM Todo p WHERE p.id = ?1")
    boolean getTodoChecked(Long id);

    @Query("SELECT p.important FROM Todo p WHERE p.id = ?1")
    boolean getTodoImportant(Long id);
}
