package bol.com.interview.kahala.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Winner {
    private String message;
    private Player winner;
}
