package com.lti.payloads;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lti.entity.Post;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {

    private String content;

    private Date createdDate;

    private Date updatedDate;



}
