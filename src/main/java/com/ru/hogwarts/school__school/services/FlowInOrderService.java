package com.ru.hogwarts.school__school.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.nio.file.Paths.get;

@Service
public class FlowInOrderService {

    public void studentsAreListedInOrder() {
        new FlowInOrderService().work();
    }

    private void work() {
        List<String> students = Arrays.asList("Рон", "Гермиона", "Малфой", "Падма", "Гарри", "Алланис");

        print1(students);
        print1(students);


        new Thread(() -> {
            try{
                Thread.sleep(2000);

            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
// даже если мы в средний поток добавим .sleep  - последовательность все равно будет выполняться
// если мы работаем только с индексом  int index;
            print1(students);


            print1(students);


        }).start();

        new Thread(() -> {

            print1(students);


            print1(students);


        }).start();



    }

    int index;
    private  synchronized  void print1(List<String> students) {
        System.out.println(students.get(index++));
    }
}



