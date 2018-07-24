package com.diego.sistemaacademicoconsumer.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.diego.sistemaacademicoconsumer.models.Student;

@Service
public class StudentService {

	private static final String API_URL = "http://localhost:8080/api/students";

	private static Logger logger;

	@Autowired
	private RestTemplate restTemplate;

	public StudentService(Logger logger) {
		StudentService.logger = logger;
	}

	public Student insertStudent(Student student) {
		return restTemplate.postForObject(API_URL, student, Student.class);
	}

	public void deleteStudentById(int id) {
		try {
			restTemplate.delete(API_URL + "/" + id);
		} catch (HttpClientErrorException e) {
			logger.error("Error deleting the student");
		}
	}

	public List<Student> getAllStudents() {
		try {
			ResponseEntity<Student[]> responseEntity = restTemplate.getForEntity(API_URL, Student[].class);
			List<Student> students = new ArrayList<>();

			for (Student student : responseEntity.getBody()) {
				students.add(student);
			}

			return students;

		} catch (HttpClientErrorException e) {
			logger.error("Id not found!");
			return null;
		}
	}

	public Student getStudentById(int id) {
		try {
			ResponseEntity<Student> responseEntity = restTemplate.getForEntity(API_URL + "/" + id, Student.class);
			return responseEntity.getBody();
		} catch (HttpClientErrorException e) {
			logger.error("Error finding the student");
			return null;
		}

	}

}
