package hu.elte.LifeBookProject.controllers;

import hu.elte.LifeBookProject.repositories.TodoRepository;
import hu.elte.LifeBookProject.entities.Todo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@CrossOrigin
@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Todo>> getAll() {
        return new ResponseEntity(todoRepository.findAll(), HttpStatus.OK);
    }

    //get Todos In Sequence
    @GetMapping("/category/{category}")
    public ResponseEntity<Iterable<Todo>> getInSequence(@PathVariable String category) {
        List<Todo> entries = todoRepository.getTodosInSequence(category);
        return new ResponseEntity(entries, HttpStatus.OK);
    }

    //todo:
    @GetMapping("/checked/{id}")
    public ResponseEntity<Iterable<Todo>> getChecked(@PathVariable Long id) {
        boolean checked = todoRepository.getTodoChecked(id);
        return new ResponseEntity(checked, HttpStatus.OK);
    }

    @GetMapping("/important/{id}")
    public ResponseEntity<Iterable<Todo>> getImportant(@PathVariable Long id) {
        boolean important = todoRepository.getTodoImportant(id);
        return new ResponseEntity(important, HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Todo> delete(@PathVariable Long id) {
        Optional<Todo> entry = todoRepository.findById(id);
        if (!entry.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        todoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    

    @PostMapping("")
    public ResponseEntity<Todo> post(@RequestBody Todo entry) {
        return ResponseEntity.ok(todoRepository.save(entry));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo newTodo) {
        Todo todo = todoRepository.findById(id).get();
        if (todo == null) {
            return ResponseEntity.notFound().build();
        }
        todo.setTodoText(newTodo.getTodoText());
        todo.setChecked(newTodo.isChecked());
        todo.setImportant(newTodo.isImportant());
        todo.setCategory(newTodo.getCategory());
        return ResponseEntity.ok(todoRepository.save(todo));
    }
}
