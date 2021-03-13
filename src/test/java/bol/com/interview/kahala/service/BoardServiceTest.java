package bol.com.interview.kahala.service;

import bol.com.interview.kahala.controller.KahalaController;
import bol.com.interview.kahala.dao.BoardDao;
import bol.com.interview.kahala.dao.PlayerDao;
import bol.com.interview.kahala.exception.ControllerAdvisor;
import bol.com.interview.kahala.model.BigPit;
import bol.com.interview.kahala.model.Board;
import bol.com.interview.kahala.model.Pit;
import bol.com.interview.kahala.model.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
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
        assertEquals(response.getNorthKahala(),null);
    }

    @Test
    void sowStones() {
        when(boardDao.initializeBoard()).thenReturn(new Board());
        Board response = boardService.initializeBoard();
        List<Pit> pits = new ArrayList<>();
        List<Pit> reverse = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            pits.add(new Pit(i, 6));
            reverse.add(new Pit(i, 6));
        }
        Map<Integer, BigPit> pnbp = new HashMap<>();
        pnbp.put(0, new BigPit());
        pnbp.put(1, new BigPit());
        Map<Integer, List<Pit>> pnp = new HashMap<>();
        pnp.put(0, pits);
        pnp.put(1, reverse);

        when(boardDao.getPitList()).thenReturn(pnp);
        when(playerDao.getPlayerById(0)).thenReturn(new Player(0));
        when(boardDao.getBigPit()).thenReturn(pnbp);

        //Mockito.doNothing().when(playerDao.setPlayNext(new Player(0)))
        Board response1 = boardService.sowStones(1, 0);
        assertEquals(response1, Board.getInstance());
    }
}