package bol.com.interview.kahala.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Winner {
    private String message;
    private Player winner;
}
