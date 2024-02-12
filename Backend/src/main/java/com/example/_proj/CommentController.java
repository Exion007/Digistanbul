package com.example._proj;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/get-all-comment")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/get-comment/{id}")
    public Optional<Comment> getCommentById(@PathVariable String id) {
        return commentService.getCommentsById(id);
    }

    @DeleteMapping("delete-comment-with-id/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable String id) {
        Optional<Comment> deletedComment = commentService.deleteCommentById(id);

        if (deletedComment.isPresent()) {
            return ResponseEntity.ok("Comment deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment with ID " + id + " not found.");
        }
    }

    @PostMapping("write-comment/")
    public String writeComment(@RequestParam String content, @RequestParam String user, @RequestParam String placeName) {
        return commentService.writeComment(content, user, placeName);

    }
}
