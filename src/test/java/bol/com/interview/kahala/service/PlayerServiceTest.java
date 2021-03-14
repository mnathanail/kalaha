package bol.com.interview.kahala.service;

import bol.com.interview.kahala.dao.BoardDao;
import bol.com.interview.kahala.dao.PlayerDao;
import bol.com.interview.kahala.model.Player;
import bol.com.interview.kahala.model.Winner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    private MockMvc mvc;

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerDao playerDao;

    @Test
    void playFirst() {
        when(playerDao.playFirst()).thenReturn(new Player(0));
        Player response = playerService.playFirst();
        assertEquals(response, new Player(0));
    }

    @Test
    void playNext() {
        when(playerDao.getPlayNext()).thenReturn(new Player(0));
        Player response = playerService.playNext();
        assertEquals(response, new Player(0));
    }

    @Test
    void winner() {
        when(playerDao.getWinner()).thenReturn(new Winner("Player 1 wins", new Player(0)));
        Winner response = playerService.winner();
        assertEquals(response.getMessage(), "Player 1 wins");
        assertEquals(response.getWinner(), new Player(0));
    }
}