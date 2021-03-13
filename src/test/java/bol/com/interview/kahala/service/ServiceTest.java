package bol.com.interview.kahala.service;

import bol.com.interview.kahala.dao.BoardDao;
import bol.com.interview.kahala.dao.PlayerDao;
import bol.com.interview.kahala.model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private BoardDao bd;
    private PlayerDao pd;

    @BeforeEach
    private void setUpBeforeClass() throws Exception {
        bd = new BoardDao();
        pd = new PlayerDao();
    }

    @Test
    void initializeBoard() {
        Board board = Board.getInstance();
        assertEquals(board, bd.initializeBoard());
    }

    @Test
    void initializeBoardNotNull() {
        assertNotNull(bd.initializeBoard());
    }

    @Test
    void playFirst() {
        assertNotNull(pd.playFirst());
    }

    @Test
    void sowStones() {
        Board board = Board.getInstance();
        assertSame(board, bd.initializeBoard());
    }

    @Test
    void playNext() {
        assertNotNull(pd.getNextPlayer());
    }

    @Test
    void winner() {
        Board board = Board.getInstance();
        assertFalse(board.isWinner());
    }
}