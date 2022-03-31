package ru.ledovskikh.andrey.amigotest.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ledovskikh.andrey.amigotest.data.Student;

import java.util.Optional;

/**
 * @author Andrey Ledovskikh
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
