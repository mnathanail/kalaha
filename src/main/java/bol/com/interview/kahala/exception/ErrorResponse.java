package bol.com.interview.kahala.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int value;
    private String name;
    private String message;

}
