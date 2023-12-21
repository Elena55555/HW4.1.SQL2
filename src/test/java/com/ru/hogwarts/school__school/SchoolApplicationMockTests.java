package com.ru.hogwarts.school__school;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ru.hogwarts.school__school.repositories.AvatarRepository;
import com.ru.hogwarts.school__school.repositories.StreamRepository;
import com.ru.hogwarts.school__school.services.*;
import org.json.JSONObject;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.boot.test.mock.mockito.SpyBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.RequestBuilder;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ru.hogwarts.school__school.controllers.StudentController;

import com.ru.hogwarts.school__school.models.Student;

import com.ru.hogwarts.school__school.repositories.FacultyRepository;

import com.ru.hogwarts.school__school.repositories.StudentRepository;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest

class SchoolApplicationMockTests {

    @Autowired

    private MockMvc mockMvc;

    @MockBean

    private StudentRepository studentRepository;

    @MockBean

    private FacultyRepository facultyRepository;
    @MockBean

    private AvatarRepository avatarRepository;
    @MockBean

    private StreamRepository streamRepository;

    @SpyBean

    private StudentService studentService;
    @SpyBean

    private StreamService   streamService;
    @SpyBean

    private FlowInOrderService flowInOrderServiceService;


    @SpyBean

    private FacultyService facultyService;
    @SpyBean

    private FlowOutOfOrderService flowOutOfOrderService;

    @InjectMocks

    private StudentController studentController;

    private RequestBuilder mockRequest;

    private ObjectMapper map;


    @Test

    public void saveAndFindByIdStudentTest() throws Exception {

        final Long id = 14L;

        final String name = "Гарри";

        final int age = 11;

        JSONObject studentObject = new JSONObject();


        studentObject.put("name", name);

        studentObject.put("age", age);


        Student student = new Student();

        student.setId(id);

        student.setName(name);

        student.setAge(age);


        when(studentRepository.save(any(Student.class))).thenReturn(student);


        when(studentRepository.findById(14L)).thenReturn(Optional.of(student));


        mockMvc.perform(MockMvcRequestBuilders

                        .post("/student")

                        .content(studentObject.toString())

                        .contentType(MediaType.APPLICATION_JSON)

                        .accept(MediaType.APPLICATION_JSON))


                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id").value(id))

                .andExpect(jsonPath("$.name").value(name))

                .andExpect(jsonPath("$.age").value(age));


        mockMvc.perform(MockMvcRequestBuilders

                        .get("/student/" + id)

                        .accept(MediaType.APPLICATION_JSON))


                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id").value(id))

                .andExpect(jsonPath("$.name").value(name))

                .andExpect(jsonPath("$.age").value(age));
    }


    @Test

    public void updateStudentTest() throws Exception {

        final Long id = 14L;

        final String name = "Гарри";

        final int age = 11;

        JSONObject studentObject = new JSONObject();


        studentObject.put("name", name);

        studentObject.put("age", age);


        Student student = new Student();

        student.setId(id);

        student.setName(name);

        student.setAge(age);

        when(studentRepository.findById(14L)).thenReturn(Optional.of(student));

        when(studentRepository.save(any(Student.class))).thenReturn(student);


        mockMvc.perform(MockMvcRequestBuilders

                        .get("/student/" + id)


                        .accept(MediaType.APPLICATION_JSON))


                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id").value(id))

                .andExpect(jsonPath("$.name").value(name))

                .andExpect(jsonPath("$.age").value(age));


        mockMvc.perform(MockMvcRequestBuilders

                        .post("/student")

                        .content(studentObject.toString())

                        .contentType(MediaType.APPLICATION_JSON)

                        .accept(MediaType.APPLICATION_JSON))



                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id").value(id))

                .andExpect(jsonPath("$.name").value(name))

                .andExpect(jsonPath("$.age").value(age));
    }

    @Test


    public void deleteStudentTest() throws Exception {

        final Long id = 14L;

        final String name = "Гарри";

        final int age = 11;

        JSONObject studentObject = new JSONObject();


        studentObject.put("name", name);

        studentObject.put("age", age);


        Student student = new Student();

        student.setId(id);

        student.setName(name);

        student.setAge(age);


        Mockito.when(studentRepository.findById (14L)).thenReturn(null);


        mockMvc.perform(MockMvcRequestBuilders

                        .delete("/student/" + id)


                        .contentType(MediaType.APPLICATION_JSON)

                        .accept(MediaType.APPLICATION_JSON))


                .andExpect(status().isOk());
    }


    @Test


    public void AllStudentTest1() throws Exception {

        JSONObject studentObject = new JSONObject();


        studentObject.put("id", 1L);

        studentObject.put("name", "Гарри");

        studentObject.put("age", 11);


        JSONObject studentObject2 = new JSONObject();


        studentObject2.put("id", 2L);

        studentObject2.put("name", "Лесси");

        studentObject2.put("age", 11);


        List<JSONObject> jsonObjectList;

        jsonObjectList = new ArrayList<>();

        jsonObjectList .add(studentObject);

        jsonObjectList . add(studentObject2);


        Student student1 = new Student();

        student1.setId(1L);

        student1.setName("Гарри");

        student1.setAge(11);


        Student student2= new Student();

        student2.setId(2L);

        student2.setName("Лесси");

        student2.setAge(11);


        List <Student> studentslist = new ArrayList<>();

        studentslist.add(student1);

        studentslist.add(student2);



        when(studentRepository.findAll()).thenReturn(studentslist);


        mockMvc.perform(MockMvcRequestBuilders

                        .get("/student")

                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$").isArray())

                .andExpect(jsonPath("$[0]").value(studentslist.get(0)));
    }
}
