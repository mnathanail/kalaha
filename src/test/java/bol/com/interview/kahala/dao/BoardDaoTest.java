package bol.com.interview.kahala.dao;

import bol.com.interview.kahala.model.BigPit;
import bol.com.interview.kahala.model.Board;
import bol.com.interview.kahala.model.Pit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BoardDaoTest {

    private BoardDao bd;

    @BeforeEach
    private void setUpBeforeClass() throws Exception {
        bd = new BoardDao();
    }

    @Test
    void initializeBoard() {
        Board b = bd.initializeBoard();
        assertNotNull(b);
    }

    @Test
    void getBigPit() {
        Map<Integer, BigPit> pnbp = new HashMap<>();
        pnbp.put(0, bd.initializeBoard().getNorthKahala());
        pnbp.get(0).setScore(0);
        assertEquals(pnbp.get(0).getScore(),bd.getPnbp().get(0).getScore());
    }

    @Test
    void getPitList() {
        Map<Integer, List<Pit>> pbp = new HashMap<>();
        pbp.put(0, bd.initializeBoard().getNorthPits());
        pbp.get(0).stream().peek(a-> a.setValue(6)).toArray();
        assertEquals(pbp.get(0).toArray(),bd.getPnbp().get(0).getScore());
    }

/*
    @Test
    void getPnbp() {
       *//* board
        board.getSouthKahala().setScore(0);*//*
    }

    @Test
    void getPnp() {
        Board board = Board.getInstance();
        board.getSouthKahala().setScore(0);
    }*/
}