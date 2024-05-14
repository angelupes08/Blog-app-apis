package com.lti.service;

import com.lti.payloads.CommentDto;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto,Integer postId);

    public void deleteComment();
}
