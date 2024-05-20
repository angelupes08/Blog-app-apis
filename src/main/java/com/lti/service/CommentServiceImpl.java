package com.lti.service;

import com.lti.dao.CommentRepo;
import com.lti.dao.PostRepo;
import com.lti.dao.UserRepo;
import com.lti.entity.Comment;
import com.lti.entity.Post;
import com.lti.entity.User;
import com.lti.exceptions.ResourceNotFoundException;
import com.lti.payloads.CommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepo cRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Override
    public CommentDto createComment(CommentDto commentDto,Integer postId) {

        User user = userRepo.findById(userService.getLoggedInUser().getId()).orElseThrow(()->new ResourceNotFoundException("There exists no such user with id"));

        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("No post found with given id"));

        Comment comment = this.modelMapper.map(commentDto,Comment.class);

        comment.setPost(post);
        comment.setUser(user);

        comment.setCreatedDate(new Date());
        comment.setUpdatedDate(new Date());

        post.getComments().add(comment);

        Comment savedComment = cRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer id) {

        Comment comment = cRepo.findByUserAndId(userService.getLoggedInUser(),id);

        cRepo.delete(comment);
    }

    @Override
    public List<CommentDto> getCommentsinAPost(Integer postId) {

        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("There exists bo post with postId"+postId));

        List<Comment> comments = cRepo.findByPostId(postId);

        return comments.stream().map((comment) -> modelMapper.map(comment, CommentDto.class)).toList();

    }

    @Override
    public List<CommentDto> getCommentsofUser() {

        List<Comment> comments = cRepo.findByUser(userService.getLoggedInUser());

        return comments.stream().map((comment) -> modelMapper.map(comment, CommentDto.class)).toList();
    }
}
