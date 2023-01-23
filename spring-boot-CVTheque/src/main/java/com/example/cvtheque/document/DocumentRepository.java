package com.example.cvtheque.document;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    @Query("SELECT d FROM DocumentEntity d where d.user.id = :idLerner")
    List<DocumentEntity> findByUserId(Long idLerner);
}
