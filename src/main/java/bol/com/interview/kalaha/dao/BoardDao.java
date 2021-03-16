package bol.com.interview.kalaha.dao;

import bol.com.interview.kalaha.model.BigPit;
import bol.com.interview.kalaha.model.Board;
import bol.com.interview.kalaha.model.Pit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDao implements IBoardDao{

    private Map<Integer, BigPit> pnbp = new HashMap<>();
    private Map<Integer, List<Pit>> pnp = new HashMap<>();
    private static int NUMBER_OF_STONES = 6;
    private static int INITIAL_SCORE = 0;

    @Override
    public Board initializeBoard() {
        Board board = Board.getInstance();
        BigPit bigPit1 = new BigPit();
        BigPit bigPit2 = new BigPit();
        bigPit1.setScore(INITIAL_SCORE);
        bigPit2.setScore(INITIAL_SCORE);

        board.setNorthKalaha(bigPit1);
        board.setSouthKalaha(bigPit2);

        List<Pit> northPits = new ArrayList<>();
        List<Pit> southPits = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_STONES; i++) {
            northPits.add(new Pit(i, NUMBER_OF_STONES));
            southPits.add(new Pit(i, NUMBER_OF_STONES));
        }

        board.setNorthPits(northPits);
        board.setSouthPits(southPits);

        board.setWinner(false);
        mapPlayersWithPits(board);
        mapPlayersWithBigPits(board);
        return board;
    }

    @Override
    public Map<Integer, BigPit> getBigPit() {
        return pnbp;
    }

    @Override
    public Map<Integer, List<Pit>> getPitList() {
        return pnp;
    }

    private void mapPlayersWithPits(Board board) {
        // pnb stands for players and pits!
        pnp.put(0, board.getNorthPits());
        pnp.put(1, board.getSouthPits());
    }

    private void mapPlayersWithBigPits(Board board) {
        // pnb stands for players and big pits!
        pnbp.put(0, board.getNorthKalaha());
        pnbp.put(1, board.getSouthKalaha());
    }

    public Map<Integer, BigPit> getPnbp() {
        return pnbp;
    }

    public Map<Integer, List<Pit>> getPnp() {
        return pnp;
    }

    public boolean haveWinner(Board board){
        return board.emptyPitExists();
    }
}
