package ru.ledovskikh.andrey.amigotest.data.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ledovskikh.andrey.amigotest.data.Student;
import ru.ledovskikh.andrey.amigotest.data.dao.StudentRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * @author Andrey Ledovskikh
 */
@Configuration
public class StudentConfiguration {

    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            final Student andrey = new Student(
                    "Andrey",
                    "andrey@gmail.com",
                    LocalDate.of(1986, Month.NOVEMBER, 27)
            );

            final Student alex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(1990, Month.AUGUST, 20)
            );

            studentRepository.saveAll(List.of(andrey, alex));
        };
    }

}
