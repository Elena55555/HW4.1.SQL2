package com.ru.hogwarts.school__school.controllers;

import com.ru.hogwarts.school__school.services.FlowInOrderService;
import com.ru.hogwarts.school__school.services.FlowOutOfOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowInOrderController {
    private final FlowInOrderService flowInOrderService ;

    public FlowInOrderController(FlowInOrderService flowInOrderService) {
        this.flowInOrderService  = flowInOrderService ;
    }


    @RequestMapping("/students_")
    public void getStudents (){
        flowInOrderService.studentsAreListedInOrder();

    }
}
