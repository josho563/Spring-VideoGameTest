package com.bae.videogame.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.videogame.Service.VideoGameService;
import com.bae.videogame.domain.Person;

@RestController // tells Spring this is a controller
				// REST COMPLIANT

public class SpringVideoGameController {

	private VideoGameService service;

	@Autowired // tells spring to fetch the service from the context
//dependacy injection	

	public SpringVideoGameController(VideoGameService service) {
		super();
		this.service = service;
	}

	@GetMapping("/helloworld") // endpoint
	public String hello() {
		return "Wake up Mr. Freeman!";
	}

	@PostMapping("/create") // 201 Created
	public ResponseEntity<Person> createpersons(@RequestBody Person person) {
		this.service.createPerson(person);
		Person created = this.service.createPerson(person);
		ResponseEntity<Person> response = new ResponseEntity<Person>(created, HttpStatus.CREATED); // Status code line
		return response;
	}

	@GetMapping("/getAll") // 200
	public ResponseEntity<List<Person>> getAllPersons() {
		return ResponseEntity.ok(this.service.getAllPerson());
	}

//	@GetMapping("/get/{id}") // 200
//	public Person getPerson(@PathVariable Integer id) {
//		for (Person i : gamecharacters) {
//			if (i.getId() == id) {
//				System.out.println(i.toString());
//			}//		
//		}
//		System.out.println("ID: " + id);
//		return null;
//		}

	@GetMapping("/get/{id}")
	public Person getPerson(@PathVariable Integer id) {
		System.out.println("ID: " + id);
		return this.service.getPerson(id);

	}

	@PutMapping("/replace/{id}") // 202 - accepted
	public ResponseEntity<Person> replacePerson(@PathVariable Integer id, @RequestBody Person newPerson) {
		Person body = this.service.replacePerson(id, newPerson);
		ResponseEntity<Person> response = new ResponseEntity<Person>(body, HttpStatus.ACCEPTED);
		return response;
	}

	@DeleteMapping("/remove/{id}") // 204
	public ResponseEntity<?> removePerson(@PathVariable Integer id) {
		this.service.removePerson(id.intValue());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}