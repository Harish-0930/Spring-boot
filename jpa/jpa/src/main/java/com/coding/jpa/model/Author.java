package com.coding.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@NamedQuery(
        name = "Author.findByNamedQuery",
        query = "select a from Author a where a.age>= :age"
)
@NamedQuery(
        name  = "Author.updateByNamedQuery",
        query = "update Author a set a.age = :age"
)
public class Author extends BaseEntity {
    // int value by default is 0;
    // Integer value by default is NULL;


    private String firstName;
    private String lastName;
    @Column(
            unique = true,
            nullable = false
    )
    private String email;
    private int age;


    @ManyToMany(mappedBy = "authors",fetch = FetchType.EAGER)
    private List<Course> courses;
}
