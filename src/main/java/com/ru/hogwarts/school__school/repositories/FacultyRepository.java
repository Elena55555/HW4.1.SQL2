
package com.ru.hogwarts.school__school.repositories;

import com.ru.hogwarts.school__school.models.Faculty;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.Collection;

@Repository

public interface FacultyRepository  extends JpaRepository<Faculty, Long> {

       Collection<Faculty> findFacultiesByNameOrColorIgnoreCase(String name, String color);

    }