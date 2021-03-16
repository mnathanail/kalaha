package bol.com.interview.kalaha.dao;

import bol.com.interview.kalaha.model.*;
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
    void playFirstType() {
        Player resp = pd.playFirst();
        assertTrue(resp instanceof Player);
    }

    @Test
    void getPlayNextNotNull() {
        pd.setNextPlayer(new Player(1));
        Player resp = pd.getNextPlayer();
        assertNotNull(resp);
    }

    @Test
    void getPlayNext() {
        pd.setNextPlayer(new Player(1));
        Player resp = pd.getPlayNext();
        assertNotNull(resp);
    }

    @Test
    void getPlayNextEquals() {
        pd.setNextPlayer(new Player(1));
        Player resp = pd.getNextPlayer();
        assertEquals(resp.getId(), 1);
    }

    @Test
    void setPlayNext() {
        pd.setPlayNext(new Player(0));
        Player resp = pd.getPlayNext();
        assertNotNull(resp);
    }

    @Test
    void getPlayersType() {
        Player[] resp = pd.getPlayers();
        assertTrue(resp[0] instanceof Player);
    }

    @Test
    void getPlayersNotNull() {
        Player[] resp = pd.getPlayers();
        assertNotNull(resp[1]);
    }

    @Test
    void getPlayersId() {
        Player[] resp = pd.getPlayers();
        assertTrue(resp[0].getId()==0 && resp[1].getId() == 1);
    }

    @Test
    void getPlayersLength() {
        Player[] resp = pd.getPlayers();
        assertEquals(resp.length, 2);
    }

    @Test
    void getWinnerPlayer1() {
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
        board.setNorthKalaha(bigPit1);
        board.setSouthKalaha(bigPit2);
        Winner resp = pd.getWinner();
        assertEquals(resp.getMessage(), "Victory, Player 1 wins");
        assertEquals(resp.getWinner(), new Player(0));
    }

    @Test
    void getWinnerPlayer2() {
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
        bigPit1.setScore(22);
        BigPit bigPit2 = new BigPit();
        bigPit2.setScore(50);
        board.setNorthKalaha(bigPit1);
        board.setSouthKalaha(bigPit2);
        Winner resp = pd.getWinner();
        assertEquals(resp.getMessage(), "Victory, Player 2 wins");
        assertEquals(resp.getWinner(), new Player(1));
    }

    @Test
    void getWinnerTie() {
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
        bigPit1.setScore(36);
        BigPit bigPit2 = new BigPit();
        bigPit2.setScore(36);
        board.setNorthKalaha(bigPit1);
        board.setSouthKalaha(bigPit2);
        Winner resp = pd.getWinner();
        assertEquals(resp.getMessage(), "It's a tie!");
        assertEquals(resp.getWinner(), null);
    }

    @Test
    void getNoWinner() {
        Board board = Board.getInstance();
        List<Pit> pits = new ArrayList<>();
        List<Pit> reverse = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            pits.add(new Pit(i, 6));
            reverse.add(new Pit(i, 6));
        }
        board.setNorthPits(pits);
        board.setSouthPits(reverse);
        BigPit bigPit1 = new BigPit();
        bigPit1.setScore(36);
        BigPit bigPit2 = new BigPit();
        bigPit2.setScore(36);
        board.setNorthKalaha(bigPit1);
        board.setSouthKalaha(bigPit2);
        Winner resp = pd.getWinner();
        assertNull(resp);
    }

    @Test
    void getPlayerByIdPlayer1() {
        Player resp = pd.getPlayerById(0);
        assertNotNull(resp);
    }

    @Test
    void getPlayerByIdPlayer2() {
        Player[] resp = pd.getPlayers();
        assertNotNull(resp[1]);
    }
}