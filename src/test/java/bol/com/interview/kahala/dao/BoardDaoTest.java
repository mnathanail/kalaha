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
        Map<Integer, BigPit> bigPits = bd.getBigPit();
        assertEquals(bigPits,bd.getPnbp());
    }

    @Test
    void getPitList() {
        Map<Integer, List<Pit>> pits = bd.getPitList();
        assertEquals(pits,bd.getPnp());
    }

}