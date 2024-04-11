
package com.ru.hogwarts.school__school.repositories;

import com.ru.hogwarts.school__school.interfaces.StudentsWithBigId;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ru.hogwarts.school__school.models.Student;

import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.Collection;

import java.util.List;

import java.util.Optional;

@Repository

public interface   StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findById(long id);


    Collection<Student> findAllByAge(int age);

    Collection <Student>  findStudentsByAgeBetween (int min, int max);

    void deleteById(long id);
    // для запроса сколько всего студентов в школе

    @Query(value = " SELECT COUNT (id) From student", nativeQuery = true)

    Integer getStudentsByNumber();
    // для запроса

    @Query(value = " SELECT AVG (age) From student", nativeQuery = true)

    Integer getAvgAgeStudents();
    // для запроса

    @Query(value = " SELECT * From student OFFSET 5", nativeQuery = true)

    List<StudentsWithBigId> getLast5StudentsWithBigId();
}