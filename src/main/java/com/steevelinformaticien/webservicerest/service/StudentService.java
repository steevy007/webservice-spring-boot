package com.steevelinformaticien.webservicerest.service;

import com.steevelinformaticien.webservicerest.exception.StudentNotFoundException;
import com.steevelinformaticien.webservicerest.model.Student;
import com.steevelinformaticien.webservicerest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(Student student) {
        return this.studentRepository.save(student);
    }

    public List<Student> getAll() {
        return this.studentRepository.findAll();
    }

    public Student findById(Long id) {
        Optional<Student> student = this.studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new StudentNotFoundException(String.format("Student with id %s not found", id));
        }
        return student.orElse(null);
    }

    public Student update(Student student, Long id) {
        Optional<Student> find = this.studentRepository.findById(id);
        if (!find.isPresent()) {
            throw new StudentNotFoundException(String.format("Student with id %s not found", id));
        }

        return this.studentRepository.save(student);
    }

    public void delete(Long id){
        Optional<Student> find=this.studentRepository.findById(id);
        if(!find.isPresent()) {
            throw new StudentNotFoundException(String.format("Student with id %s not found", id));
        }
        this.studentRepository.delete(find.get());
    }
}
