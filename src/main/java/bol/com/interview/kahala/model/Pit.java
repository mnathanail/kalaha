package bol.com.interview.kahala.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
public class Pit {
    private int pos;
    @Min(0)
    @Max(5)
    private int value;
}
