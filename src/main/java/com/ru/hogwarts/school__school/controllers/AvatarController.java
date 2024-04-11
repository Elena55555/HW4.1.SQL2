
package com.ru.hogwarts.school__school.controllers;

import com.ru.hogwarts.school__school.models.Avatar;

import com.ru.hogwarts.school__school.services.StudentService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController


@RequestMapping("/student")

public class AvatarController {

    private final StudentService studentService;

    public AvatarController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{pageNumber}/{pageSize}")

    public ResponseEntity <List<Avatar>> getAllAvatar(@RequestParam ("page") Integer pageNumber,

                                                      @RequestParam ("size")Integer pageSize){

        List<Avatar> avatars = studentService.getAllAvatars(pageNumber,pageSize);

        return ResponseEntity.ok(avatars);

    }
}

