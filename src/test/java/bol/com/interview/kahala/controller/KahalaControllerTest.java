package bol.com.interview.kahala.controller;

import bol.com.interview.kahala.model.Board;
import bol.com.interview.kahala.service.IBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class KahalaControllerTest {

    @Test
    void initBoard() {
        KahalaController kahalaController = new KahalaController();
        IBoardService service = mock(IBoardService.class);
        Board response = kahalaController.initBoard();
        assertSame(new Board(), response);
    }

    @Test
    void playFirst() {
    }

    @Test
    void playNext() {
    }

    @Test
    void sow() {
    }

    @Test
    void winner() {
    }
}