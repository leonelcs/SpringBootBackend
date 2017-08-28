package com.leonel.app.integrationTests;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.Assert.assertThat;

import com.leonel.app.AppApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,AppApplication.class})
@ActiveProfiles("test")
public class AppTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @Before
    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void saveCustomerInTheDatabase() throws Exception {
        int rowNumber = countRowsInTable("customer");

        mockMvc.perform(post("/customers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"pepe\",\"email\" : \"pepe@gmail.com\"}"))
                .andExpect(status().is2xxSuccessful());

        assertThat(countRowsInTable("customer"), is(rowNumber + 1));
    }


}
