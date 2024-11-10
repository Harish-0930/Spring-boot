package com.coding.jpa;

import com.coding.jpa.model.Author;
import com.coding.jpa.model.Video;
import com.coding.jpa.repositories.AuthorRepository;
import com.coding.jpa.repositories.VideoRepository;
import com.coding.jpa.specification.AuthorSpecification;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class JpaApplication {
    public static void main(String[] args) {

        SpringApplication.run(JpaApplication.class, args);
        System.out.println("hello world");
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthorRepository repository,
            VideoRepository videoRepository
    ) {
        return args -> {
           /* for(int i=0;i<50;i++){
                Faker faker = new Faker();
                String first_name = faker.name().firstName();
                var author = Author.builder()
                        .firstName(first_name)
                        .lastName(faker.name().lastName())
                        .age(faker.number().numberBetween(20,80))
                        .email(faker.name().username()+"@gmail.com")
                        .createdBy(first_name)
                        .createdAt(LocalDateTime.now())
                        .build();
                repository.save(author);
            }*/
            // update author where id  =1
/*            var author = Author.builder()
                    .id(1)
                    .firstName("Naveen")
                    .lastName("munagala")
                    .age(22)
                    .email("Naveen@gmail.com")
                    .createdBy("Naveen")
                    .createdAt(LocalDateTime.now())
                    .build();
            repository.save(author);*/
            // update author a set a.age = 22 where a.id = 1
         // repository.updateAuthor(28,50);
/*			var video = Video.builder()
					.name("abc")
					.length(5)
					.build();
			videoRepository.save(video);*/
            // findByNamedQuery
//            var authors = repository.findByNamedQuery(60);
//            authors.forEach(author -> {
//                System.out.println("Full Author: " + author);
//                System.out.println("First Name: " + author.getFirstName());
//                System.out.println("Last Name: " + author.getLastName());
//                System.out.println("Email: " + author.getEmail());
//                System.out.println("Age: " + author.getAge());
//                System.out.println("Courses: " + author.getCourses());
//            });
            Specification<Author> spec = Specification.
                    where(AuthorSpecification.hasAge(68))
                    .and(AuthorSpecification.firstnameLike("J"));
            repository.findAll(spec).forEach(System.out::println);
        };
    }

}
