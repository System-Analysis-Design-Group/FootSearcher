package io.github.foodsearcher.model;

import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Set;

public class Comment {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    private String type; // "store" 是对商店的评论; "dish" 是对菜式的评价
    @NotNull
    private Long storeId;
    @NotNull
    private Long userId;
    @NotNull
    private String content;

    private Long dishId;

    public Comment() {
    }

    public Comment(String type, Long storeId, Long dishId, Long userId, String content) {
        this.type = type;
        this.storeId = storeId;
        this.dishId = dishId;
        this.userId = userId;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }
}
