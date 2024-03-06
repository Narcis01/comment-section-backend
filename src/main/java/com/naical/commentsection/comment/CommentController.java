package com.naical.commentsection.comment;

import com.naical.commentsection.comment.Comment;
import com.naical.commentsection.comment.CommentDTO;
import com.naical.commentsection.comment.CommentMapper;
import com.naical.commentsection.comment.CommentServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
@Component
@Slf4j
public class CommentController {

    private final CommentServiceImp commentServiceImpl;
    private final CommentMapper commentMapper;

    @GetMapping("/comments")
    public List<CommentDTO> getAll(){
        return commentServiceImpl.getAll().stream().map(commentMapper::commentToCommentDTO).toList();
    }

    @PostMapping("/save")
    public ResponseEntity<CommentDTO> save(@RequestBody CommentDTO commentDTO){
        log.info(commentDTO.toString());
        commentServiceImpl.saveToComment(commentDTO);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommentDTO> delete(@PathVariable("id") int id){
        commentServiceImpl.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
