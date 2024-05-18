
package com.ru.hogwarts.school__school.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ru.hogwarts.school__school.models.Faculty;

import com.ru.hogwarts.school__school.repositories.FacultyRepository;

import java.util.Collection;

@Service

public class FacultyService {

    private final FacultyRepository facultyRepository;
    Logger logger= LoggerFactory.getLogger(FacultyService.class );

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty findFaculty(long id) {
        logger.debug("Find faculty {} by id", facultyRepository.findById(id).get());

        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {

        logger.debug("Edit Faculty {}", facultyRepository.save(faculty) );
        return facultyRepository.save(faculty);

    }

    public Faculty addFaculty(Faculty faculty) {
        logger.debug("Add Faculty {}", facultyRepository.save(faculty) );
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.debug("Delete Faculty {}");

        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties(){
        logger.debug("Get All Faculties {}", facultyRepository.findAll() );

        return facultyRepository.findAll();
    }

    public  Collection <Faculty>  getFacultiesByNameOrColor(String name,String color) {
        logger.debug("Get Faculties By Name Or Color {}", facultyRepository.findFacultiesByNameOrColorIgnoreCase(name, color) );

        return facultyRepository.findFacultiesByNameOrColorIgnoreCase(name, color);
    }
}