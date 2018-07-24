package com.diego.sistemaacademicoconsumer.utils;


import org.springframework.web.client.RestTemplate;

import com.diego.sistemaacademicoconsumer.utils.pojo.RandomResponse;

public class Utils {


	private static final String URL = "https://randomuser.me/api/?nat=br";

	public static String generateRandomName() {

		RestTemplate restTemplate = new RestTemplate();
		RandomResponse response = restTemplate.getForObject(URL, RandomResponse.class);

		String firstName = response.getResults().get(0).getName().getFirst().substring(0, 1).toUpperCase()
				+ response.getResults().get(0).getName().getFirst().substring(1);
		String lastName = response.getResults().get(0).getName().getLast();
		
		if (lastName.contains(" ")) {
			lastName = lastName.substring(0, lastName.indexOf(" ", 0)) + " "
					+ lastName.substring(lastName.indexOf(" ", 0) + 1, lastName.indexOf(" ", 0) + 2).toUpperCase()
					+ lastName.substring(lastName.indexOf(" ", 0) + 2);

		} else {
			lastName =  lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
		}

		return firstName + " " + lastName;
	}
	
}
