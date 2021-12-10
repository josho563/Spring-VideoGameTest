package com.bae.videogame;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import com.bae.videogame.domain.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

//boots the entire context (all beans)  - Random port to avoid collisions (two apps on the same port)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up MockMVC object
@Sql(scripts = { "classpath:videoGame-schema.sql", //set-up new table
		"classpath:videoGame-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD) // test setup for SQL
public class VideoGameControllerIntergrationTest {

	@Autowired // pulls MockMVC object from the context
	private MockMvc mvc; // class that peforms the request and captures response (like postman app)
	@Autowired
	private ObjectMapper mapper; // converts java and writes to JSON

	@Test
	void testCreate() throws Exception {
		Person testPerson = new Person("Gordon Freeman", "Half Life", "Crowbar", 2); // test making a new Person
		String testPersonAsJSON = this.mapper.writeValueAsString(testPerson);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_PROBLEM_JSON).content(testPersonAsJSON); //(settingcontent type to JSON)
		
		Person testCreatedPerson = new Person(2, "Gordon Freeman", "Half Life", "Crowbar", 2);
		String testCreatedPersonAsJSON = this.mapper.writeValueAsString(testCreatedPerson);
		ResultMatcher checkStatus = status().isCreated(); // checking if 201
		ResultMatcher checkBody = content().json(testCreatedPersonAsJSON); // checks its JSON format

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody); // sends the request - checks the status -
																			// checks the body
	}
	@Test
	void getAllTest() throws Exception {
		List<Person> testPerson = List.of(new Person(1, "Gordon Freeman", "Half Life", "Crowbar", 2));
		String json = this.mapper.writeValueAsString(testPerson);
		
		RequestBuilder req = get("/getAll");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	@Test
	void testReadId() throws Exception {
		Person testPerson = new Person(1, "Gordon Freeman", "Half Life", "Crowbar", 2);
		String json = this.mapper.writeValueAsString(testPerson);
		
		RequestBuilder req = get("/get/1");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testUpdate() throws Exception {
		Person testPerson = new Person(1, "Mario", "Mario Bros", "Plumbing Skillz", 9999);
		String json = this.mapper.writeValueAsString(testPerson);
		
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_PROBLEM_JSON).content(json);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(json);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDelete() throws Exception {
				
		RequestBuilder req = delete("/remove/1").contentType(MediaType.APPLICATION_PROBLEM_JSON);
		ResultMatcher checkStatus = status().isNoContent();
				
		this.mvc.perform(req).andExpect(checkStatus);
	}

}
