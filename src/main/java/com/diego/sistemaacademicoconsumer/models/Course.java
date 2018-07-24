package com.diego.sistemaacademicoconsumer.models;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {

	private int id;
	
	private String name;
	
	private Professor professor;
	
	private Set<Student> students = new HashSet<>();

	@Override
	public String toString() {
		String ret = "Id: " + id + " - Name: " + name + "\nProfessor: " + professor + "\nStudents:";
		for(Student student : students) {
			ret = ret.concat("\n " + student.toString());
		}	
		return ret;
	}
}
