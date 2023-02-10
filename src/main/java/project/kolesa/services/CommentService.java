package project.kolesa.services;

import project.kolesa.dto.CommentDto;
import project.kolesa.model.Ad;
import project.kolesa.model.Comments;

import java.util.List;

public interface CommentService {
    Comments addComment(CommentDto commentDto, Long id);
    List <Comments> getComments();
    void deleteComment(Long id);
    List<Comments> getByAd(Ad ad);

}
