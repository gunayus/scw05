package org.springmeetup.scw05.domain;

import java.io.Serializable;

public class Student implements Serializable {
	public enum Gender { 
		MALE,
		FEMALE
	};

	public Student() {
	}
	
	public Student(String id, String name, Gender gender, int grade) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.grade = grade;
	}
	
	private String id;
	private String name;
	private Gender gender;
	private int grade;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}