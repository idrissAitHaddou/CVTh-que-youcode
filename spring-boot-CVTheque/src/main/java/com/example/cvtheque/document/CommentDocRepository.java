package com.example.cvtheque.document;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDocRepository extends JpaRepository<CommentDocsEntity, Long> {
//    @Query("SELECT c FROM CommentDocsEntity c where c.id_document = :id")
//    List<CommentDocsEntity> findIdDocument(int id);
}
