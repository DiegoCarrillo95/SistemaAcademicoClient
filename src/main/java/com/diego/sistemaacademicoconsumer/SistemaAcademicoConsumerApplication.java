package com.diego.sistemaacademicoconsumer;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import com.diego.sistemaacademicoconsumer.models.Course;
import com.diego.sistemaacademicoconsumer.models.Professor;
import com.diego.sistemaacademicoconsumer.models.Student;
import com.diego.sistemaacademicoconsumer.services.CourseService;
import com.diego.sistemaacademicoconsumer.services.ProfessorService;
import com.diego.sistemaacademicoconsumer.services.StudentService;

import com.diego.sistemaacademicoconsumer.utils.Utils;

@SpringBootApplication
public class SistemaAcademicoConsumerApplication implements CommandLineRunner {

	@Autowired
	StudentService studentService;

	@Autowired
	ProfessorService professorService;

	@Autowired
	CourseService courseService;

	@Autowired
	RestTemplate restTemplate;

	public static void main(String[] args) {

		System.setProperty("https.proxySet", "true");
		System.setProperty("https.proxyHost", "192.168.227.1");
		System.setProperty("https.proxyPort", "3128");

		SpringApplication.run(SistemaAcademicoConsumerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@Scope("prototype")
	Logger logger(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMethodParameter().getContainingClass());
	}

	@Override
	public void run(String... args) throws Exception {
		databasePopulator();
	}
	
	//TODO: Passar para classe Utils 
	private void databasePopulator() {
		String[] courses_names = { "Programação 1", "Programação 2", "Cálculo 1", "Cálculo 2", "Banco de Dados",
				"Estrutura de Dados", "Desenvolvimento Web", "Desenvolvimento Mobile" };

		Professor[] professores = new Professor[10];
		Course[] courses = new Course[8];

		Random gerador = new Random();

		for (int i = 0; i < 10; i++) {
			professores[i] = new Professor();
			professores[i].setName(Utils.generateRandomName());
		}

		for (int i = 0; i < 8; i++) {
			courses[i] = new Course();
			courses[i].setName(courses_names[i]);
			courses[i].setProfessor(professores[gerador.nextInt(10)]);
			courses[i] = courseService.insertCourse(courses[i]);
		}

		for (int i = 0; i < 100; i++) {
			Student student = new Student();
			student.setName(Utils.generateRandomName());
			student = studentService.insertStudent(student);
			
			int index = gerador.nextInt(6);
			
			for (int j = 0; j < index; j++) {
				courseService.enrollStudent(student, courses[gerador.nextInt(8)]);
			}
		}
	}
}
