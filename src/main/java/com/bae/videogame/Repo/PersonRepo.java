package com.bae.videogame.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.videogame.domain.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

	// Spring will auto-generate all the basic CRUD functionality

	List<Person> findByName(String name);

	List<Person> findByGame(String game);

	List<Person> findByWeapon(String weapon);

	List<Person> findByNumberOfGames(Integer numberOfGames);

}