package com.diego.sistemaacademicoconsumer.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.diego.sistemaacademicoconsumer.models.Professor;

@Service
public class ProfessorService {

	private static final String API_URL = "http://localhost:8080/api/professors";

	private static Logger logger;

	@Autowired
	private RestTemplate restTemplate;

	public ProfessorService(Logger logger) {
		ProfessorService.logger = logger;
	}

	public Professor insertProfessor(Professor professor) {
		return restTemplate.postForObject(API_URL, professor, Professor.class);
	}

	public void deleteProfessorById(int id) {
		try {
			restTemplate.delete(API_URL + "/" + id);
		} catch (HttpClientErrorException e) {
			logger.error("Error deleting the professor");
		}
	}

	public List<Professor> getAllProfessors() {
		try {
			ResponseEntity<Professor[]> responseEntity = restTemplate.getForEntity(API_URL, Professor[].class);
			List<Professor> professors = new ArrayList<>();

			for (Professor professor : responseEntity.getBody()) {
				professors.add(professor);
			}

			return professors;

		} catch (HttpClientErrorException e) {
			logger.error("Id not found!");
			return null;
		}
	}

	public Professor getProfessorById(int id) {
		try {
			ResponseEntity<Professor> responseEntity = restTemplate.getForEntity(API_URL + "/" + id, Professor.class);
			return responseEntity.getBody();
		} catch (HttpClientErrorException e) {
			logger.error("Error finding the professor");
			return null;
		}

	}

}
