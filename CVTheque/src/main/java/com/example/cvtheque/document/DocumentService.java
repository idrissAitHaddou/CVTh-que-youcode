package com.example.cvtheque.document;

import com.example.cvtheque.exception.ApiException;
import com.example.cvtheque.exception.NotFoundException;
import com.example.cvtheque.learner.LearnerDto;
import com.example.cvtheque.learner.LearnerEntity;
import com.example.cvtheque.learner.LearnerService;
import com.example.cvtheque.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private LearnerService learnerService;
    @Autowired
    private DocumentDto documentDto;
    @Autowired
    private LearnerDto learnerDto;
    @Autowired
    private CommentDocRepository commentDocRepository;

    public List<CommentDocsEntity> findDocumentCommentsById(int id) {
//        return commentDocRepository.findIdDocument(id);
        System.out.println(id);
        return null;
    }

    public List<DocumentDto> getLearnerDocuments(int id) {
        Long idLerner = Long.valueOf(id);
        List<DocumentEntity> documentList = documentRepository.findByLearnerId(idLerner);
        List<DocumentDto> documentDtoList = documentDto.documentDtoListst(documentList);
        return documentDtoList;
    }

    public ResponseEntity<DocumentDto> addDocument(DocumentDto documentDto, int idUser) {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            documentDto.setCreatedAt(dateFormat.format(now));
            DocumentEntity document = documentDto.documentDtoToEntity(documentDto);
            LearnerEntity user = learnerDto.LearnerDtoToEntity(learnerService.findLearnerById(idUser));
            document.setLearner(user);
            DocumentEntity documentAdded = documentRepository.save(document);
            DocumentDto documentDtoAdded = documentDto.documentEntityToDto(documentAdded);
            return ResponseEntity.ok(documentDtoAdded);
        }catch (Exception e) {
            throw new ApiException("Document not uploaded", HttpStatus.CREATED);
        }
    }

    public boolean deleteDocument(int id) {
        try {
            Long idDoc = Long.valueOf(id);
            documentRepository.deleteById(idDoc);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public DocumentDto getOneDocument(int id) {
        try {
            Long idDoc = Long.valueOf(id);
            Optional<DocumentEntity> documentList = documentRepository.findById(idDoc);
            DocumentDto document = documentDto.documentEntityToDto(documentList.get());
            return document;
        }catch (Exception e) {
            throw new NotFoundException("that document not found");
        }
    }

    public ResponseEntity<DocumentDto> updateDocument(DocumentDto documentDto, int id) {
        try {
            Long idDoc = Long.valueOf(id);
            DocumentEntity document = documentDto.documentDtoToEntity(documentDto);
            Optional<DocumentEntity> findDocument = documentRepository.findById(idDoc);
            document.setLearner(findDocument.get().getLearner());
            if(document.getData_file() == null) document.setData_file(findDocument.get().getData_file());
            DocumentEntity documentUpdated = documentRepository.save(document);
            DocumentDto documentDtoUpdated = documentDto.documentEntityToDto(documentUpdated);
            return ResponseEntity.ok(documentDtoUpdated);
        }catch (Exception e) {
            throw new ApiException("Document not updated", HttpStatus.CREATED);
        }
    }
}
