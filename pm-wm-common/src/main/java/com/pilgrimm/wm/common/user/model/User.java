package com.pilgrimm.wm.common.user.model;

public class User {

	private int id;
	private String name;
	private int age;

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public User(int id, String name, int age) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
	}
}
