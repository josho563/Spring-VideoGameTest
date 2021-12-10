package com.bae.videogame.Service;

import java.util.ArrayList;
import java.util.List;

import com.bae.videogame.domain.Person;

public class VideoGameServiceList implements VideoGameService {

	private List<Person> persons = new ArrayList<>();

	@Override
	public Person createPerson(Person person) {
		this.persons.add(person);
		Person created = this.persons.get(this.persons.size() - 1);
		return created;
	}

	@Override
	public List<Person> getAllPerson() {
		return this.persons;
	}

	@Override
	public Person getPerson(Integer id) {
		return this.persons.get(id);
	}

	@Override
	public Person replacePerson(Integer id, Person newPerson) {
		Person found = this.persons.set(id, newPerson);
		return found;
	}

	@Override
	public void removePerson(Integer id) {
		this.persons.remove(id.intValue());

	}
}
