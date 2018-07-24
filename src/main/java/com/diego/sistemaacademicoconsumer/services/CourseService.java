package com.diego.sistemaacademicoconsumer.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.diego.sistemaacademicoconsumer.models.Course;
import com.diego.sistemaacademicoconsumer.models.Student;

@Service
public class CourseService {

	private static final String API_URL = "http://localhost:8080/api/courses";
	
	private static Logger logger;

	@Autowired
	private RestTemplate restTemplate;

	public CourseService(Logger logger) {
		CourseService.logger = logger;
	}

	public Course insertCourse(Course course) {
		return restTemplate.postForObject(API_URL, course, Course.class);
	}

	public void deleteCourseById(int id) {
		try {
			restTemplate.delete(API_URL + "/" + id);
		} catch (HttpClientErrorException e) {
			logger.error("Error deleting the course");
		}
	}
	
	public void enrollStudent(Student student, Course course) {
		course.getStudents().add(student);
		restTemplate.put(API_URL + "/" + course.getId(), course);
	}

	public List<Course> getAllCourses() {
		try {
			ResponseEntity<Course[]> responseEntity = restTemplate.getForEntity(API_URL, Course[].class);
			List<Course> courses = new ArrayList<>();

			for (Course course : responseEntity.getBody()) {
				courses.add(course);
			}

			return courses;

		} catch (HttpClientErrorException e) {
			logger.error("Id not found!");
			return null;
		}
	}

	public Course getCourseById(int id) {
		try {
			ResponseEntity<Course> responseEntity = restTemplate.getForEntity(API_URL + "/" + id, Course.class);
			return responseEntity.getBody();
		} catch (HttpClientErrorException e) {
			logger.error("Error finding the course");
			return null;
		}

	}

}
