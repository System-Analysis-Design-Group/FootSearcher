package io.github.foodsearcher.controller;

import io.github.foodsearcher.dao.CommentDao;
import io.github.foodsearcher.model.Comment;
import io.github.foodsearcher.model.StatusMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentDao commentDao;

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(path = "/Comments/store/{store_id}", method = RequestMethod.GET)
    public List<Comment> getComments(@PathVariable("store_id") String store_id){
        List<Comment> commentList = commentDao.findAll();
        return commentList;
    }

    @RequestMapping(path = "/Comments/store/{storeId}", method = RequestMethod.POST)
    public StatusMsg createComment(@PathVariable("storeId") long storeId,
                                   @RequestParam("comment") String comment,
                                   @RequestParam("userId") Long userId){

        Comment comObj = new Comment("store", storeId, null, userId, comment);
        commentDao.save(comObj);
        return StatusMsg.returnOkWithObj(comObj);
    }

    @RequestMapping(path = "/Comments/{commentId}", method = RequestMethod.GET)
    public StatusMsg getStoreComment(@PathVariable("commentId") String commentId){
        Comment com = commentDao.findOne(commentId);
        return StatusMsg.returnOkWithObj(com);
    }

    @RequestMapping(path = "/Comments/{commentId}", method = RequestMethod.DELETE)
    public StatusMsg deleteComment(@PathVariable("commentId") String commentId){
        try {
            commentDao.delete(commentId);
            return StatusMsg.returnOk();
        } catch (Exception ex) {
            return StatusMsg.returnError();
        }
    }

    @RequestMapping(path = "/Comments/{commentId}", method = RequestMethod.PUT)
    public StatusMsg updateComment(@PathVariable("commentId") String commentId,
                                   @RequestParam("content") String content){
        try {
            Comment comment = commentDao.findOne(commentId);
            comment.setContent(content);
            commentDao.save((comment));
        } catch (Exception ex) {
            return StatusMsg.returnError();
        }

        return StatusMsg.returnOk();
    }

    @RequestMapping(path = "/Comments/dish/{dishId}", method = RequestMethod.GET)
    public StatusMsg getdishComment(@PathVariable("dishId") Long dishId){
        List<Comment> comList = commentDao.findAllByDishId(dishId);

        return StatusMsg.returnOkWithObj(comList);
    }

    @RequestMapping(path = "/Comments/dish/{dishId}", method = RequestMethod.POST)
    public StatusMsg setDishComment(@PathVariable("dishId") Long dishId, Comment comment){

        comment.setDishId(dishId);
        comment.setType("dish");
        Comment newCom = commentDao.save(comment);

        return StatusMsg.returnOkWithObj(newCom);
    }


}
