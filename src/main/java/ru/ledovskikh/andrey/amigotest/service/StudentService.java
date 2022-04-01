package ru.ledovskikh.andrey.amigotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ledovskikh.andrey.amigotest.data.Student;
import ru.ledovskikh.andrey.amigotest.data.dao.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Andrey Ledovskikh
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
        final Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                    "student with id " + studentId + " does not exist"));

        if (name != null
                && name.length() > 0
                && !Objects.equals(name, student.getName())) {
            student.setName(name);
        }

        if (email != null
                && email.length() > 0
                && !Objects.equals(email, student.getEmail())) {

            if (studentRepository.findStudentByEmail(email).isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
