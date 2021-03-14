package bol.com.interview.kahala.controller;

import bol.com.interview.kahala.dao.BoardDao;
import bol.com.interview.kahala.exception.ControllerAdvisor;
import bol.com.interview.kahala.model.BigPit;
import bol.com.interview.kahala.model.Board;
import bol.com.interview.kahala.model.Player;
import bol.com.interview.kahala.model.Winner;
import bol.com.interview.kahala.service.BoardService;
import bol.com.interview.kahala.service.IBoardService;
import bol.com.interview.kahala.service.IPlayerService;
import bol.com.interview.kahala.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.validation.ConstraintViolationException;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ImportAutoConfiguration(ControllerAdvisor.class)
class KahalaControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private KahalaController kahalaController;

    @Mock
    private BoardService boardService;
    @Mock
    private PlayerService playerService;

    @BeforeEach
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(kahalaController)
                .setControllerAdvice(new ControllerAdvisor())
                .build();
    }

    @Test
    public void initBoard() throws Exception {
        Board board = new Board();
        board.setWinner(true);
        given(boardService.initializeBoard()).willReturn(board);
        MockHttpServletResponse response = mvc.perform(get("/start-game"))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(),"{\"southKahala\":null,\"northKahala\":null,\"northPits\":null,\"southPits\":null,\"winner\":true}");
    }

    @Test
    void playFirst() throws Exception {
        given(playerService.playFirst()).willReturn(new Player(0));
        MockHttpServletResponse response = mvc.perform(get("/first-player"))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    void playNext() throws Exception {
        given(playerService.playNext()).willReturn(new Player(0));
        MockHttpServletResponse response = mvc.perform(get("/next-player"))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    void sow() throws Exception {
        given(boardService.sowStones(1,0)).willReturn(new Board());
        MockHttpServletResponse response = mvc.perform(get("/sow-stones")
                .param("pit", "1")
                .param("player", "0")
        )
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    void sowMissingParameter() throws Exception {
        /*given(boardService.sowStones(1,0)).willReturn(new Board());*/
        MockHttpServletResponse response = mvc.perform(get("/sow-stones")
                .param("pit", "1")
        )
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void sowMissingParameterMessage() throws Exception {
        /*given(boardService.sowStones(1,0)).willReturn(new Board());*/
        MockHttpServletResponse response = mvc.perform(get("/sow-stones")
                .param("pit", "1")
        )
                .andReturn().getResponse();
        assertEquals(response.getContentAsString(), "{\"value\":400,\"name\":\"MissingServletRequestParameterException\",\"message\":\"Required parameter player is missing\"}");
    }

    @Test
    void sowMethodArgumentTypeMismatchException() throws Exception {
        /*given(boardService.sowStones(1,1)).willReturn(new Board());*/
        MockHttpServletResponse response = mvc.perform(get("/sow-stones")
                .param("pit", "1")
                .param("player", "3ABC")
        ).andReturn().getResponse();
        System.out.println(response);
        assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void winner() throws Exception {
        given(playerService.winner()).willReturn(new Winner("msg", new Player(0)));
        MockHttpServletResponse response = mvc.perform(get("/winner"))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
}