package hu.elte.LifeBookProject.controllers;
        
import hu.elte.LifeBookProject.controllers.EatingHabitController;
import hu.elte.LifeBookProject.repositories.EatingHabitRepository;
import hu.elte.LifeBookProject.entities.EatingHabit;

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
public class EatingHabitControllerTest {
    
    private MockMvc mockMvc;
    
    @Mock
    private EatingHabitRepository eatingHabitRepository;

    @InjectMocks
    private EatingHabitController eatingHabitController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(eatingHabitController)
                .build();
    }
    
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/eatinghabits"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetByType() throws Exception {
        mockMvc.perform(get("/eatinghabits/type/fruit"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testByIsFood() throws Exception {
        mockMvc.perform(get("/eatinghabits/food"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/eatinghabits/drink"))
                .andExpect(status().isOk());
    }
        
    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/eatinghabits/Orange"))
                .andExpect(status().isOk());
    }
    
}
