package com.edonoxako.mathtest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by edono on 03.06.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MathtestApplication.class})
@WebAppConfiguration
public class MathtestIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .build();
    }

    @Test
    public void testLoadSingleExercise() throws Exception {
        checkGettighExercise(0, "dummy-1", "(a - b) * (a + b)");
        checkGettighExercise(1, "dummy-2", "a^2 + 2*a*b + b^2");
    }

    private void checkGettighExercise(int exerciseId, String definition, String expression) throws Exception {
        mockMvc.perform(get("/api/exercise/load/" + exerciseId)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status()
                        .isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.definition")
                        .value(definition))
                .andExpect(jsonPath("$.expression")
                        .value(expression));
    }
}
