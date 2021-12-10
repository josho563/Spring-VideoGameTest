package com.bae.videogame.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // flags class as a table to spring data
public class Person {

	@Id // told that its PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMEMENT
	private Integer id;
	private String name;
	private String game;

	private String weapon;
	private Integer numberOfGames;
	
	

	public Person(String name, String game, String weapon, Integer numberOfGames) {
		super();
		this.name = name;
		this.game = game;
		this.weapon = weapon;
		this.numberOfGames = numberOfGames;
	}

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(Integer id, String name, String game, String weapon, Integer numberOfGames) {
		super();
		this.id = id;
		this.name = name;
		this.game = game;
		this.weapon = weapon;
		this.numberOfGames = numberOfGames;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public Integer getNumberOfGames() {
		return numberOfGames;
	}

	public void setNumberOfGames(Integer numberOfGames) {
		this.numberOfGames = numberOfGames;
	}

}
