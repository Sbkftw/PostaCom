package com.m3.postacom.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.m3.postacom.generated.api.PostApi;
import com.m3.postacom.persistence.PostRepository;

@RestController
public class PostController implements PostApi {

    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Page<List<PoliceOfficer>> getAllPosts() {
        return ResponseEntity.ok(postRepository.findAll());
    }
}
