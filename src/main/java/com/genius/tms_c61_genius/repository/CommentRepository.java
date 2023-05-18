package com.genius.tms_c61_genius.repository;

import com.genius.tms_c61_genius.model.domain.Comment;
import com.genius.tms_c61_genius.model.domain.Song;
import com.genius.tms_c61_genius.model.request.CommentReqDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Optional<Comment> getCommentById(Integer id);
    List<Comment> getCommentsByUserId(Integer id);

    List<Comment> getCommentsBySongId(Integer id);

    void deleteCommentsBySongId(Integer id);

    Boolean existsCommentById(Integer id);
    Boolean existsCommentsBySongId(Integer id);

    Boolean existsCommentsByUserId(Integer id);

}
