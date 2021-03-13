package bol.com.interview.kahala.utils;

import bol.com.interview.kahala.model.Board;
import bol.com.interview.kahala.model.Pit;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static boolean isPitEmpty(int lastIndex, List<Pit> pits) {
        return pits.get(lastIndex).getValue() == 0;
    }

    public static boolean allPitsEmpty(List<Pit> pits) {
        return pits.stream().allMatch(e -> e.getValue() == 0);
    }

    public static List<Pit> emptyAllPits(List<Pit> pits) {
        return pits.stream().peek(e -> e.setValue(0)
        ).collect(Collectors.toList());
    }

    public static int countAllPits(List<Pit> pits) {
        return pits.stream().mapToInt(Pit::getValue).sum();
    }

    public static void updateBoardWin(Board board) {

        int count1 = Utils.countAllPits(board.getNorthPits());
        int count2 = Utils.countAllPits(board.getSouthPits());
        int score1 = board.getNorthKahala().getScore();
        int score2 = board.getSouthKahala().getScore();
        board.getNorthKahala().setScore(score1 + count1);
        board.getSouthKahala().setScore(score2 + count2);

        Utils.emptyAllPits(board.getNorthPits());
        Utils.emptyAllPits(board.getSouthPits());
    }
}
