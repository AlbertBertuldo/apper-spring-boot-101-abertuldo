package com.apper.theblogservice;

import com.apper.theblogservice.exceptions.BlogNotFoundException;
import com.apper.theblogservice.exceptions.BloggerNotFoundException;
import com.apper.theblogservice.exceptions.EmailAlreadyRegisteredException;
import com.apper.theblogservice.exceptions.InvalidBloggerIdException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.payload.*;
import com.apper.theblogservice.service.BloggerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("blogger")
public class BloggerApi {

    private final BloggerService bloggerService;

    public BloggerApi(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    @PostMapping("/blogger")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBloggerResponse createBlogger(@RequestBody @Valid CreateBloggerRequest request) throws EmailAlreadyRegisteredException {
        if (bloggerService.isEmailRegistered(request.getEmail())) {
            throw new EmailAlreadyRegisteredException("Email already registered");
        }

        Blogger createdBlogger = bloggerService.createBlogger(request.getEmail(), request.getName(), request.getPassword());
        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId(createdBlogger.getId());
        response.setDateRegistration(createdBlogger.getCreatedAt());
        return response;

    }

    @GetMapping("/blogger/{id}")
    public BloggerDetails getBlogger(@PathVariable String id) throws BloggerNotFoundException{
        Blogger blogger = bloggerService.getBlogger(id);

        BloggerDetails bloggerDetails = new BloggerDetails();
        bloggerDetails.setId(blogger.getId());
        bloggerDetails.setName(blogger.getName());
        bloggerDetails.setEmail(blogger.getEmail());
        bloggerDetails.setDateRegistration(blogger.getCreatedAt());

        return bloggerDetails;
    }

    @GetMapping("/blogger")
    public List<BloggerDetails> getAllBloggers() throws BloggerNotFoundException{
        List<BloggerDetails> responseList = new ArrayList<>();
        for (Blogger blogger : bloggerService.getAllBlogger()){
            BloggerDetails response = createGetAllBloggerResponse(blogger);
            responseList.add(response);
        }

        return responseList;
    }

    private BloggerDetails createGetAllBloggerResponse(Blogger blogger){
        BloggerDetails response = new BloggerDetails();
        response.setId(blogger.getId());
        response.setName(blogger.getName());
        response.setEmail(blogger.getEmail());
        response.setDateRegistration(blogger.getCreatedAt());
        return response;
    }

    @PostMapping("/blog")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBlogResponse createBlog(@RequestBody @Valid CreateBlogRequest request) throws BloggerNotFoundException, InvalidBloggerIdException {
        Blog createdBlog = bloggerService.createBlog(request.getTitle(), request.getBody(), request.getBloggerId());

        CreateBlogResponse response = new CreateBlogResponse();
        response.setId(createdBlog.getId());
        response.setBloggerId(createdBlog.getBlogger().getId());
        response.setCreatedAt(createdBlog.getCreatedAt());
        response.setLastUpdated(createdBlog.getLastUpdate());
        return response;
    }


    @GetMapping("/blog/{id}")
    public BlogDetails getBlog(@PathVariable String id) throws BlogNotFoundException{
        Blog blog = bloggerService.getBlog(id);

        BlogDetails blogDetails = new BlogDetails();
        blogDetails.setBloggerId(blog.getBlogger().getId());
        blogDetails.setTitle(blog.getTitle());
        blogDetails.setBody(blog.getBody());
        blogDetails.setCreatedAt(blog.getCreatedAt());
        blogDetails.setLastUpdated(blog.getLastUpdate());

        return blogDetails;
    }

    @GetMapping("/blog")
    public List<BlogDetails> getAllBlogs() throws BlogNotFoundException {
        List<BlogDetails> responseList = new ArrayList<>();
        for (Blog blog : bloggerService.getAllBlogs()) {
            BlogDetails response = createBlogDetailsResponse(blog);
            responseList.add(response);
        }

        return responseList;
    }


    private BlogDetails createBlogDetailsResponse(Blog blog) {
        BlogDetails response = new BlogDetails();
        response.setBloggerId(blog.getBlogger().getId());
        response.setTitle(blog.getTitle());
        response.setBody(blog.getBody());
        response.setCreatedAt(blog.getCreatedAt());
        response.setLastUpdated(blog.getLastUpdate());
        return response;
    }


    @PutMapping("/blog/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBlog(@PathVariable String blogId, @RequestBody UpdateBlogRequest request) throws BlogNotFoundException {
        bloggerService.updateBlog(blogId, request.getTitle(), request.getBody());
    }

    @GetMapping("/blog/blogger/{bloggerId}")
    public List<BlogDetails> getBlogsByBlogger(@PathVariable String bloggerId) throws BloggerNotFoundException, BlogNotFoundException{
        List<BlogDetails> responseList = new ArrayList<>();
        for (Blog blog : bloggerService.getBlogsByBlogger(bloggerId)) {
            BlogDetails response = createBlogDetailsResponse(blog);
            responseList.add(response);
        }

        return responseList;
    }


}
