package bol.com.interview.kahala.service;

import bol.com.interview.kahala.controller.KahalaController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ServiceIntTest.class)
class ServiceIntTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void initializeBoard() {

    }

    @Test
    void playFirst() {
    }

    @Test
    void sowStones() {
    }

    @Test
    void playNext() {
    }

    @Test
    void winner() {
    }
}