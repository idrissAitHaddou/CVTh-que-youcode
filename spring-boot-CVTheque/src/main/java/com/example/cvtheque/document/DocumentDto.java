package com.example.cvtheque.document;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class DocumentDto {
    private Long id;
    private String name;
    private String description;
    private String src;
    private String type;
    private String category;
    private String createdAt;
    private String data_file;

    public DocumentDto documentEntityToDto(DocumentEntity document){
        return DocumentDto.builder()
                .id(document.getId())
                .name(document.getName())
                .description(document.getDescription())
                .src(document.getSrc())
                .type(document.getType())
                .category(document.getCategory())
                .createdAt(document.getCreatedAt())
                .data_file(document.getData_file())
                .build();
    }

    public DocumentEntity documentDtoToEntity(DocumentDto document){
        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setId(document.getId());
        documentEntity.setName(document.getName());
        documentEntity.setDescription(document.getDescription());
        documentEntity.setSrc(document.getSrc());
        documentEntity.setType(document.getType());
        documentEntity.setCategory(document.getCategory());
        documentEntity.setCreatedAt((document.getCreatedAt()));
        documentEntity.setData_file(document.getData_file());
        return documentEntity;
    }

    public List<DocumentDto> documentDtoListst(List<DocumentEntity> documents) {
        List<DocumentDto> documentDtoList = new ArrayList<>();
        documents.forEach(document -> {
            DocumentDto documentDto = DocumentDto.builder()
                    .id(document.getId())
                    .name(document.getName())
                    .description(document.getDescription())
                    .src(document.getSrc())
                    .type(document.getType())
                    .category(document.getCategory())
                    .createdAt(document.getCreatedAt())
                    .data_file(document.getData_file())
                    .build();
            documentDtoList.add(documentDto);
        });
        return documentDtoList;
    }

}
