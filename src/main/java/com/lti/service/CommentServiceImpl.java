package com.lti.service;

import com.lti.dao.CommentRepo;
import com.lti.dao.PostRepo;
import com.lti.entity.Comment;
import com.lti.entity.Post;
import com.lti.exceptions.ResourceNotFoundException;
import com.lti.payloads.CommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepo cRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto,Integer postId) {

        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("No post found with given id"));

        Comment comment = this.modelMapper.map(commentDto,Comment.class);

        comment.setPost(post);

        post.getComments().add(comment);
        //postRepo.save(post);
        Comment savedComment = cRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment() {

    }
}
