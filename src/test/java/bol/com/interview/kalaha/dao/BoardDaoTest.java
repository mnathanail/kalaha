package bol.com.interview.kalaha.dao;

import bol.com.interview.kalaha.model.BigPit;
import bol.com.interview.kalaha.model.Board;
import bol.com.interview.kalaha.model.Pit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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
        Map<Integer, BigPit> bigPits = bd.getBigPit();
        assertEquals(bigPits,bd.getPnbp());
    }

    @Test
    void getPitList() {
        Map<Integer, List<Pit>> pits = bd.getPitList();
        assertEquals(pits,bd.getPnp());
    }

    @Test
    void haveWinner() {
        Board board = bd.initializeBoard();
        boolean hw = bd.haveWinner(board);
        assertFalse(hw);
    }

    @Test
    void haveWinnerTrue() {
        Board board = bd.initializeBoard();
        board.emptyAllPits();
        boolean hw = bd.haveWinner(board);
        assertTrue(hw);
    }
}