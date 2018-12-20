package io.github.foodsearcher.dao;

import io.github.foodsearcher.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentDao extends MongoRepository<Comment, String> {
    public Comment findById(String id);

    List<Comment> findAllByDishId(Long dishId);
}
