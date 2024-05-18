package com.ru.hogwarts.school__school.controllers;
//import com.ru.hogwarts.school__school.services.GetPortService;
import com.ru.hogwarts.school__school.services.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/getPort")
public class InfoController {
    @Value("${server.port}")
    private String serverPort;
    @GetMapping
    public  ResponseEntity<String>  getSeverPort(){
        return  ResponseEntity.ok("server Port = " + serverPort);
    }

}
