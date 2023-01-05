package com.example.cvtheque.promotion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {
    private Long id;
    private String name;
    private String year;
    private String level;
    private String image;
    private String startDate;
    private String endtDate;
    private String descritpion;
}
