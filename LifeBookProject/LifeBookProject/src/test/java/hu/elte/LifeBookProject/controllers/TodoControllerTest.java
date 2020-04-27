package hu.elte.LifeBookProject.controllers;
        
import hu.elte.LifeBookProject.controllers.TodoController;
import hu.elte.LifeBookProject.repositories.TodoRepository;
import hu.elte.LifeBookProject.entities.Todo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
/*@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TodoController.class
)
@AutoConfigureMocMvc
@TestPropertySource(location = "classpath:application-test.properties")*/
public class TodoControllerTest {
    
    private MockMvc mockMvc;
    
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoController todoController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(todoController)
                .build();
    }
    
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/todo"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetInSequence() throws Exception {
        mockMvc.perform(get("/todo/category/HOUSEHOLD"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetChecked() throws Exception {
        mockMvc.perform(get("/todo/checked/2"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetImportant() throws Exception {
        mockMvc.perform(get("/todo/important/1"))
                .andExpect(status().isOk());
    }
    
    
    @Test
    public void testDetele() throws Exception {
        mockMvc.perform(delete("/todo/delete/3"))
                .andExpect(status().isOk());
    }
    
    /*@Test
    public void testPost() throws Exception {
        Todo todo = new Todo();
        todo.setId(100l);
        todo.setChecked(false);
        todo.setImportant(true);
        todo.setTodoText("új task");
        
        mockMvc.perform(post("/todo")
                .contentType(Todo.class)
                .content(todo))
                .andExpect(status().isOk());
    }*/
}
