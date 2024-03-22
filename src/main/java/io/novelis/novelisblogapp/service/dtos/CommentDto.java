package io.novelis.novelisblogapp.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Data @AllArgsConstructor @NoArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private Date dateCreation;
    private Date dateLastUpdate;
    private Long articleId;
    private Long userId;
}
