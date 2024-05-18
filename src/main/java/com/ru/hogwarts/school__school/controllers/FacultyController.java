
package com.ru.hogwarts.school__school.controllers;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.ru.hogwarts.school__school.models.Faculty;

import com.ru.hogwarts.school__school.services.FacultyService;

import java.util.Collection;

@RestController

@RequestMapping("/faculty")

public class FacultyController{

       private final FacultyService facultyService;

        public FacultyController(FacultyService facultyService){
        this.facultyService=facultyService;
        }

        @GetMapping("{id}")

        public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id){

            Faculty faculty=facultyService.findFaculty(id);

            if(faculty==null){

                return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(faculty);
        }

        @GetMapping

        public ResponseEntity<Collection<Faculty>>getAllFacultyInfo(){

            return ResponseEntity.ok(facultyService.getAllFaculties());
        }

        @PostMapping

        public Faculty createFaculty(@RequestBody Faculty faculty){
        return facultyService.addFaculty(faculty);
        }

        @PutMapping

        public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty){

            Faculty foundFaculty=facultyService.editFaculty(faculty);

            if(foundFaculty==null){

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

        return ResponseEntity.ok(foundFaculty);
        }

        @DeleteMapping("{id}")

        public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id){

        facultyService.deleteFaculty(id);

        return ResponseEntity.ok().build();
        }
}