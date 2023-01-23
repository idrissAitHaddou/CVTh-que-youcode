package com.example.cvtheque.document;

import com.example.cvtheque.cme.CmeEntity;
import com.example.cvtheque.learner.LearnerEntity;
import com.example.cvtheque.users.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentDocDto {
    private Long id;
    private int idDocument;
    private String comment;
    private String created_at;
    private List<CommentLikesEntity> commentLikes = new ArrayList<>();
    private UserEntity user = new UserEntity();

    public CommentDocDto commentEntityToDto(CommentDocsEntity comment){
        return CommentDocDto.builder()
                .id(comment.getId())
                .idDocument(comment.getIdDocument())
                .comment(comment.getComment())
                .created_at(comment.getCreated_at())
                .commentLikes(comment.getCommentLikes())
                .user(comment.getUser())
                .build();
    }

    public List<CommentDocDto> commentDtoList(List<CommentDocsEntity> comments) {
        List<CommentDocDto> commentDocDtoList = new ArrayList<>();
        comments.forEach(comment -> {
            comment.getUser().setPassword("");
            CommentDocDto commentDocDto = CommentDocDto.builder()
                    .id(comment.getId())
                    .idDocument(comment.getIdDocument())
                    .comment(comment.getComment())
                    .created_at(comment.getCreated_at())
                    .commentLikes(comment.getCommentLikes())
                    .user(comment.getUser())
                    .build();
            commentDocDtoList.add(commentDocDto);
        });
        return commentDocDtoList;
    }

}
