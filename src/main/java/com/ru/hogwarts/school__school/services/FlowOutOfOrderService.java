package com.ru.hogwarts.school__school.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service

public class FlowOutOfOrderService {
    public void    studentsAreListedOutOfOrder() {
        new FlowOutOfOrderService ().work();
    }

    private void work() {
        List <String> students  = Arrays.asList("Рон", "Гермиона","Малфой","Падма","Гарри","Алланис");
        print (students );
        print (students);
        new  Thread (()-> {
            try{
                Thread.sleep(2000);

            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            print(students.get(2), students.get(3));


        } ).start();
        new Thread(()-> {
            print(students.get(4), students.get(5));


        } ).start();
    }


    public synchronized void print (String student1, String student2){
        System.out.println(student1);
        System.out.println(student2);

    }
    // Чтобы нарушить последовательность выполнения нам необходимо
    int index;
    private synchronized void print(List<String> students) {
        System.out.println(students.get(index++));
    }



}
