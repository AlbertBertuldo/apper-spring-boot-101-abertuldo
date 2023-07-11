package com.apper.theblogservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

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

    @OneToMany(mappedBy = "blogger", cascade = CascadeType.ALL)
    private List<Blog> blogs = new ArrayList<>();


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

        lastUpdate = LocalDateTime.now();
    }

}
