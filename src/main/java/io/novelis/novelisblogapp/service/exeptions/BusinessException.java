package io.novelis.novelisblogapp.service.exeptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BusinessException extends RuntimeException{

    private int status;
    private String title;
    private  String message;
}
