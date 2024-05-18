package com.ru.hogwarts.school__school.repositories;

import com.ru.hogwarts.school__school.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface   StreamRepository extends JpaRepository<Student, Long> {

};