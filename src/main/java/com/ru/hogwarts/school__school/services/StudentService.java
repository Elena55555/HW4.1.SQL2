
package com.ru.hogwarts.school__school.services;

import com.ru.hogwarts.school__school.interfaces.StudentsWithBigId;

import com.ru.hogwarts.school__school.models.Avatar;

import com.ru.hogwarts.school__school.repositories.AvatarRepository;

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


    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {

        this.studentRepository = studentRepository;

        this.avatarRepository = avatarRepository;

    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get() ;
    }

    public Student editStudent(Student student){
        return studentRepository.save(student);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent (long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents(){
        return studentRepository.findAll();
    }


    public Collection<Student> findByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> getAllStudentsByAgeBetween(int min, int max) {

        return studentRepository.findStudentsByAgeBetween(min, max);

    }


    public Avatar findAvatar(long studentId) {
        return avatarRepository.findByStudentId(studentId).orElseThrow();
    }

    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {

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
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Integer getStudentsByNumber(){
        return studentRepository.getStudentsByNumber();
    }

    public Integer getAvgAgeStudents(){
        return studentRepository.getAvgAgeStudents();
    }

    public List<StudentsWithBigId> getStudentsWithBigId(){
        return studentRepository.getLast5StudentsWithBigId();
    }

    public List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);

        return avatarRepository.findAll(pageRequest).getContent();//

    }
}
