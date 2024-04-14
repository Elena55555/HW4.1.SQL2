
package com.ru.hogwarts.school__school.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;

import java.util.Collection;

import java.util.Objects;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import javax.persistence.OneToMany;

import javax.persistence.*;
import java.util.Objects;

@Entity

public class Faculty {

    @Id

    @GeneratedValue  (strategy= GenerationType.IDENTITY)

    private Long id;

    private String name;

    private String color;

    @OneToMany(  cascade = CascadeType.ALL)

    @JsonIgnore
//    @JoinColumn(name = "faculty_id")

    private Collection<Student> students;

    @OneToMany( cascade = CascadeType.ALL)

    @JsonIgnore
//    @JoinColumn(name = "student_id")

    private Collection<Avatar> avatars;

    public Faculty(Long id, String name, String color, Collection<Student> students) {

        this.id = id;

        this.name = name;

        this.color = color;

        this.students = students;
    }

    public Faculty() {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override

    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Faculty)) return false;

        Faculty faculty = (Faculty) o;

        return Objects.equals(getId(), faculty.getId()) && Objects.equals(getName(), faculty.getName()) && Objects.equals(getColor(), faculty.getColor()) && Objects.equals(getStudents(), faculty.getStudents());

    }

    @Override

    public int hashCode() {
        return Objects.hash(getId(), getName(), getColor(), getStudents());
    }

    @Override


    public String toString() {

        return "Faculty{" +

                "id=" + id +

                ", name='" + name + '\'' +

                ", color='" + color + '\'' +

                ", students=" + students +

                '}';

    }
}
