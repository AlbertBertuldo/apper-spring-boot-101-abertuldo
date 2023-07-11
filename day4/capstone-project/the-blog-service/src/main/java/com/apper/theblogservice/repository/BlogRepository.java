package com.apper.theblogservice.repository;

import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<Blog, String> {
}
