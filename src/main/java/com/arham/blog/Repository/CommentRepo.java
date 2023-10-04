package com.arham.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arham.blog.Entites.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer >{

}
