package com.interview.kalaha.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
//@Scope("singleton")
public class Board {

    private static Board instance = null;

    private BigPit southKalaha;
    private BigPit northKalaha;
    private List<Pit> northPits;
    private List<Pit> southPits;
    private boolean winner;


    public void calculateScore(){
        int count1 = northPits.stream().mapToInt(Pit::getValue).sum();
        int count2 = southPits.stream().mapToInt(Pit::getValue).sum();
        int score1 = northKalaha.getScore();
        int score2 = southKalaha.getScore();
        northKalaha.setScore(score1 + count1);
        southKalaha.setScore(score2 + count2);
    }

    public void emptyAllPits() {
        emptyNorthPits();
        emptySouthPits();
    }

    private List<Pit> emptyNorthPits(){
        return northPits.stream().peek(e -> e.setValue(0)).collect(Collectors.toList());
    }

    private List<Pit> emptySouthPits(){
        return southPits.stream().peek(e -> e.setValue(0)).collect(Collectors.toList());
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
