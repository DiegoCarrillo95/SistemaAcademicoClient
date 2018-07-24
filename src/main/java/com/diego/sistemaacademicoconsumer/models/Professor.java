package com.diego.sistemaacademicoconsumer.models;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Professor {

	private int id;
	
	private String name;
	
	@Override
	public String toString() {
		return "Id: " + id + " - Name: " + name;
	}
	
}
