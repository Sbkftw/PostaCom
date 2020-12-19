package com.sbkftw.postacom.rest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sbkftw.postacom.model.Post;
import com.sbkftw.postacom.model.User;
import com.sbkftw.postacom.persistence.PostRepository;
import com.sbkftw.postacom.persistence.UserRepository;
import com.sbkftw.postacom.rest.dto.PostDTO;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostController(PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @GetMapping(value = "/api/{postId}")
    public Post getPost(@PathVariable("postId") @Digits(integer = 10, fraction = 0) @NotNull Integer id) {
        return postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createPost(@RequestBody @Valid PostDTO post) {
        postRepository.save(convertToEntity(post));
    }

    @PutMapping(value = "/{postId}", consumes = APPLICATION_JSON_VALUE)
    public void likePost(@PathVariable("postId") @Digits(integer = 10, fraction = 0) @NotNull Integer id) {
        Post likedPost = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        likedPost.like();
        postRepository.save(likedPost);
    }

    private Post convertToEntity(PostDTO postDTO) {
        Optional<User> author = userRepository.findByEmail(postDTO.getAuthor());
        if (author.isPresent()) {
            Post createdPost = new Post();
            createdPost.setText(postDTO.getText());
            createdPost.setPublishDate(LocalDateTime.now());
            createdPost.setLikes(0);
            createdPost.setAuthor(author.get().getId());
            return createdPost;
        } else {
            throw new EntityNotFoundException();
        }

    }
}
