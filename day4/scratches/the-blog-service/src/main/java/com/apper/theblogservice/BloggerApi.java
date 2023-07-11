package com.apper.theblogservice;

import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.payload.BloggerDetails;
import com.apper.theblogservice.payload.CreateBloggerRequest;
import com.apper.theblogservice.payload.CreateBloggerResponse;
import com.apper.theblogservice.service.BloggerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("blogger")
public class BloggerApi {

    private final BloggerService bloggerService;

    public BloggerApi(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)// must be 201
    //@Valid used or helps to validate response
    public CreateBloggerResponse createBlogger(@RequestBody @Valid CreateBloggerRequest request){

        Blogger createdBlogger = bloggerService.createBlogger(request.getEmail(), request.getName(), request.getPassword());

        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId(createdBlogger.getId());
        response.setDateRegistration(createdBlogger.getCreatedAt());
        return response;
    }

    // Created GetMapping, and implemented getBlogger() on BloggerService, Blogger repository has an internal function called  bloggerRepository.findById() which literally looks for a record by means of its id.
    @GetMapping("{id}")
    public BloggerDetails getBlogger(@PathVariable String id) {
        Blogger blogger = bloggerService.getBlogger(id);

        BloggerDetails bloggerDetails = new BloggerDetails();
        bloggerDetails.setId(blogger.getId());
        bloggerDetails.setName(blogger.getName());
        bloggerDetails.setEmail(blogger.getEmail());
        bloggerDetails.setDateRegistration(blogger.getCreatedAt());

        return bloggerDetails;
    }

}
