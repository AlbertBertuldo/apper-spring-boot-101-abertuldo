package com.apper.theblogservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

//the @Entity annotation is used to mark a Java class as an entity, representing a table in a relational database. This annotation is part of the Java Persistence API (JPA) and is commonly used in conjunction with JPA and an underlying ORM (Object-Relational Mapping) framework, such as Hibernate.
@Entity
//Represents table name BLOGGER
@Table(name = "BLOGGER")
@Data
public class Blogger {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "COMPLETE_NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "LAST_UPDATED")
    private LocalDateTime lastUpdate;


    //Better approach for creating TIMESTAMP when created/saved
    @PrePersist
    public void setCreatedAt(){
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        lastUpdate = now;
    }

    // Timestamp before an entity is updated
    @PreUpdate
    public void setLastUpdate(){
        LocalDateTime lastUpdate = LocalDateTime.now();
    }

}
