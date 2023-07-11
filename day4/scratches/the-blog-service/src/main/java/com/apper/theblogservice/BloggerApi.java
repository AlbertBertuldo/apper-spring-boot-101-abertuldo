package com.apper.theblogservice;

import com.apper.theblogservice.payload.CreateBloggerRequest;
import com.apper.theblogservice.payload.CreateBloggerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("blogger")
public class BloggerApi {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)// must be 201
    //@Valid used or helps to validate response
    public CreateBloggerResponse createBlogger(@RequestBody @Valid CreateBloggerRequest request){
        System.out.println(request);

        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId("dummy_id");
        response.setDateRegistration(LocalDateTime.now());

        return response;
    }
}
