package bol.com.interview.kalaha.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int value;
    private String name;
    private String message;

}
