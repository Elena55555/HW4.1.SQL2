
package com.ru.hogwarts.school__school.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.Objects;

@Entity

public class Student {

    @Id

    @GeneratedValue  (strategy= GenerationType.IDENTITY)

    private Long id;

    private String name;

    private int age;

    @OneToOne  (cascade = CascadeType.ALL )

    @JsonIgnore
//
//    @JoinColumn(name = "student_id")

    private Avatar avatar;

    @ManyToOne

    @JsonIgnore

    @JoinColumn(name = "faculty_id")

    public Faculty faculty;

    public Student(Long id, String name, int age, Faculty faculty) {

        this.id = id;

        this.name = name;

        this.age =  age;

        this.faculty = faculty;

    }

    public Student() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return  age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override

    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        return getAge() == student.getAge() && Objects.equals(getId(), student.getId()) && Objects.equals(getName(), student.getName()) && Objects.equals(getFaculty(), student.getFaculty());

    }

    @Override

    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getFaculty());
    }

    @Override

    public String toString() {

        return "Student{" +

                "id=" + id +

                ", name='" + name + '\'' +

                ", age=" + age +

                ", faculty=" + faculty +

                '}';

    }


}
