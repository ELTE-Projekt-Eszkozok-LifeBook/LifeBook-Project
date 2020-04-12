package hu.elte.LifeBookProject.testingEntities;

import hu.elte.LifeBookProject.controllers.TodoController;
import hu.elte.LifeBookProject.entities.Todo;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import hu.elte.LifeBookProject.repositories.TodoRepository;
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
public class TodoEntityTest {


    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private TodoRepository todoRepository;
    private TodoController todoController;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(todoRepository).isNotNull();
    }

    @Test
    void foundAllTodosInTodoRepository(){
        Iterable<Todo> todos = todoRepository.findAll();
        assertThat(todos).isNotNull();
        assertThat(todos).isNotEmpty();
        assertThat(todos).hasSize(3);
    }
    
    @Test
    void foundTodosByGivenCategoryInTodoRepository(){
        Iterable<Todo> todos = todoRepository.getTodosInSequence("HOUSEHOLD");
        assertThat(todos).isNotNull();
        assertThat(todos).isNotEmpty();
        assertThat(todos).hasSize(1);
        
        todos = todoRepository.getTodosInSequence("SHOPPING");
        assertThat(todos).isNotNull();
        assertThat(todos).isNotEmpty();
        assertThat(todos).hasSize(1);
        
        todos = todoRepository.getTodosInSequence("UNIVERSITY");
        assertThat(todos).isNotNull();
        assertThat(todos).isNotEmpty();
        assertThat(todos).hasSize(1);
        
        todos = todoRepository.getTodosInSequence("PERSONAL");
        assertThat(todos).isNotNull();
        assertThat(todos).isEmpty();
    }
    
    @Test
    void checkingIsCorrectOfAGivenTodoSearchByItsIdInTodoRepository(){
        boolean checking = todoRepository.getTodoChecked(1L);
        assertEquals(true, checking);
        
        checking = todoRepository.getTodoChecked(2L);
        assertEquals(false, checking);
        
        checking = todoRepository.getTodoChecked(3L);
        assertEquals(false, checking);
    }
    
    @Test
    void importanceIsCorrectOfAGivenTodoSearchByItsIdInTodoRepository(){
        boolean importance = todoRepository.getTodoImportant(1L);
        assertEquals(true, importance);
        
        importance = todoRepository.getTodoChecked(2L);
        assertEquals(false, importance);
        
        importance = todoRepository.getTodoChecked(3L);
        assertEquals(false, importance);
    }

    @Test
    void whenSaved_foundByIdInSportActivityRepository(){
        Todo todo = todoRepository.save(new Todo("Buying a new shoulder bag for school", false, false, "SHOPPING"));
        assertThat(todoRepository.findById(todo.getId())).isNotNull();
        Iterable<Todo> todos = todoRepository.findAll();
        assertThat(todos).isNotNull();
        assertThat(todos).isNotEmpty();
        assertThat(todos).hasSize(4);
    }
    
    @Test
    void whenDeleted_notFoundByIdInSportActivityRepository(){
        todoRepository.delete(todoRepository.findById(2L).get());
        assertThat(todoRepository.findById(2L)).isEmpty();
        Iterable<Todo> todos = todoRepository.findAll();
        assertThat(todos).isNotNull();
        assertThat(todos).hasSize(2);
    }
    
    @Test
    void whenUpdated_changesOccuredAlsoInFinancialStatsRepository(){
        Todo todo = todoRepository.findById(2L).get();
        Todo newTodo = new Todo("Give in the next homework of Computer Graphics! - Finished", true, true, "UNIVERSITY");
        todo.setTodoText(newTodo.getTodoText());
        todo.setChecked(newTodo.isChecked());
        todo.setImportant(newTodo.isImportant());
        todo.setCategory(newTodo.getCategory());
        assertThat(todoRepository.save(todo)).isNotNull();
        assertThat(todoRepository.findAll()).isNotEmpty();
        assertThat(todoRepository.findAll()).hasSize(3); 
        
        todo = todoRepository.findById(2L).get();
        assertThat(todo).isNotNull();
        assertThat(todo.getTodoText()).isEqualTo(newTodo.getTodoText());
        assertThat(todo.isChecked()).isEqualTo(newTodo.isChecked());
        assertThat(todo.isImportant()).isEqualTo(newTodo.isImportant());
        assertThat(todo.getCategory()).isEqualTo(newTodo.getCategory());
    }
    
    /*@Test
    //@Sql("./createTodo.sql")
    void whenInitializedByDbUnit_thenGetAll() {
      todoController = new TodoController();
      ResponseEntity<Iterable<Todo>> todo = todoController.getAll();
      assertThat(todo).isNotNull();
      assertEquals(todo.getStatusCode(), HttpStatus.OK);

      Iterable<Todo> entries = todoRepository.findAll();
      assertThat(entries).hasSize(5);
    }

    @Test
    //@Sql("./createTodo.sql")
    void whenInitializedByDbUnit_thenGetInSequence() {
        todoController = new TodoController();
        ResponseEntity<Iterable<Todo>> todo_household = todoController.getInSequence("HOUSEHOLD");
        assertThat(todo_household).isNotNull();
        assertEquals(todo_household.getStatusCode(), HttpStatus.OK);
        //meg kell nézni h tényleg hoosehold-e mind, és sorba van e

        System.out.println(todo_household.getBody());

        ResponseEntity<Iterable<Todo>> todo_someday = todoController.getInSequence("SOMEDAY_STUFF");
        assertThat(todo_someday).isNull();
    }

    @Test
    //@Sql("./createTodo.sql")
    void whenInitializedByDbUnit_thenGetChecked() {
        todoController = new TodoController();
        ResponseEntity<Iterable<Todo>> todo_checked = todoController.getChecked(4l);
        assertThat(todo_checked).isNotNull();
        assertEquals(todo_checked.getStatusCode(), HttpStatus.OK);
        //meg kell nézni h tényleg true-e

        ResponseEntity<Iterable<Todo>> todo_notChecked = todoController.getChecked(3l);
        assertThat(todo_notChecked).isNotNull();
        assertEquals(todo_notChecked.getStatusCode(), HttpStatus.OK);
        //meg kell nézni h tényleg false-e
    }

    @Test
    //@Sql("./createTodo.sql")
    void whenInitializedByDbUnit_thenGetImportant() {
        todoController = new TodoController();
        ResponseEntity<Iterable<Todo>> todo_important = todoController.getImportant(2l);
        assertThat(todo_important).isNotNull();
        assertEquals(todo_important.getStatusCode(), HttpStatus.OK);
        //meg kell nézni h tényleg true-e

        ResponseEntity<Iterable<Todo>> todo_notImportant = todoController.getImportant(4l);
        assertThat(todo_notImportant).isNotNull();
        assertEquals(todo_notImportant.getStatusCode(), HttpStatus.OK);
        //meg kell nézni h tényleg false-e
    }


    @Test
    //@Sql("./createTodo.sql")
    void whenInitializedByDbUnit_thenDelete() {
        todoController = new TodoController();
        Long id = 2l;
        ResponseEntity<Todo> todo = todoController.delete(id);
        assertThat(todo).isNotNull();
        assertEquals(todo.getStatusCode(), HttpStatus.OK);

        Todo todo_deleted = todoRepository.findById(id).get();
        assertThat(todo_deleted).isNull();

    }

    @Test
    //@Sql("./createTodo.sql")
    void whenInitializedByDbUnit_thenPost() {
        todoController = new TodoController();
        Todo todo = new Todo();
        todo.setId(100l);
        todo.setChecked(false);
        todo.setImportant(true);
        todo.setTodoText("új task");


        ResponseEntity<Todo> todo_posted = todoController.post(todo);
        assertThat(todo_posted).isNotNull();
        assertEquals(todo_posted.getStatusCode(), HttpStatus.OK);

        Todo todo_get = todoRepository.findById(todo.getId()).get();
        assertThat(todo_get).isNotNull();
    }*/
}
