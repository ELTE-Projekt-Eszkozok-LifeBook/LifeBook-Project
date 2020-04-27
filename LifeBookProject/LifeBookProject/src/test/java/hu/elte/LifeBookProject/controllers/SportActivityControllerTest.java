package hu.elte.LifeBookProject.controllers;
        
import hu.elte.LifeBookProject.controllers.SportActivityController;
import hu.elte.LifeBookProject.repositories.SportActivityRepository;
import hu.elte.LifeBookProject.entities.SportActivity;

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
public class SportActivityControllerTest {
    
    private MockMvc mockMvc;
    
    @Mock
    private SportActivityRepository sportActivityRepository;

    @InjectMocks
    private SportActivityController sportActivityController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(sportActivityController)
                .build();
    }
    
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/sportactivity"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetByName() throws Exception {
        mockMvc.perform(get("/sportactivity/name/running"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetByRegularity() throws Exception {
        mockMvc.perform(get("/sportactivity/regularity/WEEKLY"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetByOfficiality() throws Exception {
        mockMvc.perform(get("/sportactivity/true"))
                .andExpect(status().isOk());
    }
    
    
    @Test
    public void testDetele() throws Exception {
        mockMvc.perform(delete("/sportactivity/delete/basketball"))
                .andExpect(status().isOk());
    }
    
}
