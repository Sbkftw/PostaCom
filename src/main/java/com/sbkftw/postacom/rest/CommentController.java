package com.sbkftw.postacom.rest;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sbkftw.postacom.model.Comment;
import com.sbkftw.postacom.model.Post;
import com.sbkftw.postacom.model.User;
import com.sbkftw.postacom.persistence.CommentRepository;
import com.sbkftw.postacom.persistence.PostRepository;
import com.sbkftw.postacom.persistence.UserRepository;
import com.sbkftw.postacom.rest.dto.CommentDTO;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/{postId}/comments")
public class CommentController {

    private final CommentRepository commentRepository;
    private final PostRepository    postRepository;
    private final UserRepository    userRepository;
    private final ModelMapper       modelMapper;

    @Autowired
    public CommentController(CommentRepository commentRepository, PostRepository postRepository,
            UserRepository userRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createComment(@RequestBody @Valid CommentDTO comment, @PathVariable Integer postId) {
        comment.setPost(postId);
        commentRepository.save(convertToEntity(comment));
    }

    private Comment convertToEntity(CommentDTO commentDTO) {
        Optional<User> author = userRepository.findByEmail(commentDTO.getAuthor());
        Optional<Post> post = postRepository.findById(commentDTO.getPost());
        if (author.isPresent() && post.isPresent()) {
            Comment createdComment = new Comment();
            createdComment.setMessage(commentDTO.getMessage());
            createdComment.setPublishDate(LocalDateTime.now());
            createdComment.setAuthor(author.get().getId());
            createdComment.setPost(post.get().getId());
            return createdComment;
        } else {
            throw new EntityNotFoundException();
        }

    }
}
