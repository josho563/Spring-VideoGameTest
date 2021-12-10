package com.bae.videogame.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.videogame.Repo.PersonRepo;
import com.bae.videogame.domain.Person;

@Service
public class VideoGameServiceDB implements VideoGameService {

	private PersonRepo repo;

	@Autowired
	public VideoGameServiceDB(PersonRepo repo) { // constructor has injection
		super();
		this.repo = repo;
	}

	@Override
	public Person createPerson(Person person) {
		Person created = this.repo.save(person);
		return created;
	}

	@Override
	public List<Person> getAllPerson() {
		return this.repo.findAll();
	}

	@Override
	public Person getPerson(Integer id) {
		Optional<Person> found = this.repo.findById(id); // Optional is a box for the .get
		return found.get();
	}

	@Override
	public Person replacePerson(Integer id, Person newPerson) {
		Person existing = this.repo.findById(id).get();
		existing.setName(newPerson.getName());
		existing.setGame(newPerson.getGame());
		existing.setWeapon(newPerson.getWeapon());
		existing.setNumberOfGames(newPerson.getNumberOfGames());
		Person updated = this.repo.save(existing);
		return updated;
	}

	@Override
	public void removePerson(Integer id) {
		this.repo.deleteById(id);
	}
}