package com.ru.hogwarts.school__school.controllers;

import com.ru.hogwarts.school__school.services.StreamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/getValues")
public class StreamController {
    private final StreamService streamService ;
    public  StreamController(StreamService streamService) {
        this.streamService = streamService;
    }
    @GetMapping
    public ResponseEntity<Optional<String>> getAllStudentNameOnA(){

        Optional<String> namesStudents = streamService.getAllNamesStudentsOnA();

        return ResponseEntity.ok(namesStudents);
    }
    @GetMapping("/avg")

    public ResponseEntity<Optional<Integer>> getAvgAgeStudentName(){

        Optional<Integer> avgAgeStudents = streamService.getAvgAgeStudentName();

        return ResponseEntity.ok(avgAgeStudents);
    }
    @GetMapping("/long")

    public ResponseEntity<List<String>> getLongNameFaculty2(){

        List<String> longestWord= streamService.findLongestWords( );

        return ResponseEntity.ok(longestWord);
    }
    @GetMapping("/number")
    public ResponseEntity <Long>  getIntValue2() {

        long a  =  streamService.getIntValue2( );
        return ResponseEntity.ok(a);
    }
}








