package hu.elte.LifeBookProject.repositories;

import hu.elte.LifeBookProject.entities.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    //Getters
    @Query("SELECT p FROM Todo p WHERE p.category = ?1 ORDER BY p.checked DESC")
    List<Todo> getTodosInSequence(String category);

    @Query("SELECT p.checked FROM Todo p WHERE p.id = ?1")
    boolean getTodoChecked(Long id);

    @Query("SELECT p.important FROM Todo p WHERE p.id = ?1")
    boolean getTodoImportant(Long id);


    //Delete
    @Query("DELETE FROM Todo WHERE id = ?1")
    boolean deleteTodo(Long id);


    //Update
    @Query("UPDATE Todo SET checked = ?2 WHERE id = ?1")
    void updateTodoChecked(Long id, boolean checked);

    @Query("UPDATE Todo SET important = ?2 WHERE id = ?1")
    void updateTodoImportant(Long id, boolean important);


    //Insert
    /*@Query("INSERT INTO Todo (todoText, checked, important, category) VALUES (?1, ?2, ?3, ?4)")
    void insertTodo(String todoText, boolean checked, boolean importnat, String category);*/
}
