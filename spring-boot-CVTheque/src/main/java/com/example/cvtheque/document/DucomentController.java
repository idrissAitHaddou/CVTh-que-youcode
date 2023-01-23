package com.example.cvtheque.document;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/api/document")
@MultipartConfig
public class DucomentController {
    @Autowired
    private DocumentService documentService;

    @PostMapping(value = "/add")
    public ResponseEntity<DocumentDto> addDocument(@RequestPart(value = "document") DocumentDto documentDto, @RequestPart("file") MultipartFile file, @RequestPart("idUser") int idUser) {
        return documentService.addDocument(documentDto, file, idUser);
    }

    @GetMapping("/learner")
    public List<DocumentDto> getLearnerDocuments(@RequestParam("idUser") int idUser) {
        return documentService.getUserDocuments(idUser);
    }

    @GetMapping("/delete")
    public ResponseEntity<Boolean> deleteDocuments(@RequestParam("id") int id) {
        return ResponseEntity.ok(documentService.deleteDocument(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<DocumentDto>> getDocuments() {
        return ResponseEntity.ok(documentService.getDocuments());
    }

    @GetMapping("/one")
    public ResponseEntity<DocumentDto> getOneDocument(@RequestParam("id") int id) {
        return ResponseEntity.ok(documentService.getOneDocument(id));
    }

    @PostMapping("/update")
    public ResponseEntity<DocumentDto> updateDocument(@RequestPart(value = "document") DocumentDto documentDto, @RequestPart(value = "file", required = false) MultipartFile file, @RequestPart("id") int id) {
        return documentService.updateDocument(documentDto, file, id);
    }

    @PostMapping("/comment/create")
    public ResponseEntity<CommentDocDto> createComment(@RequestPart(value = "comment") String comment, @RequestPart("idDoc") int idDoc, @RequestPart("idUser") int idUser) {
           comment = comment.substring(1, comment.length() - 1);
            return documentService.saveComment(comment, idDoc, idUser);
    }

    @GetMapping("/comment/get")
    public ResponseEntity<List<CommentDocDto>> getDocumentComments(@RequestParam("id") int id) {
        return ResponseEntity.ok(documentService.findDocumentCommentsById(id));
    }

}
