package com.ru.hogwarts.school__school;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;

import com.ru.hogwarts.school__school.controllers.StudentController;

import com.ru.hogwarts.school__school.models.Student;

import com.ru.hogwarts.school__school.repositories.StudentRepository;

import com.ru.hogwarts.school__school.services.StudentService;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SchoolSchoolApplicationTests_ {

	@LocalServerPort

	private int port;

	@Autowired

	private StudentController studentController;


	@Autowired

	private TestRestTemplate testrestTemplate;

	@Autowired

	private StudentRepository studentRepository;

	@Autowired

	private StudentService studentService;


	@Test

	void contextLoads() throws Exception {

		Assertions.assertThat(studentController).isNotNull();// org.assertj.core.api.!!!

	}


	@Test

	public void testGetStudents() throws Exception {
		Assertions
				.assertThat(this.testrestTemplate.getForObject("http://localhost:" + port + "/student", String.class))
				.isNotEmpty();
	}


	@Test
	public void testPostStudents() throws Exception {

		Student student1 = new Student();

		student1.setId(2L);

		student1.setName("Миртл");

		student1.setAge(15);

		Assertions

				.assertThat(this.testrestTemplate.postForObject("http://localhost:" + port + "/student/", student1, String.class))

				.isNotEmpty();
	}


	@Test

	public void testEditStudents() throws Exception {

		Student student1 = new Student();

		student1.setId(1L);

		student1.setName("Джон");

		student1.setAge(15);

		Assertions

				.assertThat(this.testrestTemplate.postForObject("http://localhost:" + port + "/student/", student1, String.class))

				.isNotEmpty();
	}

	@Test

	public void RestDeleteStudents() throws Exception {

		Student student1 = new Student();

		student1.setId(1L);

		student1.setName("Джон");

		student1.setAge(15);

		boolean empty = isEmpty();

		testrestTemplate.delete("http://localhost:" + port + "/student/", student1, String.class);

	}
}



