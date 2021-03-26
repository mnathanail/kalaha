package com.interview.kalaha.controller;

import com.interview.kalaha.exception.ControllerAdvisor;
import com.interview.kalaha.model.Board;
import com.interview.kalaha.model.Player;
import com.interview.kalaha.model.Winner;
import com.interview.kalaha.service.BoardService;
import com.interview.kalaha.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ImportAutoConfiguration(ControllerAdvisor.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class KalahaControllerTest {

    private MockMvc mvc;

    private MockMvc mvcContext;

    @InjectMocks
    private KalahaController kalahaController;

    @Mock
    private BoardService boardService;
    @Mock
    private PlayerService playerService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(kalahaController)
                .setControllerAdvice(new ControllerAdvisor())
                .build();

        mvcContext = MockMvcBuilders
                .webAppContextSetup(context)
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
        assertEquals(response.getContentAsString(), "{\"southKalaha\":null,\"northKalaha\":null,\"northPits\":null,\"southPits\":null,\"winner\":true}");
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
        given(boardService.sowStones(1,0)).willReturn(new Board());
        MockHttpServletResponse response = mvc.perform(get("/sow-stones")
                .param("pit", "1")
        )
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void sowMissingParameterMessage() throws Exception {
        given(boardService.sowStones(1,0)).willReturn(new Board());
        MockHttpServletResponse response = mvc.perform(get("/sow-stones")
                .param("pit", "1")
        )
                .andReturn().getResponse();
        assertEquals(response.getContentAsString(), "{\"value\":400,\"name\":\"MissingServletRequestParameterException\",\"message\":\"Required parameter player is missing\"}");
    }

    @Test
    void sowMethodArgumentTypeMismatchException() throws Exception {
        given(boardService.sowStones(1,1)).willReturn(new Board());
        MockHttpServletResponse response = mvc.perform(get("/sow-stones")
                .param("pit", "1")
                .param("player", "3ABC")
        ).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void sowConstraintViolationException() throws Exception {
        MockHttpServletResponse response = mvcContext.perform(get("/sow-stones")
                .param("pit", "1231")
                .param("player", "1")
        ).andReturn().getResponse();
        System.out.println(response.getContentAsString());

        assertEquals(response.getStatus(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    void sowMethodNumberFormatException() throws Exception {
        given(boardService.sowStones(1,1)).willReturn(new Board());
        MockHttpServletResponse response = mvc.perform(get("/sow-stones")
                .param("pit", "1!342d")
                .param("player", "3ABC")
        ).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void winner() throws Exception {
        given(playerService.winner()).willReturn(new Winner("msg", new Player(0)));
        MockHttpServletResponse response = mvc.perform(get("/winner"))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    void winnerNullPointer() throws Exception {
        given(playerService.winner()).willThrow(new NullPointerException()).getMock();
        MockHttpServletResponse response = mvc.perform(get("/winner"))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}