package com.example.cvtheque.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/document")
public class DucomentController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private CommentDocRepository commentDocRepository;

    @PostMapping(value = "/add")
    public ResponseEntity<DocumentDto> addDocument(@RequestPart(value = "document") DocumentDto documentDto, @RequestPart("idUser") int idUser) {
        return documentService.addDocument(documentDto, idUser);
    }

    @GetMapping("/learner")
    public List<DocumentDto> getLearnerDocuments(@RequestParam("idUser") int idUser) {
        return documentService.getLearnerDocuments(idUser);
    }

    @GetMapping("/delete")
    public ResponseEntity<Boolean> deleteDocuments(@RequestParam("id") int id) {
        return ResponseEntity.ok(documentService.deleteDocument(id));
    }

    @GetMapping("/one")
    public ResponseEntity<DocumentDto> getOneDocument(@RequestParam("id") int id) {
        return ResponseEntity.ok(documentService.getOneDocument(id));
    }

    @PostMapping("/update")
    public ResponseEntity<DocumentDto> updateDocument(@RequestPart(value = "document") DocumentDto documentDto, @RequestPart("id") int id) {
        return documentService.updateDocument(documentDto, id);
    }

    @PostMapping("/comment/create")
    public ResponseEntity<?> createComment(@RequestBody String comment) {
        CommentDocsEntity com = new CommentDocsEntity();
        com.setComment(comment);
        return ResponseEntity.ok(commentDocRepository.save(com));
    }

    @PostMapping("/comment/get")
    public ResponseEntity<?> getDocumentComments(@RequestParam("id") int id) {
        return ResponseEntity.ok(documentService.findDocumentCommentsById(id));
    }

}
