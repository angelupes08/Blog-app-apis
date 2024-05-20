package com.lti.controller;

import com.lti.payloads.CommentDto;
import com.lti.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Operation(summary = "Create comments in a post")
    @PostMapping("/user/post/{postId}")
    public CommentDto createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){

        return commentService.createComment(commentDto,postId);
    }

    @Operation(summary = "Delete comments")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer id){

        commentService.deleteComment(id);

        return new ResponseEntity<String>("Comment deleted", HttpStatus.OK);
    }

    @Operation(summary = "See all comments in a post")
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsinAPost(@PathVariable Integer postId){

        return new ResponseEntity<List<CommentDto>>(commentService.getCommentsinAPost(postId),HttpStatus.OK);
    }

    @Operation(summary = "See all comments of a user")
    @GetMapping("")
    public ResponseEntity<List<CommentDto>> getCommentsofUser(){

        return new ResponseEntity<List<CommentDto>>(commentService.getCommentsofUser(),HttpStatus.OK);
    }
}
