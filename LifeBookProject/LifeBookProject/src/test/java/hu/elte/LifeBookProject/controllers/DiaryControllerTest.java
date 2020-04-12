package hu.elte.LifeBookProject.controllers;
        
import hu.elte.LifeBookProject.controllers.DiaryController;
import hu.elte.LifeBookProject.repositories.DiaryRepository;
import hu.elte.LifeBookProject.entities.Diary;

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
public class DiaryControllerTest {
    
    private MockMvc mockMvc;
    
    @Mock
    private DiaryRepository diaryRepository;

    @InjectMocks
    private DiaryController diaryController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(diaryController)
                .build();
    }
    
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/diary"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetByDate() throws Exception {
        mockMvc.perform(get("/diary/date/2020-04-10"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetByMood() throws Exception {
        mockMvc.perform(get("/diary/mood/HAPPY"))
                .andExpect(status().isOk());
    }
    
    
    @Test
    public void testDetele() throws Exception {
        mockMvc.perform(delete("/diary/delete/1"))
                .andExpect(status().isOk());
    }
    
}