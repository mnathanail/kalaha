package com.interview.kalaha.service;

import com.interview.kalaha.dao.BoardDao;
import com.interview.kalaha.dao.PlayerDao;
import com.interview.kalaha.model.BigPit;
import com.interview.kalaha.model.Board;
import com.interview.kalaha.model.Pit;
import com.interview.kalaha.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    private MockMvc mvc;

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardDao boardDao;

    @Mock
    private PlayerDao playerDao;

    @Test
    void initializeBoard() {
        when(boardDao.initializeBoard()).thenReturn(new Board());
        Board response = boardService.initializeBoard();
        assertNull(response.getNorthKalaha());
    }

    @Test
    void sowStones() {
        Board board = Board.getInstance();

        List<Pit> northPits = new ArrayList<>();
        List<Pit> southPits = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            northPits.add(new Pit(i, 6));
            southPits.add(new Pit(i, 6));
        }
        Map<Integer, BigPit> pnbp = new HashMap<>();
        pnbp.put(0, new BigPit());
        pnbp.put(1, new BigPit());
        Map<Integer, List<Pit>> pnp = new HashMap<>();
        pnp.put(0, northPits);
        pnp.put(1, southPits);
        board.setSouthPits(southPits);
        board.setNorthPits(northPits);
        when(boardDao.getPitList()).thenReturn(pnp);
        when(playerDao.getPlayerById(0)).thenReturn(new Player(0));
        when(boardDao.getBigPit()).thenReturn(pnbp);
        when(boardDao.haveWinner(board)).thenReturn(false);
        Board response = boardService.sowStones(1, 0);
        assertEquals(response, board);
    }


    @Test
    void sowStonesInNoInitializedBoard() {
        assertThrows(NullPointerException.class,
        () -> {
            when(boardDao.getPitList()).thenReturn(null);
            when(playerDao.getPlayerById(0)).thenReturn(new Player(0));
            boardService.sowStones(1, 0);
        });
    }

    @Test
    void sowStonesWithWinner() {
        Board board = Board.getInstance();

        List<Pit> northPits = new ArrayList<>();
        List<Pit> southPits = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            northPits.add(new Pit(i, 6));
            southPits.add(new Pit(i, 6));
        }
        Map<Integer, BigPit> pnbp = new HashMap<>();
        pnbp.put(0, new BigPit());
        pnbp.put(1, new BigPit());
        Map<Integer, List<Pit>> pnp = new HashMap<>();
        pnp.put(0, northPits);
        pnp.put(1, southPits);
        board.setSouthPits(southPits);
        board.setNorthPits(northPits);
        when(boardDao.getPitList()).thenReturn(pnp);
        when(playerDao.getPlayerById(0)).thenReturn(new Player(0));
        when(boardDao.getBigPit()).thenReturn(pnbp);
        when(boardDao.haveWinner(board)).thenReturn(true);
        BigPit northKahala = new BigPit();
        BigPit southKahala = new BigPit();
        northKahala.setScore(30);
        southKahala.setScore(42);
        board.setNorthKalaha(northKahala);
        board.setSouthKalaha(southKahala);

        Board response = boardService.sowStones(1, 0);
        assertEquals(response, board);
    }

    @Test
    void sowStonesLastStoneToKahala() {
        Board board = Board.getInstance();

        List<Pit> northPits = new ArrayList<>();
        List<Pit> southPits = new ArrayList<>();
        northPits.add(new Pit(0, 1));
        northPits.add(new Pit(1, 2));
        northPits.add(new Pit(2, 9));
        northPits.add(new Pit(3, 9));
        northPits.add(new Pit(4, 10));
        northPits.add(new Pit(5, 2));
        southPits.add(new Pit(0, 1));
        southPits.add(new Pit(1, 0));
        southPits.add(new Pit(2, 8));
        southPits.add(new Pit(3, 8));
        southPits.add(new Pit(4, 0));
        southPits.add(new Pit(5, 0));
        Map<Integer, BigPit> pnbp = new HashMap<>();
        pnbp.put(0, new BigPit());
        pnbp.put(1, new BigPit());
        Map<Integer, List<Pit>> pnp = new HashMap<>();
        pnp.put(0, northPits);
        pnp.put(1, southPits);
        board.setSouthPits(southPits);
        board.setNorthPits(northPits);
        when(boardDao.getPitList()).thenReturn(pnp);
        when(playerDao.getPlayerById(1)).thenReturn(new Player(1));
        when(boardDao.getBigPit()).thenReturn(pnbp);
        when(boardDao.haveWinner(board)).thenReturn(true);
        BigPit northKahala = new BigPit();
        BigPit southKahala = new BigPit();
        northKahala.setScore(1);
        southKahala.setScore(21);
        board.setNorthKalaha(northKahala);
        board.setSouthKalaha(southKahala);

        Board response = boardService.sowStones(0, 1);
        assertEquals(response, board);
    }

    @Test
    void sowStonesNotLastStone() {
        Board board = Board.getInstance();

        List<Pit> northPits = new ArrayList<>();
        List<Pit> southPits = new ArrayList<>();
        northPits.add(new Pit(0, 0));
        northPits.add(new Pit(1, 0));
        northPits.add(new Pit(2, 0));
        northPits.add(new Pit(3, 0));
        northPits.add(new Pit(4, 8));
        northPits.add(new Pit(5, 2));
        southPits.add(new Pit(0, 1));
        southPits.add(new Pit(1, 0));
        southPits.add(new Pit(2, 15));
        southPits.add(new Pit(3, 13));
        southPits.add(new Pit(4, 12));
        southPits.add(new Pit(5, 11));
        Map<Integer, BigPit> pnbp = new HashMap<>();
        pnbp.put(0, new BigPit());
        pnbp.put(1, new BigPit());
        Map<Integer, List<Pit>> pnp = new HashMap<>();
        pnp.put(0, northPits);
        pnp.put(1, southPits);
        board.setSouthPits(southPits);
        board.setNorthPits(northPits);
        when(boardDao.getPitList()).thenReturn(pnp);
        when(playerDao.getPlayerById(1)).thenReturn(new Player(1));
        when(boardDao.getBigPit()).thenReturn(pnbp);
        when(boardDao.haveWinner(board)).thenReturn(true);
        BigPit northKahala = new BigPit();
        BigPit southKahala = new BigPit();
        northKahala.setScore(1);
        southKahala.setScore(21);
        board.setNorthKalaha(northKahala);
        board.setSouthKalaha(southKahala);

        Board response = boardService.sowStones(0, 1);
        assertEquals(response, board);
    }

    @Test
    void sowStonesCaptureOpponents() {
        Board board = Board.getInstance();

        List<Pit> northPits = new ArrayList<>();
        List<Pit> southPits = new ArrayList<>();
        northPits.add(new Pit(0, 0));
        northPits.add(new Pit(1, 1));
        northPits.add(new Pit(2, 1));
        northPits.add(new Pit(3, 1));
        northPits.add(new Pit(4, 9));
        northPits.add(new Pit(5, 3));
        southPits.add(new Pit(0, 2));
        southPits.add(new Pit(1, 1));
        southPits.add(new Pit(2, 16));
        southPits.add(new Pit(3, 14));
        southPits.add(new Pit(4, 12));
        southPits.add(new Pit(5, 0));
        Map<Integer, BigPit> pnbp = new HashMap<>();
        pnbp.put(0, new BigPit());
        pnbp.put(1, new BigPit());
        Map<Integer, List<Pit>> pnp = new HashMap<>();
        pnp.put(0, northPits);
        pnp.put(1, southPits);
        board.setSouthPits(southPits);
        board.setNorthPits(northPits);
        when(boardDao.getPitList()).thenReturn(pnp);
        when(playerDao.getPlayerById(0)).thenReturn(new Player(0));
        when(boardDao.getBigPit()).thenReturn(pnbp);
        when(boardDao.haveWinner(board)).thenReturn(true);
        BigPit northKahala = new BigPit();
        BigPit southKahala = new BigPit();
        northKahala.setScore(9);
        southKahala.setScore(3);
        board.setNorthKalaha(northKahala);
        board.setSouthKalaha(southKahala);

        Board response = boardService.sowStones(1, 0);
        assertEquals(response, board);
    }
}