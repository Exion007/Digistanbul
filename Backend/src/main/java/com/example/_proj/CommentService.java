package com.example._proj;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class CommentService {
    private final CommentRepository CommentRepository;
    private final PlaceService PlaceService;
    private final UserService UserService;
    public List<Comment> getAllComments() { return CommentRepository.findAll(); }

    public Optional<Comment> getCommentsByUsername(String username) {
        return CommentRepository.findCommentByUser(username);
    }

    public Optional<Comment> getCommentsById(String id) {
        return CommentRepository.findCommentById(id);
    }

    public Optional<Comment> deleteCommentById(String id) {
        Optional<Comment> deletedComment = CommentRepository.deleteCommentById(id);
        deletedComment.ifPresent(comment -> PlaceService.removeCommentFromPlace(comment.getPlaceName(), comment));
        return deletedComment;
    }

    public String writeComment(String content, String user, String placeName) {
        if (UserService.doesUsernameExist(user)) {
            if (PlaceService.getPlaceByPlacename(placeName).isPresent()) {
                Comment comment = new Comment(content, user, placeName);
                CommentRepository.insert(comment);
                PlaceService.addCommentToPlace(placeName, comment);
                return comment.getId();
            } else {
                return "Place with name " + placeName + " does not exist.";
            }
        } else {
            return "User with username " + user + " does not exist.";
        }
    }
}