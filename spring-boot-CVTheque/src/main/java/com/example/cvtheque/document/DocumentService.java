package com.example.cvtheque.document;

import com.example.cvtheque.exception.ApiException;
import com.example.cvtheque.exception.NotFoundException;
import com.example.cvtheque.learner.LearnerDto;
import com.example.cvtheque.learner.LearnerEntity;
import com.example.cvtheque.learner.LearnerService;
import com.example.cvtheque.users.UserEntity;
import com.example.cvtheque.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QSort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
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
    @Autowired
    private UserService userService;
    @Autowired
    private CommentDocDto commentDocDto;

    public ResponseEntity<CommentDocDto> saveComment(String comment,int idDoc, int idUser){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        CommentDocsEntity com = new CommentDocsEntity();
        com.setCreated_at(dateFormat.format(now));
        com.setComment(comment);
        com.setIdDocument(idDoc);
        UserEntity user = userService.findUserById(idUser);
        com.setUser(user);
        CommentDocsEntity commentDocs =commentDocRepository.save(com);
        return ResponseEntity.ok(commentDocDto.commentEntityToDto(commentDocs));
    }

    public List<CommentDocDto> findDocumentCommentsById(int id) {
        List<CommentDocsEntity> comments = commentDocRepository.findByIdDocument(id);
        List<CommentDocDto> commentDocDtoList = commentDocDto.commentDtoList(comments);
        return commentDocDtoList;
    }

    public List<DocumentDto> getDocuments() {
        List<DocumentEntity> documentList = documentRepository.findAll();
        List<DocumentDto> documentDtoList = documentDto.documentDtoListst(documentList);
        return documentDtoList;
    }

    public List<DocumentDto> getUserDocuments(int id) {
        Long idUser = Long.valueOf(id);
        List<DocumentEntity> documentList = documentRepository.findByUserId(idUser);
        List<DocumentDto> documentDtoList = documentDto.documentDtoListst(documentList);
        return documentDtoList;
    }

    public Boolean uploadFile(MultipartFile uploadFile) {
        try{
            String path = "C:\\xampp\\htdocs\\cvThequeUploadDocs\\";
            uploadFile.transferTo( new File(path + uploadFile.getOriginalFilename()));
            return true;
        }catch (Exception ex) {
            return false;
        }
    }

    public ResponseEntity<DocumentDto> addDocument(DocumentDto documentDto, MultipartFile file, int idUser) {
        try {
            Boolean isUpload = this.uploadFile(file);
            if(!isUpload) {
                return null;
            }
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            documentDto.setCreatedAt(dateFormat.format(now));
            DocumentEntity document = documentDto.documentDtoToEntity(documentDto);
            document.setSrc(file.getOriginalFilename());
            document.setType(file.getContentType());
            UserEntity user = userService.findUserById(idUser);
            document.setUser(user);
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

    public ResponseEntity<DocumentDto> updateDocument(DocumentDto documentDto, MultipartFile file, int id) {
        try {
            Long idDoc = Long.valueOf(id);
            Boolean isFileUplaod = true;
            DocumentEntity document = documentDto.documentDtoToEntity(documentDto);
            Optional<DocumentEntity> findDocument = documentRepository.findById(idDoc);
            document.setUser(findDocument.get().getUser());
            if(file != null) isFileUplaod = this.uploadFile(file);
            if(!isFileUplaod) return null;
            else {
                document.setSrc(file.getOriginalFilename());
                document.setType(file.getContentType());
            }
            DocumentEntity documentUpdated = documentRepository.save(document);
            DocumentDto documentDtoUpdated = documentDto.documentEntityToDto(documentUpdated);
            return ResponseEntity.ok(documentDtoUpdated);
        }catch (Exception e) {
            throw new ApiException("Document not updated", HttpStatus.CREATED);
        }
    }

}
