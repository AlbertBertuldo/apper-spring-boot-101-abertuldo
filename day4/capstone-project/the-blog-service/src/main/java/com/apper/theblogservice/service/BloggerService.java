package com.apper.theblogservice.service;

import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BlogRepository;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BloggerService {
    private final BloggerRepository bloggerRepository;

    private final BlogRepository blogRepository;

    public BloggerService(BloggerRepository bloggerRepository, BlogRepository blogRepository) {
        this.bloggerRepository = bloggerRepository;
        this.blogRepository = blogRepository;
    }
    public Blogger createBlogger(String email, String name, String password){

        Blogger blogger = new Blogger();
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);
        return bloggerRepository.save(blogger);

    }

    public Blogger getBlogger(String id) {
        Optional<Blogger> bloggerResult = bloggerRepository.findById(id);
        return bloggerResult.get();
    }

    public boolean isEmailRegistered(String email) {
        Iterable<Blogger> bloggers = bloggerRepository.findAll();
        for (Blogger blogger : bloggers) {
            if (email.equals(blogger.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public List<Blogger> getAllBlogger() {
        Iterable<Blogger> bloggers = bloggerRepository.findAll();
        List<Blogger> bloggerList = new ArrayList<>();
        for (Blogger blogger : bloggers) {
            bloggerList.add(blogger);
        }
        return bloggerList;
    }

    public Blog createBlog(String title, String body, String bloggerId) {
        Blogger blogger = getBlogger(bloggerId);

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setBody(body);
        blog.setCreatedAt(LocalDateTime.now());
        blog.setLastUpdate(LocalDateTime.now());
        blog.setBlogger(blogger);

        blogger.getBlogs().add(blog);
        //bloggerRepository.save(blogger);
        return blogRepository.save(blog);
    }

    public Blog updateBlog(String blogId, String title, String body) {
        Optional<Blog> blogResult = blogRepository.findById(blogId);
        if (blogResult.isPresent()) {
            Blog blog = blogResult.get();
            blog.setTitle(title);
            blog.setBody(body);
            blog.setLastUpdate(LocalDateTime.now());
            return blogRepository.save(blog);
        }
        return null;
    }

    public Blog getBlog(String id) {
        Optional<Blog> blogResult = blogRepository.findById(id);
        return blogResult.orElse(null);
    }

    public List<Blog> getAllBlogs() {
        return StreamSupport.stream(blogRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Blog> getBlogsByBlogger(String bloggerId) {
        Optional<Blogger> bloggerResult = bloggerRepository.findById(bloggerId);
        if (bloggerResult.isPresent()) {
            Blogger blogger = bloggerResult.get();
            return blogger.getBlogs();
        }
        return new ArrayList<>();
    }

}
