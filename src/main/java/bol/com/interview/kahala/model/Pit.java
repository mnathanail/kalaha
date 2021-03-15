package bol.com.interview.kahala.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
public class Pit {
    private int pos;
    private int value;
}
