package com.bae.videogame.Service;

import java.util.List;

import com.bae.videogame.domain.Person;

public interface VideoGameService {

	// create
	Person createPerson(Person person);

	// Get All
	List<Person> getAllPerson();

	// Get by ID
	Person getPerson(Integer id);

	// Update
	Person replacePerson(Integer id, Person newPerson);

	// Delete
	void removePerson(Integer id);
}