package com.naical.commentsection.comment;

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
        commentServiceImpl.saveToComment(commentDTO);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommentDTO> delete(@PathVariable("id") int id){
        commentServiceImpl.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comment")
    public ResponseEntity<CommentDTO> getById(@RequestParam int id){
        Comment comment = commentServiceImpl.getById(id);
        return new ResponseEntity<>(commentMapper.commentToCommentDTO(comment), HttpStatus.OK);
    }
}
