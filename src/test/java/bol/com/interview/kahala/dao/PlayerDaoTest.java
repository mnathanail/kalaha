package bol.com.interview.kahala.dao;

import bol.com.interview.kahala.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDaoTest {

    private PlayerDao pd;

    @BeforeEach
    private void setUpBeforeClass() throws Exception {
        pd = new PlayerDao();
    }
    @Test
    void playFirst() {
        Player resp = pd.playFirst();
        assertTrue(resp instanceof Player);
        assertTrue(resp.getId()==0 || resp.getId() == 1);
    }

    @Test
    void getPlayNext() {
        pd.setNextPlayer(new Player(1));
        Player resp = pd.getNextPlayer();
        assertNotNull(resp);
        assertTrue(resp.getId()==1);
    }

    @Test
    void setPlayNext() {
    }

    @Test
    void getPlayers() {
        Player[] resp = pd.getPlayers();
        assertTrue(resp[0] instanceof Player);
        assertTrue(resp[1] instanceof Player);
        assertTrue(resp[0].getId()==0 && resp[1].getId() == 1);
        assertTrue(resp.length==2);
    }

    @Test
    void getWinner() {
        Board board = Board.getInstance();
        List<Pit> pits = new ArrayList<>();
        List<Pit> reverse = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            pits.add(new Pit(i, 0));
            reverse.add(new Pit(i, 0));
        }
        board.setNorthPits(pits);
        board.setSouthPits(reverse);
        BigPit bigPit1 = new BigPit();
        bigPit1.setScore(50);
        BigPit bigPit2 = new BigPit();
        bigPit2.setScore(22);
        board.setNorthKahala(bigPit1);
        board.setSouthKahala(bigPit2);
        Winner resp = pd.getWinner();
        assertEquals(resp.getMessage(), "Player 1 wins");
        assertEquals(resp.getWinner(), new Player(0));
    }
}