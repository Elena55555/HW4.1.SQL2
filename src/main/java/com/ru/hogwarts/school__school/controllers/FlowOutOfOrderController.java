package com.ru.hogwarts.school__school.controllers;

import com.ru.hogwarts.school__school.services.FlowOutOfOrderService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FlowOutOfOrderController {
    private final FlowOutOfOrderService  flowOutOfOrderService ;

    public FlowOutOfOrderController(FlowOutOfOrderService  flowOutOfOrderService) {
        this.flowOutOfOrderService  = flowOutOfOrderService ;
    }


    @RequestMapping("/students")
    public void getStudents (){
          flowOutOfOrderService.studentsAreListedOutOfOrder();

    }
}
