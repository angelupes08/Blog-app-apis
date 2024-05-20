package com.lti.dao;

import com.lti.entity.Comment;
import com.lti.entity.Post;
import com.lti.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

    public Comment findByUserAndId(User user, Integer id);

    public List<Comment> findByPostId(Integer postId);

    public List<Comment> findByUser(User user);
}
