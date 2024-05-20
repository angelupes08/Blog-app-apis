package com.lti.service;

import com.lti.payloads.CommentDto;

import java.util.List;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto,Integer postId);

    public void deleteComment(Integer id);

    public List<CommentDto> getCommentsinAPost(Integer postId);

    public List<CommentDto> getCommentsofUser();
}
