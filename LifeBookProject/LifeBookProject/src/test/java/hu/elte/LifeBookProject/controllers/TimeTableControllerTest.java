package hu.elte.LifeBookProject.controllers;
        
import hu.elte.LifeBookProject.controllers.TimeTableController;
import hu.elte.LifeBookProject.repositories.TimeTableRepository;
import hu.elte.LifeBookProject.entities.TimeTable;

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
public class TimeTableControllerTest {
    
    private MockMvc mockMvc;
    
    @Mock
    private TimeTableRepository timeTableRepository;

    @InjectMocks
    private TimeTableController timeTableController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(timeTableController)
                .build();
    }
    
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/timetable"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetByDate() throws Exception {
        mockMvc.perform(get("/timetable/date/2020-01-20/2020-04-20"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testDetele() throws Exception {
        mockMvc.perform(delete("/timetable/delete/MI exam"))
                .andExpect(status().isOk());
    }
    
}
