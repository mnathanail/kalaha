package bol.com.interview.kahala.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@Scope("singleton")
public class Board {

    private static Board instance = null;

    private BigPit southKahala;
    private BigPit northKahala;
    private List<Pit> northPits;
    private List<Pit> southPits;
    private boolean winner;

    public void calculateScore(){
        int count1 = northPits.stream().mapToInt(Pit::getValue).sum();
        int count2 = southPits.stream().mapToInt(Pit::getValue).sum();
        int score1 = northKahala.getScore();
        int score2 = southKahala.getScore();
        northKahala.setScore(score1 + count1);
        southKahala.setScore(score2 + count2);
    }

    public void emptyAllPits() {
        northPits.stream().peek(e -> e.setValue(0));
        southPits.stream().peek(e -> e.setValue(0));
    }

    public boolean emptyPitExists() {
        boolean np = northPits.stream().allMatch(e -> e.getValue() == 0);
        boolean sp = southPits.stream().allMatch(e -> e.getValue() == 0);
        return np || sp;
    }

    public static Board getInstance(){
        if(instance == null){
            instance = new Board();
        }
        return instance;
    }
}
