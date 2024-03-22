package io.novelis.novelisblogapp.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data @AllArgsConstructor @NoArgsConstructor
public class ArticleDto{
    private Long id;
    private String title;
    private String content;
    private String author;
    private Date dateCreation;
    private Date dateLastUpdate;
    private Boolean isCreatedByCurrentUser;
    private List<Long> commentIds;

}
