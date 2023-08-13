
package com.ru.hogwarts.school__school.services;

import com.ru.hogwarts.school__school.interfaces.StudentsWithBigId;

import com.ru.hogwarts.school__school.models.Avatar;

import com.ru.hogwarts.school__school.repositories.AvatarRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import com.ru.hogwarts.school__school.models.Student;

import com.ru.hogwarts.school__school.repositories.StudentRepository;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.nio.file.Files;

import java.nio.file.Path;

import java.util.Collection;

import java.util.List;

import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service

@Transactional

public class StudentService {

    @Value(value = "${avatar.dir.path}")

    private String avatarDir;

    File directory = new File("avatar");

   private final StudentRepository studentRepository;

    private final AvatarRepository avatarRepository;
    Logger logger= LoggerFactory.getLogger(StudentService.class );


    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {

        this.studentRepository = studentRepository;

        this.avatarRepository = avatarRepository;

    }

    public Student findStudent(long id) {
        logger.debug("Find student {} by id",  studentRepository.findById(id).get());

        return studentRepository.findById(id).get() ;
    }

    public Student editStudent(Student student){
        logger.debug("Edit student {} ",  studentRepository.save(student));

        return studentRepository.save(student);
    }

    public Student addStudent(Student student) {
        logger.debug("Add student {} ",  studentRepository.save(student));

        return studentRepository.save(student);
    }

    public void deleteStudent (long id) {
        logger.debug("Delete student {} ");

        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents(){
        logger.debug("Get All Students {} ",  studentRepository.findAll());

        return studentRepository.findAll();
    }


    public Collection<Student> findByAge(int age) {
        logger.debug(" Find student By Age {} ",  studentRepository.findAllByAge(age));
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> getAllStudentsByAgeBetween(int min, int max) {
        logger.debug(" Get All Students By Age Between {} ",  studentRepository.findStudentsByAgeBetween(min, max));

        return studentRepository.findStudentsByAgeBetween(min, max);

    }


    public Avatar findAvatar(long studentId) {
        logger.debug(" Find Avatar {} ",  avatarRepository.findByStudentId(studentId).orElseThrow());

        return avatarRepository.findByStudentId(studentId).orElseThrow();
    }

    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        logger.debug("upload Avatar {}  ");

        Student student = findStudent(studentId);

        Path filePath = Path.of(avatarDir, studentId + "." + getExtension(Objects.requireNonNull(file.getOriginalFilename())));

        Files.createDirectories(filePath.getParent());

        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();

             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);

             BufferedInputStream bis = new BufferedInputStream(is, 1024);

             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);

        ) {

            bis.transferTo(bos);

        }

        Avatar avatar = avatarRepository.findByStudentId(studentId).orElseGet(Avatar::new);

        avatar.setStudent(student);

        avatar.setFilePath(filePath.toString());

        avatar.setFileSize(file.getSize());

        avatar.setMediaType(file.getContentType());

        avatar.setData(file.getBytes());

        avatarRepository.save(avatar);

    }

    private String getExtension(String fileName) {
        logger.debug(" Get Extension {} ", fileName.substring(fileName.lastIndexOf(".") + 1));

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Integer getStudentsByNumber(){
        logger.debug(" Get Students ByNumber {} ", studentRepository.getStudentsByNumber());

        return studentRepository.getStudentsByNumber();
    }

    public Integer getAvgAgeStudents(){
        logger.debug(" Get Avg Age Students {} ", studentRepository.getAvgAgeStudents());

        return studentRepository.getAvgAgeStudents();
    }

    public List<StudentsWithBigId> getStudentsWithBigId(){
        logger.debug(" List <StudentsWithBigId> {} ", studentRepository.getLast5StudentsWithBigId());

        return studentRepository.getLast5StudentsWithBigId();
    }

    public List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        logger.debug(" getAllAvatars{} ", avatarRepository.findAll(pageRequest).getContent());


        return avatarRepository.findAll(pageRequest).getContent();//

    }
}
