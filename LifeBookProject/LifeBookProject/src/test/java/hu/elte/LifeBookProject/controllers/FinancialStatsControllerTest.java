package hu.elte.LifeBookProject.controllers;
        
import hu.elte.LifeBookProject.controllers.FinancialStatsController;
import hu.elte.LifeBookProject.repositories.FinancialStatsRepository;
import hu.elte.LifeBookProject.entities.FinancialStats;

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
public class FinancialStatsControllerTest {
    
    private MockMvc mockMvc;
    
    @Mock
    private FinancialStatsRepository financialStatsRepository;

    @InjectMocks
    private FinancialStatsController financialStatsController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(financialStatsController)
                .build();
    }
    
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/financial"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetByCategory() throws Exception {
        mockMvc.perform(get("/financialStats/category/COST"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetByDate() throws Exception {
        mockMvc.perform(get("/financialStats/date/2020-04-10"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testGetByMonth() throws Exception {
        mockMvc.perform(get("/financialStats/month/4"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testDetele() throws Exception {
        mockMvc.perform(delete("/financialStats/delete/2"))
                .andExpect(status().isOk());
    }
    
}
