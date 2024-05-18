package com.lti.controller;

import com.lti.payloads.CommentDto;
import com.lti.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
