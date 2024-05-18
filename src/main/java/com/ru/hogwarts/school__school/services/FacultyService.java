
package com.ru.hogwarts.school__school.services;

import org.springframework.stereotype.Service;

import com.ru.hogwarts.school__school.models.Faculty;

import com.ru.hogwarts.school__school.repositories.FacultyRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional


public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }

    public  Collection <Faculty>  getFacultiesByNameOrColor(String name,String color) {

        return facultyRepository.findFacultiesByNameOrColorIgnoreCase(name, color);
    }
}