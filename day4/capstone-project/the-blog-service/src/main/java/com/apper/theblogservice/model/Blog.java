package com.apper.theblogservice.model;

import jakarta.persistence.Table;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="BLOG")
@Data
public class Blog {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BODY")
    private String body;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "LAST_UPDATED")
    private LocalDateTime lastUpdate;

    @ManyToOne
    private Blogger blogger;


    @PrePersist
    public void setCreatedAt(){
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        lastUpdate = now;
    }

    // Timestamp before an entity is updated
    @PreUpdate
    public void setLastUpdate(){

        lastUpdate = LocalDateTime.now();
    }
}

